package com.example.demo.gen;

import com.example.demo.translator.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.ParserRuleContext;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VisitorTraductor extends JavaParserBaseVisitor<Object> {

    //Variable auxiliar para poder saber a que paquete pertenece la clase o la interfaz
    private String nombrePaquete="";
    private Clasificador clasificador;
    private Retornable retornable;
    //variable auxiliar ["C" si es una clase, "A" si es atributo/relacion]
    private String banderaAnotacion = "C";
    //Almacenan temporalmente los valores necesarios para atributo/relacion
    private String esAuditableAtributo;
    private String pseudonimoAtributo;
    private String multiplicidadRelacion;

    //Se activa cuando se lee el paquete y guarda su nombre
    @Override
    public Object visitPackageDeclaration(JavaParser.PackageDeclarationContext ctx) {
        String x = ctx.qualifiedName().IDENTIFIER().toString();
        x = x.replace("]", "");
        x = x.replaceAll(", ", ".");
        x = x.substring(x.lastIndexOf("."));
        nombrePaquete = x.replaceAll("\\.", "");
        return super.visitPackageDeclaration(ctx);
    }


    @Override
    public Object visitTypeDeclaration(JavaParser.TypeDeclarationContext ctx) {

        //Si encuentra una clase
        if(ctx.children.contains(ctx.classDeclaration())) {
            clasificador = new Clase();
            clasificador.setNombre(ctx.classDeclaration().IDENTIFIER().getText());
            clasificador.setTipo("Clase"); //valor auxiliar para StringTemplate

            clasificador.setNombrePaquete(nombrePaquete);
            //Si una clase es abstracta
            for (JavaParser.ClassOrInterfaceModifierContext x : ctx.classOrInterfaceModifier()) {
                if (x.children.contains(x.ABSTRACT())) {
                    ((Clase) clasificador).setEsAbstracta("Es Abstracta");
                }
            }
            //Guarda los nombres de las interfaces que implementa, si tiene
            if (ctx.classDeclaration().children.contains(ctx.classDeclaration().IMPLEMENTS())) {
                for (JavaParser.TypeTypeContext y : ctx.classDeclaration().typeList().typeType()) {
                    String x = y.classOrInterfaceType().IDENTIFIER().toString();
                    x = x.replaceAll("\\[", "").replaceAll("]", "");
                    ((Clase) clasificador).getImplementaciones().add(x);
                }
            }
            //Guarda el nombre de la superclase, si tiene
            if (ctx.classDeclaration().children.contains(ctx.classDeclaration().EXTENDS())) {
                String x = ctx.classDeclaration().typeType().classOrInterfaceType().IDENTIFIER().toString();
                x = x.replaceAll("\\[","").replaceAll("]","");
                clasificador.getHerencias().add(x);

            }
        }
        //Si encuentra una interfaz
        if(ctx.children.contains(ctx.interfaceDeclaration())){
            clasificador = new Interface();
            clasificador.setNombrePaquete(nombrePaquete);
            clasificador.setTipo("Interface");//valor auxiliar para StringTemplate
            clasificador.setNombre(ctx.interfaceDeclaration().IDENTIFIER().getText());

            //Guarda el nombre de las interfaces de las que hereda, si tiene
            if(ctx.interfaceDeclaration().children.contains(ctx.interfaceDeclaration().EXTENDS())){
                for (JavaParser.TypeTypeContext x : ctx.interfaceDeclaration().typeList().typeType()) {
                    clasificador.getHerencias().add(x.getText());

                }
            }
        }
        return super.visitTypeDeclaration(ctx);
    }

    //cambia la bandera de anotaciones
    @Override
    public Object visitClassDeclaration(JavaParser.ClassDeclarationContext ctx) {
        banderaAnotacion = "A";
        return super.visitClassDeclaration(ctx);
    }
    //cambia la bandera de anotaciones


    @Override
    public Object visitInterfaceDeclaration(JavaParser.InterfaceDeclarationContext ctx) {
        banderaAnotacion = "A";
        return super.visitInterfaceDeclaration(ctx);
    }

    //carga los atributos y relaciones de una clase
    @Override
    public Object visitFieldDeclaration(JavaParser.FieldDeclarationContext ctx) {

        //el bucle se repite para cada atributo nuevo(aunque esten definidos en la misma linea)
        for (JavaParser.VariableDeclaratorContext x : ctx.variableDeclarators().variableDeclarator()) {
            retornable = new Atributo();
            setearModificador(ctx.getParent().getParent());
            //si el atributo es de tipo primitivo
            if(ctx.typeType().children.contains(ctx.typeType().primitiveType())){

                retornable.setNombre(x.variableDeclaratorId().IDENTIFIER().toString());
                ((Atributo)retornable).setTipo(ctx.typeType().primitiveType().getText());
                ((Atributo)retornable).setEsAuditable(esAuditableAtributo);
                ((Atributo)retornable).setPseudonimo(pseudonimoAtributo);
                //modifico las banderas
                esAuditableAtributo = null;
                pseudonimoAtributo = null;

                clasificador.getAtributosContenidos().add((Atributo) retornable);
                //si el atributo no es de tipo primitivo
            }else if(ctx.typeType().children.contains(ctx.typeType().classOrInterfaceType())) {
                String identifier = ctx.typeType().classOrInterfaceType().getText();
                //si el atributo no pertenece a una lista
                if(identifier.contains("String") ||
                        identifier.contains("Integer")||
                        identifier.contains("Date") ||
                        identifier.contains("Long")){
                    retornable.setNombre(x.variableDeclaratorId().IDENTIFIER().toString());
                    ((Atributo)retornable).setTipo(identifier);
                    clasificador.getAtributosContenidos().add((Atributo) retornable);
                    //si es una relacion
                }else{
                    Relacion relacion = new Relacion();
                    relacion.setEsFinal(retornable.getEsFinal());
                    relacion.setEsStatic(retornable.getEsStatic());
                    relacion.setVisibilidad(retornable.getVisibilidad());
                    relacion.setNombre(x.variableDeclaratorId().IDENTIFIER().toString());
                    relacion.setOrigen((Clase)clasificador);
                    identifier = identifier.replaceAll("List","").replaceAll("<","").replaceAll(">","");
                    relacion.setNombreClasificadorDestino(identifier);
                    relacion.setMultiplicidad(multiplicidadRelacion);
                    //modifico la bandera
                    multiplicidadRelacion = null;
                    ((Clase) clasificador).getRelacionesContenidas().add(relacion);
                }
            }
        }
        return super.visitFieldDeclaration(ctx);
    }
    //agrega los atributos de una interface
    @Override
    public Object visitConstDeclaration(JavaParser.ConstDeclarationContext ctx) {
        for (JavaParser.ConstantDeclaratorContext x : ctx.constantDeclarator()) {
            retornable = new Atributo();
            setearModificador(ctx.getParent().getParent());
            retornable.setNombre(x.IDENTIFIER().getText());
            ((Atributo)retornable).setTipo(ctx.typeType().getText());
            clasificador.getAtributosContenidos().add((Atributo)retornable);
        }
        return super.visitConstDeclaration(ctx);
    }

    //carga los metodos de una clase
    @Override
    public Object visitMethodDeclaration(JavaParser.MethodDeclarationContext ctx) {
        retornable = new Operacion();
        setearModificador(ctx.getParent().getParent());
        ((Operacion)retornable).setTipoRetorno(ctx.typeTypeOrVoid().getText());
        retornable.setNombre(ctx.IDENTIFIER().toString());
        //condicional en caso de que no existan parametros
        if(ctx.formalParameters().children.contains(ctx.formalParameters().formalParameterList())) {
            for (JavaParser.FormalParameterContext x : ctx.formalParameters().formalParameterList().formalParameter()) {
                String parametro = x.typeType().getText() +" "+ x.variableDeclaratorId().getText();
                ((Operacion)retornable).getParametrosContenidos().add(parametro);
            }
        }
        //en caso de que el metodo sea abstracto
        JavaParser.ClassBodyDeclarationContext padre = (JavaParser.ClassBodyDeclarationContext) ctx.getParent().getParent();
        for (JavaParser.ModifierContext x : padre.modifier()) {
            if(x.getText().equals("abstract")){
                ((Operacion)retornable).setEsAbstracta("esAbstracta");
            }
        }

        clasificador.getOperacionesContenidas().add((Operacion) retornable);
        return super.visitMethodDeclaration(ctx);
    }

    //carga los metodos de una interface
    @Override
    public Object visitInterfaceMethodDeclaration(JavaParser.InterfaceMethodDeclarationContext ctx) {
        retornable = new Operacion();
        setearModificador(ctx.getParent().getParent());
        ((Operacion) retornable).setTipoRetorno(ctx.typeTypeOrVoid().getText());
        retornable.setNombre(ctx.IDENTIFIER().toString());
        //condicional en caso que no existan parametros
        if (ctx.formalParameters().children.contains(ctx.formalParameters().formalParameterList())) {
            for (JavaParser.FormalParameterContext x : ctx.formalParameters().formalParameterList().formalParameter()) {
                String parametro = x.typeType().getText() + " " + x.variableDeclaratorId().getText();
                ((Operacion) retornable).getParametrosContenidos().add(parametro);
            }
        }
        //en caso de que el metodo sea abstracto
        JavaParser.InterfaceBodyDeclarationContext padre = (JavaParser.InterfaceBodyDeclarationContext) ctx.getParent().getParent();
        for (JavaParser.ModifierContext x : padre.modifier()) {
            if(x.getText().equals("abstract")){
                ((Operacion)retornable).setEsAbstracta("esAbstracta");
            }
        }
        clasificador.getOperacionesContenidas().add((Operacion) retornable);

        return super.visitInterfaceMethodDeclaration(ctx);
    }


    @Override
    public Object visitAnnotation(JavaParser.AnnotationContext ctx) {
        String anotacion = ctx.qualifiedName().getText();
        //si es anotacion de clase
        if(banderaAnotacion.equals("C")){
            //si es auditable
            if(anotacion.equals("Audited")){
                ((Clase)clasificador).setEsAuditable("esAuditable");
            }
            //si la clase es un controlador
            if(anotacion.equals("RestController")){
                ((Clase)clasificador).setEsControlador(true);
            }
            //si es anotacion de atributo/relacion
        }else if(banderaAnotacion.equals("A")){
            //si la relacion es OneToOne
            if(anotacion.equals("OneToOne")){
                multiplicidadRelacion = "OneToOne";
            }
            //si la relacion es OneToMany
            if(anotacion.equals("OneToMany")){
                multiplicidadRelacion = "OneToMany";
            }
            //si la relacion es ManyToOne
            if(anotacion.equals("ManyToOne")){
                multiplicidadRelacion = "ManyToOne";
            }
            //si la relacion es ManyToMany
            if(anotacion.equals("ManyToMany")){
                multiplicidadRelacion = "ManyToMany";
            }
            //si el atributo es auditable
            if(anotacion.equals("Audited")){

                esAuditableAtributo = "esAuditable";
            }
            //si el atributo tiene pseudonimo
            if(anotacion.equals("Column")){
                if(ctx.children.contains(ctx.elementValuePairs())){
                    pseudonimoAtributo = ctx.elementValuePairs().getText().replaceAll("\"","").replaceAll("name","").replaceAll("=","");
                }
            }
        }
        return super.visitAnnotation(ctx);
    }

    //Metodo auxiliar para setear el modificador ya sea
    //de un metodo o de un atributo
    private void setearModificador(ParserRuleContext ctx){

        //Controla que tenga por lo menos un modificador que no sea el Default
        if(ctx.getChild(0) instanceof JavaParser.ModifierContext) {
            for(int i = 0; i<ctx.getChildCount()-1; i++){
                if(ctx.getChild(i).getText().contains("static")){
                    retornable.setEsStatic("Static");
                }
                if(ctx.getChild(i).getText().contains("final")){
                    retornable.setEsFinal("Final");
                }
                if(ctx.getChild(i).getText().contains("public")){
                    retornable.setVisibilidad("public");
                }else if(ctx.getChild(i).getText().contains("private")){
                    retornable.setVisibilidad("private");
                }else if(ctx.getChild(i).getText().contains("protected")){
                    retornable.setVisibilidad("protected");
                }
            }
        }
    }

}
