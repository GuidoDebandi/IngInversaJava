package com.example.demo.processors.translator;

import com.example.demo.model.translator.*;
import com.example.demo.model.translator.Package;

import java.util.List;

public class ProcesadorTraductor {
    public static void procesar(List<Clasificador> clasificadorList,DiagramaClases diagrama){
        //recorro todas los clasificadores
        for (Clasificador c : clasificadorList) {
            //si tiene herencias
            if(c.getHerencias().size()>0){
                //recorro el vector que almacena los nombres de los clasificadores padre
                for (String padre : c.getHerencias()) {
                    //controla para no considerar Base
                    if(!(padre.equals("Base"))){
                        Herencia herencia = new Herencia();
                        herencia.setOrigen(c);
                        //busco en todos los clasificadores, cual es el padre y lo seteo
                        for (Clasificador posiblePadre : clasificadorList) {
                            if(posiblePadre.getNombre().equals(padre)){
                                herencia.setDestino(posiblePadre);
                            }
                        }
                        //agrego la herencia al clasificador
                        c.getHerenciasContenidas().add(herencia);
                    }
                }
            }
            //si es una clase
            if(c.getClass().getTypeName().equals("com.example.demo.model.translator.Clase")){
                //si tiene realizaciones
                if(((Clase)c).getImplementaciones().size()>0){
                    //recorro el vector que almacena los nombres de las interfaces implementadas
                    for (String implementada : ((Clase)c).getImplementaciones()) {
                        Realizacion realizacion = new Realizacion();
                        realizacion.setOrigen((Clase)c);
                        //busco en las interfaces cual es y la setea
                        for (Clasificador posibleInterfaz : clasificadorList) {
                            if(posibleInterfaz.getNombre().equals(implementada)){
                                realizacion.setDestino((Interface)posibleInterfaz);
                            }
                        }
                        //agrego la realizacion a la clase
                        ((Clase)c).getRealizacionesContenidas().add(realizacion);
                    }
                }
                //si tiene relaciones
                if(((Clase)c).getRelacionesContenidas().size()>0){
                    //recorro todas las relaciones de la clase
                    for (Relacion r : ((Clase)c).getRelacionesContenidas()) {
                        r.setTipo("unidireccional");
                        //busco entre todos los clasificadores, cual es el destino y lo seteo
                        for (Clasificador posibleDestino : clasificadorList) {
                            if(posibleDestino.getNombre().equals(r.getNombreClasificadorDestino())){
                                r.setDestino(posibleDestino);
                                if(((Clase)posibleDestino).getRelacionesContenidas().size()>0){
                                    for (Relacion r2: ((Clase)posibleDestino).getRelacionesContenidas()){
                                        if(r.getDestino().getNombre().equals(r2.getOrigen().getNombre())){
                                            r.setTipo("bidireccional");
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                //si la clase es un Controlador
                if(((Clase)c).isEsControlador()){

                    //almaceno el nombre del controlador
                    String claseController = c.getNombre().replaceAll("Controller","");
                    //recorro las clases
                    for (Clasificador clase : clasificadorList) {
                        //si coinciden los nombres, tiene ABM
                        if(clase.getNombre().equals(claseController)){
                            ((Clase)clase).setTieneABM("tieneABM");
                        }
                    }
                }

            }
            //crea el paquete
            if(!(diagrama.getPackagesContenidos().size()>0)){
                //crea el primer paquete de todos, y le agrega el clasificador actual

                if(!(c.isEsControlador() || c.isEsBase() || c.isEsRepositorio() || c.isEsServicio())){
                    Package paquete = new Package();
                    paquete.setIndice(Package.contadorClasificadores);
                    Package.contadorClasificadores++;
                    Clasificador.contadorClasificadores = 0;
                    paquete.setNombre(c.getNombrePaquete());
                    c.setIndice(Clasificador.contadorClasificadores);
                    Clasificador.contadorClasificadores++;
                    paquete.getClasificadoresContenidos().add(c);
                    diagrama.getPackagesContenidos().add(paquete);
                }
            }else{
                    /*
                    revisa que el paquete ya haya sido creado:
                      Si fue creado, le agrega el clasificador actual
                      Si no fue creado, crea un paquete nuevo y le agrega el clasificador actual
                    */
                boolean controlAux = true; //vale true si el paquete no existe, false caso contrario
                for (Package paquete : diagrama.getPackagesContenidos()) {
                    if(paquete.getNombre().equals(c.getNombrePaquete())){
                        if(!(c.isEsBase())){

                            c.setIndice(Clasificador.contadorClasificadores);
                            Clasificador.contadorClasificadores++;
                            paquete.getClasificadoresContenidos().add(c);
                            controlAux = false;
                        }
                    }
                }
                if(controlAux){
                    if(!(c.isEsControlador() || c.isEsBase() || c.isEsRepositorio() || c.isEsServicio())){
                        Package paquete = new Package();
                        paquete.setIndice(Package.contadorClasificadores);
                        Package.contadorClasificadores++;
                        Clasificador.contadorClasificadores = 0;
                        paquete.setNombre(c.getNombrePaquete());
                        c.setIndice(Clasificador.contadorClasificadores);
                        Clasificador.contadorClasificadores++;
                        paquete.getClasificadoresContenidos().add(c);
                        diagrama.getPackagesContenidos().add(paquete);
                    }
                }
            }
        }
    }
}
