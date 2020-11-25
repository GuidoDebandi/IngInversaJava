package com.example.demo.gen.translator;

import com.example.demo.gen.java.JavaParser;
import com.example.demo.model.translator.Retornable;
import org.antlr.v4.runtime.ParserRuleContext;

public class VisitorModifier {

    //Metodo auxiliar para setear el modificador ya sea
    //de un metodo o de un atributo
    public static void setearModificador(ParserRuleContext ctx, Retornable retornable){
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
