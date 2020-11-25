package com.example.demo.gen.translator;

import com.example.demo.gen.java.JavaParser;
import com.example.demo.model.translator.Atributo;
import com.example.demo.model.translator.Clasificador;

public class VisitorConstDeclaration {

    public static void visitConstDeclaration(Clasificador clasificador, JavaParser.ConstDeclarationContext ctx){
        for (JavaParser.ConstantDeclaratorContext x : ctx.constantDeclarator()) {
            Atributo retornable = new Atributo();
            VisitorModifier.setearModificador(ctx.getParent().getParent(), retornable);
            retornable.setNombre(x.IDENTIFIER().getText());
            retornable.setTipo(ctx.typeType().getText());
            clasificador.getAtributosContenidos().add(retornable);
        }
    }

}
