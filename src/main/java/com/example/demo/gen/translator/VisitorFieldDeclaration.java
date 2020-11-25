package com.example.demo.gen.translator;

import com.example.demo.gen.java.JavaParser;
import com.example.demo.model.translator.Atributo;
import com.example.demo.model.translator.Clase;
import com.example.demo.model.translator.Relacion;
import com.example.demo.model.translator.enumeraciones.TipoDato;

public class VisitorFieldDeclaration {

    private VisitorTraductorMain visitorTraductorMain;
    public VisitorFieldDeclaration(VisitorTraductorMain visitorTraductorMain){
        this.visitorTraductorMain = visitorTraductorMain;
    }
    public void visitFieldDeclaration(JavaParser.FieldDeclarationContext ctx){
        visitorTraductorMain.setRetornable(new Atributo());
        VisitorModifier.setearModificador(ctx.getParent().getParent(),visitorTraductorMain.getRetornable());
        //el bucle se repite para cada atributo nuevo(aunque esten definidos en la misma linea)
        for (JavaParser.VariableDeclaratorContext x : ctx.variableDeclarators().variableDeclarator()) {
            //si el atributo es de tipo primitivo
            if(ctx.typeType().children.contains(ctx.typeType().primitiveType())){
                setearDatoAtributo(x);
                ((Atributo)visitorTraductorMain.getRetornable()).setTipo(ctx.typeType().primitiveType().getText());
                visitorTraductorMain.getClasificador().getAtributosContenidos().add((Atributo) visitorTraductorMain.getRetornable());
                //si el atributo no es de tipo primitivo
            }else if(ctx.typeType().children.contains(ctx.typeType().classOrInterfaceType())) {
                String identifier = ctx.typeType().classOrInterfaceType().getText();
                //si el atributo no pertenece a una lista
                if(perteneceLista(identifier)){
                    setearDatoAtributo(x);
                    ((Atributo)visitorTraductorMain.getRetornable()).setTipo(identifier);
                    visitorTraductorMain.getClasificador().getAtributosContenidos().add((Atributo) visitorTraductorMain.getRetornable());
                    //si es una relacion
                }else{
                    Relacion relacion = new Relacion();
                    relacion.setEsFinal(visitorTraductorMain.getRetornable().getEsFinal());
                    relacion.setEsStatic(visitorTraductorMain.getRetornable().getEsStatic());
                    relacion.setVisibilidad(visitorTraductorMain.getRetornable().getVisibilidad());
                    relacion.setNombre(x.variableDeclaratorId().IDENTIFIER().toString());
                    relacion.setOrigen((Clase)visitorTraductorMain.getClasificador());
                    identifier = identifier.replaceAll("List","").replaceAll("<","").replaceAll(">","");
                    relacion.setNombreClasificadorDestino(identifier);
                    relacion.setMultiplicidad(visitorTraductorMain.getMultiplicidadRelacion());
                    visitorTraductorMain.setMultiplicidadRelacion(null);
                    ((Clase) visitorTraductorMain.getClasificador()).getRelacionesContenidas().add(relacion);
                }
            }
        }
    }
    public void setearDatoAtributo(JavaParser.VariableDeclaratorContext x){
        visitorTraductorMain.getRetornable().setNombre(x.variableDeclaratorId().IDENTIFIER().toString());
        ((Atributo)visitorTraductorMain.getRetornable()).setEsAuditable(visitorTraductorMain.getEsAuditableAtributo());
        if(visitorTraductorMain.getPseudonimoAtributo() == null){
            ((Atributo)visitorTraductorMain.getRetornable()).setPseudonimo(x.variableDeclaratorId().IDENTIFIER().toString());
        }else{
            ((Atributo)visitorTraductorMain.getRetornable()).setPseudonimo(visitorTraductorMain.getPseudonimoAtributo());
        }
        visitorTraductorMain.setEsAuditableAtributo(null);
        visitorTraductorMain.setPseudonimoAtributo(null);
    }
    public boolean perteneceLista(String identifier){
        if(identifier.contains(TipoDato.String.toString()) ||
           identifier.contains(TipoDato.Integer.toString())||
           identifier.contains(TipoDato.Date.toString()) ||
           identifier.contains(TipoDato.Long.toString())){
            return true;
        }
        return false;
    }
}
