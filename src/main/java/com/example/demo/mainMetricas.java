package com.example.demo;


import com.example.demo.model.metrics.Clase;
import com.example.demo.gen.java.JavaLexer;
import com.example.demo.gen.java.JavaParser;
import com.example.demo.gen.metrics.VisitorAtributoMetodo;
import com.example.demo.gen.metrics.VisitorClase;
import com.example.demo.gen.metrics.VisitorMetricas;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import static org.antlr.v4.runtime.CharStreams.fromFileName;

public class mainMetricas {



    public static void main(String[] args){

        try{
            String fuente = "C:\\Users\\Usuario\\eclipse-workspace\\testeosIngInversaJava\\Datos\\Persona.java";
            CharStream cs = fromFileName(fuente);
            JavaLexer lexer = new JavaLexer(cs);
            CommonTokenStream tokenStream = new CommonTokenStream(lexer);
            JavaParser parser = new JavaParser(tokenStream);
            ParseTree tree = parser.compilationUnit();
            Clase clase = new Clase();
            VisitorMetricas visitorMetricas = new VisitorMetricas();
            visitorMetricas.setClase(clase);
            VisitorAtributoMetodo visitorAtributoMetodo = new VisitorAtributoMetodo();
            visitorAtributoMetodo.setClase(clase);
            visitorAtributoMetodo.setVisitorMetricas(visitorMetricas);
            VisitorClase visitorClase = new VisitorClase(visitorAtributoMetodo, visitorMetricas);
            visitorClase.setClase(clase);

            visitorClase.visit(tree);

            System.out.println(clase.toString());

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
