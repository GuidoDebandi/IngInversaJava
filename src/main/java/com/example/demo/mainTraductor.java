package com.example.demo;


import com.example.demo.translator.*;
import com.example.demo.translator.Package;
import com.example.demo.translator.configuraciones.ConfiguracionDB;
import com.example.demo.translator.configuraciones.ConfiguracionDocker;
import com.example.demo.translator.configuraciones.ConfiguracionLenguaje;
import com.example.demo.translator.configuraciones.ConfiguracionMicroservicios;
import com.example.demo.translator.enumeraciones.RepresentacionGrafica;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class mainTraductor {
    public static void main(String[] args){

        try {
            DiagramaClases diagrama = new DiagramaClases();

//Configuraciones

            ConfiguracionDB configuracionDB = new ConfiguracionDB();
            configuracionDB.setDbNombre("bd");
            configuracionDB.setIp("localhost");
            configuracionDB.setDbContrasena("");
            configuracionDB.setDbUsuario("root");
            configuracionDB.setDbPuerto(3306);
            configuracionDB.setServerPuerto(9000);

            ConfiguracionDocker configuracionDocker = new ConfiguracionDocker();
            configuracionDocker.setNombreContenedor("contenedor");
            configuracionDocker.setNombreImagen("imagenDocker");
            configuracionDocker.setPuertoInterior(1000);

            ConfiguracionLenguaje configuracionLenguaje = new ConfiguracionLenguaje();
            configuracionLenguaje.setVersion("8");

            ConfiguracionMicroservicios configuracionMicroservicios = new ConfiguracionMicroservicios();

            diagrama.getConfiguracionesDB().add(configuracionDB);
            diagrama.getConfiguracionesDocker().add(configuracionDocker);
            diagrama.getConfiguracionesLenguaje().add(configuracionLenguaje);
            diagrama.getConfiguracionesMicroservicios().add(configuracionMicroservicios);

//Artefacto

            Artefacto artefacto = new Artefacto();
            diagrama.setArtefactoContenido(artefacto);

//Paquetes

            Package paquete = new Package();
            paquete.setIndice(0);
            paquete.setNombre("usuarios");
            diagrama.getPackagesContenidos().add(paquete);

//Clasificadores

            Clase clasePersona = new Clase();
            clasePersona.setTipo("Clase");
            clasePersona.setIndice(0);
            clasePersona.setNombre("Persona");
            clasePersona.setEsAbstracta("true");

            Atributo atributoNombre = new Atributo();
            atributoNombre.setEsAuditable("true");
            atributoNombre.setVisibilidad("Protected");
            atributoNombre.setEsStatic("true");
            atributoNombre.setEsFinal("true");
            atributoNombre.setTipo( "String");
            atributoNombre.setNombre("nombre");
            atributoNombre.setPseudonimo("nombre");
            atributoNombre.setRepresentacionGrafica(RepresentacionGrafica.text);
            clasePersona.getAtributosContenidos().add(atributoNombre);

            Operacion operacionRespirar = new Operacion();
            operacionRespirar.setVisibilidad( "Public");
            //operacionRespirar.setEsAbstracta("true");
            operacionRespirar.setEsStatic("true");
            operacionRespirar.setEsFinal("true");
            operacionRespirar.setTipoRetorno( "String");
            operacionRespirar.setNombre("estudiar");




            //parametros contenidos
            operacionRespirar.getParametrosContenidos().add("String uno");
            operacionRespirar.getParametrosContenidos().add("String dos");

            clasePersona.getOperacionesContenidas().add(operacionRespirar);

            paquete.getClasificadoresContenidos().add(clasePersona);

            ////////////////////////////////////////////////////////////////////////////

            Clase claseEstudiante = new Clase();
            claseEstudiante.setTipo("Clase");
            claseEstudiante.setIndice(1);
            claseEstudiante.setNombre("Estudiante");
            claseEstudiante.setEsAuditable("true");
            claseEstudiante.setTieneABM("true");

            Herencia herencia = new Herencia();
            herencia.setOrigen(claseEstudiante);
            herencia.setDestino(clasePersona);
            claseEstudiante.getHerenciasContenidas().add(herencia);

            Atributo atributoMatricula = new Atributo();
            atributoMatricula.setVisibilidad( "Private");
            atributoMatricula.setTipo( "Int");
            atributoMatricula.setNombre("matricula");
            atributoMatricula.setPseudonimo("matricula");
            atributoMatricula.setRepresentacionGrafica(RepresentacionGrafica.text);
            claseEstudiante.getAtributosContenidos().add(atributoMatricula);

            Operacion operacionEstudiar = new Operacion();
            operacionEstudiar.setVisibilidad( "Public");
            operacionEstudiar.setTipoRetorno( "String");
            operacionEstudiar.setNombre("estudiar");
            EndPoint endPointOperacionEstudiar = new EndPoint();
            endPointOperacionEstudiar.setNombrePublicado("Estudiar");
            operacionEstudiar.setEndPoint(endPointOperacionEstudiar);

            claseEstudiante.getOperacionesContenidas().add(operacionEstudiar);


            paquete.getClasificadoresContenidos().add(claseEstudiante);

            ////////////////////////////////////////////////////////////////////////////

            Clase claseActividad = new Clase();
            claseActividad.setTipo("Clase");
            claseActividad.setIndice(2);
            claseActividad.setNombre("Actividad");

            Atributo atributoMateria = new Atributo();
            atributoMateria.setVisibilidad( "Private");
            atributoMateria.setTipo( "Int");
            atributoMateria.setNombre("materia");
            atributoMateria.setPseudonimo("materia");
            atributoMateria.setRepresentacionGrafica(RepresentacionGrafica.text);
            claseActividad.getAtributosContenidos().add(atributoMateria);

            paquete.getClasificadoresContenidos().add(claseActividad);

            ////////////////////////////////////////////////////////////////////////////

            Clase claseEntrega = new Clase(); //Clase asociativa entre estudiante y actividad
            claseEntrega.setTipo("Clase");
            claseEntrega.setIndice(3);
            claseEntrega.setNombre("Entrega");
            claseEntrega.setEsAsociativa("true");

            Atributo atributoNota = new Atributo();
            atributoNota.setVisibilidad("Private");
            atributoNota.setTipo("Int");
            atributoNota.setNombre("nota");
            atributoNota.setPseudonimo("nota");
            atributoNota.setRepresentacionGrafica(RepresentacionGrafica.text);
            claseEntrega.getAtributosContenidos().add(atributoNota);

            Atributo atributoEstudiante = new Atributo();
            atributoEstudiante.setVisibilidad("Private");
            atributoEstudiante.setTipo("Object");
            atributoEstudiante.setNombre("estudiante");
            atributoEstudiante.setPseudonimo("estudiante");
            atributoEstudiante.setRepresentacionGrafica(RepresentacionGrafica.text);
            claseEntrega.getAtributosContenidos().add(atributoEstudiante);

            Atributo atributoActividad = new Atributo();
            atributoActividad.setVisibilidad("Private");
            atributoActividad.setTipo("Object");
            atributoActividad.setNombre("actividad");
            atributoActividad.setPseudonimo("actividad");
            atributoActividad.setRepresentacionGrafica(RepresentacionGrafica.text);
            claseEntrega.getAtributosContenidos().add(atributoActividad);

            paquete.getClasificadoresContenidos().add(claseEntrega);

            ////////////////////////////////////////////////////////////////////////////

            Interface interfazComportamiento = new Interface();
            interfazComportamiento.setTipo("Interface");
            interfazComportamiento.setIndice(4);
            interfazComportamiento.setNombre("Comportamiento");


            paquete.getClasificadoresContenidos().add(interfazComportamiento);

            Interface interface2 = new Interface();
            interface2.setTipo("Interface");
            interface2.setIndice(5);
            interface2.setNombre("interface2");

            paquete.getClasificadoresContenidos().add(interface2);


            ////////////////////////////////////////////////////////////////////////////

            //Relacion

            Relacion relacionActividad = new Relacion();
            relacionActividad.setOrigen(claseEstudiante);
            relacionActividad.setDestino(claseActividad);
            relacionActividad.getClasesAsociativasContenidas().add(claseEntrega);
            relacionActividad.setMultiplicidad( "oneToOne");
            relacionActividad.setTipo( "unidireccional");
            relacionActividad.setVisibilidad( "Private");
            relacionActividad.setNombre("actividad");
            claseEstudiante.getRelacionesContenidas().add(relacionActividad);

            //Realizaciones
            Realizacion realizacion = new Realizacion();
            realizacion.setOrigen(claseEstudiante);
            realizacion.setDestino(interfazComportamiento);
            claseEstudiante.getRealizacionesContenidas().add(realizacion);

            Realizacion realizacion2 = new Realizacion();
            realizacion2.setOrigen(claseEstudiante);
            realizacion2.setDestino(interface2);
            claseEstudiante.getRealizacionesContenidas().add(realizacion2);












            STGroup group = new STGroupFile("src/main/resources/templates/xmi.stg");
            ST plantilla = group.getInstanceOf("traducir");
            plantilla.add("diagrama", diagrama);


            String ruta = "src/main/resources/generated/resultado.xmi";
            File archivo = new File(ruta);
            BufferedWriter bw;
            bw = new BufferedWriter(new FileWriter(archivo));


            bw.write(plantilla.render());
            bw.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }



     /*   ClasiconfiguracionDB.stg
        configuracionDocker.stg
        configuraciones.stg
        configuracionLenguaje.stg
        configuracionMicroservicios.stg
        artefacto.stg
        atributo.stg
        claseAsociativa.stg
        clasificador.stg
        endPoint.stg
        herencia.stg
        operacion.stg
package.stg
        parametro.stg
        parametroInterno.stg
        realizacion.stg
        relacion.stg
        xmi.stgficador c = new Clase();
        Clasificador i = new Interface();

        if(i.getClass().getTypeName().equals("com.example.demo.translator.Interface")){
            System.out.println("Interfaz");
        }
        if(c.getClass().getTypeName().equals("com.example.demo.translator.Clase")){
            System.out.println("Clase");
        }
        String nombre = "PersonaController";
        nombre = nombre.replaceAll("Controller","");
        if("Auto".equals(nombre)){
            System.out.println("TieneABM");
        }



        /*

        //CONTROLADOR

        try{
            STGroup group= new STGroupFile("src/main/resources/templates/main.stg");
            ST plantilla = group.getInstanceOf("traducir");
            plantilla.add("paquetes",paquetes);

            String ruta = "src/main/resources/generated/resultado.xmi";
            File archivo = new File(ruta);
            BufferedWriter bw;
            bw = new BufferedWriter(new FileWriter(archivo));


            //VISTA

            bw.write(plantilla.render());
            bw.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        */
    }
}