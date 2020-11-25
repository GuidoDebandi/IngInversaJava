package com.example.demo.gen.translator;

import com.example.demo.gen.java.JavaParser;
import com.example.demo.model.translator.Atributo;
import com.example.demo.model.translator.Operacion;
import com.example.demo.model.translator.enumeraciones.ModificadoresAcceso;

public class VisitorMethodDeclaration {

    private VisitorTraductorMain visitorTraductorMain;
    public VisitorMethodDeclaration(VisitorTraductorMain visitorTraductorMain){
        this.visitorTraductorMain = visitorTraductorMain;
    }
    public void visitClassMethodDeclaration(JavaParser.MethodDeclarationContext ctx){
        Operacion retornable = new Operacion();
        VisitorModifier.setearModificador(ctx.getParent().getParent(),retornable);
        retornable.setTipoRetorno(ctx.typeTypeOrVoid().getText());
        retornable.setNombre(ctx.IDENTIFIER().toString());
        //condicional en caso que no existan parametros
        if (ctx.formalParameters().children.contains(ctx.formalParameters().formalParameterList())) {
            for (JavaParser.FormalParameterContext x : ctx.formalParameters().formalParameterList().formalParameter()) {
                Atributo parametro = new Atributo();
                parametro.setTipo(x.typeType().getText());
                parametro.setNombre(x.variableDeclaratorId().getText());
                retornable.getParametrosContenidos().add(parametro);
            }
        }
        JavaParser.ClassBodyDeclarationContext padre = (JavaParser.ClassBodyDeclarationContext) ctx.getParent().getParent();
        //en caso de que el metodo sea abstracto
        for (JavaParser.ModifierContext x : padre.modifier()) {
            if(x.getText().equals(ModificadoresAcceso.Abstract.toString().toLowerCase())){
                retornable.setEsAbstracta(ModificadoresAcceso.Abstract.toString().toLowerCase());
            }
        }
        visitorTraductorMain.getClasificador().getOperacionesContenidas().add(retornable);
    }
    public void visitInterfaceMethodDeclaration(JavaParser.InterfaceMethodDeclarationContext ctx){
        Operacion retornable = new Operacion();
        VisitorModifier.setearModificador(ctx.getParent().getParent(),retornable);
        retornable.setTipoRetorno(ctx.typeTypeOrVoid().getText());
        retornable.setNombre(ctx.IDENTIFIER().toString());
        //condicional en caso que no existan parametros
        if (ctx.formalParameters().children.contains(ctx.formalParameters().formalParameterList())) {
            for (JavaParser.FormalParameterContext x : ctx.formalParameters().formalParameterList().formalParameter()) {
                Atributo parametro = new Atributo();
                parametro.setTipo(x.typeType().getText());
                parametro.setNombre(x.variableDeclaratorId().getText());
                retornable.getParametrosContenidos().add(parametro);
            }
        }
        JavaParser.InterfaceBodyDeclarationContext padre = (JavaParser.InterfaceBodyDeclarationContext) ctx.getParent().getParent();
        //en caso de que el metodo sea abstracto
        for (JavaParser.ModifierContext x : padre.modifier()) {
            if(x.getText().equals(ModificadoresAcceso.Abstract.toString().toLowerCase())){
                retornable.setEsAbstracta(ModificadoresAcceso.Abstract.toString().toLowerCase());
            }
        }
        visitorTraductorMain.getClasificador().getOperacionesContenidas().add(retornable);
    }
}
