package com.example.demo;



import com.example.demo.gen.JavaLexer;
import com.example.demo.gen.JavaParser;
import com.example.demo.gen.VisitorTraductor;
import com.example.demo.translator.*;
import com.example.demo.translator.Package;
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
        /*
        //creo una clase
        Clase clasePersona = new Clase();
        clasePersona.setNombre("Persona");
        clasePersona.setTipo("Clase");
        clasePersona.setIndice(0);
        clasePersona.setEsAuditable("esAuditable");

        //creo dos atributos y los agrego a la clase
        Atributo atributoNombre = new Atributo();
        atributoNombre.setNombre("nombre");
        atributoNombre.setVisibilidad("private");
        atributoNombre.setTipo("String");
        Atributo atributoEdad = new Atributo();
        atributoEdad.setNombre("edad");
        atributoEdad.setVisibilidad("private");
        atributoEdad.setTipo("int");

        clasePersona.getAtributosContenidos().add(atributoNombre);
        clasePersona.getAtributosContenidos().add(atributoEdad);

        //creo dos operaciones y se las agrego

        Operacion metodoSaltar = new Operacion();
        metodoSaltar.setNombre("saltar");
        metodoSaltar.setVisibilidad("public");
        metodoSaltar.setTipoRetorno("void");
        metodoSaltar.getParametrosContenidos().add("int metros");
        Operacion metodoContar = new Operacion();
        metodoContar.setNombre("contar");
        metodoContar.setVisibilidad("public");
        metodoContar.setTipoRetorno("int");

        clasePersona.getOperacionesContenidas().add(metodoSaltar);
        clasePersona.getOperacionesContenidas().add(metodoContar);

        //creo clase Alumno hija de Persona

        Clase claseAlumno = new Clase();
        claseAlumno.setTipo("Clase");
        claseAlumno.setIndice(1);
        claseAlumno.setNombre("Alumno");
        Herencia herenciaAlumno = new Herencia();
        herenciaAlumno.setOrigen(claseAlumno);
        herenciaAlumno.setDestino(clasePersona);
        claseAlumno.getHerenciasContenidas().add(herenciaAlumno);
        //creo legajo para alumno
        Atributo atributoLegajo = new Atributo();
        atributoLegajo.setTipo("int");
        atributoLegajo.setNombre("legajo");
        atributoLegajo.setVisibilidad("private");
        claseAlumno.getAtributosContenidos().add(atributoLegajo);

        //creo una interfaz y hago que alumno la implemente
        Interface interfazVolador = new Interface();
        interfazVolador.setNombre("Volador");
        interfazVolador.setTipo("Interface");
        interfazVolador.setIndice(2);

        Realizacion implementacionAlumno = new Realizacion();
        implementacionAlumno.setOrigen(claseAlumno);
        implementacionAlumno.setDestino(interfazVolador);
        claseAlumno.getRealizacionesContenidas().add(implementacionAlumno);

        Interface interfazSaltador = new Interface();
        interfazVolador.setNombre("Saltador");
        interfazVolador.setTipo("Interface");
        interfazVolador.setIndice(3);

        Realizacion implementacionAlumno2 = new Realizacion();
        implementacionAlumno.setOrigen(claseAlumno);
        implementacionAlumno.setDestino(interfazSaltador);
        claseAlumno.getRealizacionesContenidas().add(implementacionAlumno2);

        //creo domicilio
        Clase claseDomicilio = new Clase();
        claseDomicilio.setNombre("Domicilio");
        claseDomicilio.setIndice(4);
        claseDomicilio.setTipo("Clase");
        Atributo atributoCalle = new Atributo();
        atributoCalle.setNombre("calle");
        atributoCalle.setVisibilidad("private");
        atributoCalle.setTipo("String");
        claseDomicilio.getAtributosContenidos().add(atributoCalle);
        //asocio persona a domicilio
        Relacion relacionPersonaDomicilio = new Relacion();
        relacionPersonaDomicilio.setNombre("haciaDomicilio");
        relacionPersonaDomicilio.setVisibilidad("private");
        relacionPersonaDomicilio.setMultiplicidad("OneToOne");
        relacionPersonaDomicilio.setTipo("Unidireccional");
        relacionPersonaDomicilio.setOrigen(clasePersona);
        relacionPersonaDomicilio.setDestino(claseDomicilio);

        clasePersona.getRelacionesContenidas().add(relacionPersonaDomicilio);

        Package paquete = new Package();
        paquete.setNombre("negocio");

        paquete.getClasificadoresContenidos().add(claseAlumno);
        paquete.getClasificadoresContenidos().add(interfazVolador);
        List<Package> packageList = new ArrayList<>(); //lista de paquetes que se le pasa al StringTemplate
        packageList.add(paquete);

        DiagramaClases diagrama = new DiagramaClases();
        diagrama.setPackagesContenidos(packageList);

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
    */
        final File carpeta = new File("C:\\Users\\Guido\\eclipse-workspace\\IngInversaJava\\src\\main\\resources\\Datos"); //direccion de la carpeta
        try {
            listarFicherosPorCarpeta(carpeta);
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public static void listarFicherosPorCarpeta(final File carpeta) throws IOException {
        List<ParseTree> arboles = new ArrayList<>();
        for (final File ficheroEntrada : carpeta.listFiles()) {
            if (ficheroEntrada.isDirectory()) {
                listarFicherosPorCarpeta(ficheroEntrada);
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
        for(ParseTree arbol : arboles){
            VisitorTraductor vt = new VisitorTraductor();
            vt.visit(arbol);
        }
    }
}
