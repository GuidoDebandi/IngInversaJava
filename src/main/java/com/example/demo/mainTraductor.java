package com.example.demo;



import com.example.demo.gen.java.JavaLexer;
import com.example.demo.gen.java.JavaParser;
import com.example.demo.gen.translator.VisitorTraductorMain;
import com.example.demo.model.translator.*;

import com.example.demo.processors.translator.ProcesadorTraductor;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

import java.io.*;

import java.util.ArrayList;
import java.util.List;

public class mainTraductor {
    public static void main(String[] args){

        List<ParseTree> arboles = new ArrayList<>();
        DiagramaClases diagrama = new DiagramaClases();
        final File carpeta = new File("C:\\Users\\Usuario\\eclipse-workspace\\IngInversaJava\\src\\main\\resources\\datos"); //direccion de la carpeta
        try {
            listarFicherosPorCarpeta(carpeta,arboles);
            ProcesadorTraductor.procesar(analizarArboles(arboles),diagrama);
        }catch (IOException e){
            e.printStackTrace();
        }
        try{
            STGroup group= new STGroupFile("src/main/resources/templates/xmi.stg");
            ST plantilla = group.getInstanceOf("traducir");
            plantilla.add("diagrama",diagrama);

            String ruta = "src/main/resources/generated/resultado.xmi";
            File archivo = new File(ruta);
            BufferedWriter bw;
            bw = new BufferedWriter(new FileWriter(archivo));

            bw.write(plantilla.render());
            bw.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public static void listarFicherosPorCarpeta(final File carpeta, List<ParseTree> arboles) throws IOException {
        for (final File ficheroEntrada : carpeta.listFiles()) {
            if (ficheroEntrada.isDirectory()) {
                listarFicherosPorCarpeta(ficheroEntrada,arboles);
            } else {
                String fuente =  ficheroEntrada.getPath();
                InputStream is = new FileInputStream(fuente);
                CharStream cs = null;
                cs = CharStreams.fromStream(is);
                JavaLexer lexer = new JavaLexer(cs);
                CommonTokenStream tokenStream = new CommonTokenStream(lexer);
                JavaParser parser = new JavaParser(tokenStream);
                ParseTree tree = parser.compilationUnit();
                arboles.add(tree);
            }
        }
    }
    public static List<Clasificador> analizarArboles(List<ParseTree> arboles){
        List<Clasificador> clasificadorList = new ArrayList<>();
        for(ParseTree arbol : arboles){
            Clasificador clasificador = new Clasificador();
            VisitorTraductorMain vt = new VisitorTraductorMain();
            vt.setClasificador(clasificador);
            vt.visit(arbol);
            clasificadorList.add(vt.getClasificador());

        }
        return clasificadorList;
    }
}
