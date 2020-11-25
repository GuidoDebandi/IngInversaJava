package com.example.demo.gen.translator;

import com.example.demo.gen.java.JavaParser;
import com.example.demo.model.translator.Clase;
import com.example.demo.model.translator.Clasificador;
import com.example.demo.model.translator.Interface;

public class VisitorTypeDeclaration {
    public static void visitClassDeclaration(JavaParser.ClassDeclarationContext ctx, Clase clasificador, String nombrePaquete){
        clasificador.setNombre(ctx.IDENTIFIER().getText());
        clasificador.setTipo("Clase"); //valor auxiliar para StringTemplate
        clasificador.setIndice(Clasificador.contadorClasificadores);//valor auxiliar para StringTemplate
        Clasificador.contadorClasificadores++;
        clasificador.setNombrePaquete(nombrePaquete);
        //Guarda el nombre de la superclase, si tiene
        if (ctx.children.contains(ctx.EXTENDS())) {
            String x = ctx.typeType().classOrInterfaceType().IDENTIFIER().toString();
            x = x.replaceAll("\\[","").replaceAll("]","");
            clasificador.getHerencias().add(x);
        }
        //Si una clase es abstracta
        JavaParser.TypeDeclarationContext padre = (JavaParser.TypeDeclarationContext) ctx.getParent();
        for (JavaParser.ClassOrInterfaceModifierContext x : padre.classOrInterfaceModifier()) {
            if (x.children.contains(x.ABSTRACT())) {
                clasificador.setEsAbstracta("Es Abstracta");
            }
        }
        //Guarda los nombres de las interfaces que implementa, si tiene
        if (ctx.children.contains(ctx.IMPLEMENTS())) {
            for (JavaParser.TypeTypeContext y : ctx.typeList().typeType()) {
                String x = y.classOrInterfaceType().IDENTIFIER().toString();
                x = x.replaceAll("\\[", "").replaceAll("]", "");
                clasificador.getImplementaciones().add(x);
            }
        }
    }
    public static void visitInterfaceDeclaration(JavaParser.InterfaceDeclarationContext ctx, Interface clasificador, String nombrePaquete){
        clasificador.setNombre(ctx.IDENTIFIER().getText());
        clasificador.setTipo("Interface");//valor auxiliar para StringTemplate
        clasificador.setIndice(Clasificador.contadorClasificadores);
        Clasificador.contadorClasificadores++;
        clasificador.setNombrePaquete(nombrePaquete);

        //Guarda el nombre de las interfaces de las que hereda, si tiene
        if(ctx.children.contains(ctx.EXTENDS())){
            for (JavaParser.TypeTypeContext x : ctx.typeList().typeType()) {
                clasificador.getHerencias().add(x.getText());
            }
        }
    }
}
