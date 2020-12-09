package com.example.demo;

import com.example.demo.gen.properties.PropertiesLexer;
import com.example.demo.gen.properties.PropertiesParser;
import com.example.demo.gen.pomxml.XMLLexer;
import com.example.demo.gen.pomxml.XMLParser;
import com.example.demo.gen.translator.VisitorPom;
import com.example.demo.gen.translator.VisitorProperties;
import com.example.demo.model.translator.Artefacto;
import com.example.demo.model.translator.configuraciones.ConfiguracionDB;
import com.example.demo.model.translator.configuraciones.ConfiguracionLenguaje;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.IOException;

import static org.antlr.v4.runtime.CharStreams.fromFileName;

public class mainPruebas {
    public static void main(String[] args){
        try {
        String fuente = "C:\\Users\\Usuario\\eclipse-workspace\\IngInversaJava\\src\\main\\resources\\datos\\pom.xml";
        CharStream cs = fromFileName(fuente);
        XMLLexer lexer = new XMLLexer(cs);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        XMLParser parser = new XMLParser(tokenStream);
        ParseTree tree = parser.document();
        VisitorPom vt = new VisitorPom();
        vt.visit(tree);
        ConfiguracionLenguaje configuracionLenguaje = vt.getConfiguracionLenguaje();
        Artefacto artefacto = vt.getArtefacto();
            System.out.println(artefacto.toString());
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
