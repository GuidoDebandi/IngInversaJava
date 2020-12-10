package com.example.demo.processors.translator;

import com.example.demo.model.translator.*;
import com.example.demo.model.translator.Package;

import java.util.List;

public class ProcesadorTraductor {

    public static void procesar(List<Clasificador> clasificadorList, DiagramaClases diagrama) {
        //recorro todos los clasificadores
        for (Clasificador c : clasificadorList) {
            //si tiene herencias
            if (c.getHerencias().size() > 0) {
                //recorro el vector que almacena los nombres de los clasificadores padre
                for (String padre : c.getHerencias()) {
                    //controla para no considerar Base
                    if (!(padre.equals("Base"))) {
                        Herencia herencia = new Herencia();
                        herencia.setOrigen(c);
                        //busco en todos los clasificadores, cual es el padre y lo seteo
                        herencia.setDestino((Clasificador) buscarApuntador(padre, clasificadorList, 1));
                        //agrego la herencia al clasificador
                        c.getHerenciasContenidas().add(herencia);
                    }
                }
            }
            //si es una clase
            if (c.getClass().getTypeName().equals("com.example.demo.model.translator.Clase")) {
                //si tiene realizaciones
                if (((Clase) c).getImplementaciones().size() > 0) {
                    //recorro el vector que almacena los nombres de las interfaces implementadas
                    for (String implementada : ((Clase) c).getImplementaciones()) {
                        Realizacion realizacion = new Realizacion();
                        realizacion.setOrigen((Clase) c);
                        //busco en las interfaces cual es y la setea
                        realizacion.setDestino((Interface) buscarApuntador(implementada, clasificadorList, 1));
                        //agrego la realizacion a la clase
                        ((Clase) c).getRealizacionesContenidas().add(realizacion);
                    }
                }
                //si tiene relaciones
                if (((Clase) c).getRelacionesContenidas().size() > 0) {
                    //recorro todas las relaciones de la clase
                    for (Relacion r : ((Clase) c).getRelacionesContenidas()) {
                        //busco entre todos los clasificadores, cual es el destino y lo seteo
                        r.setDestino((Clasificador) buscarApuntador(r.getNombreClasificadorDestino(), clasificadorList, 1));
                        r.setTipo(comprobarDireccionalidad(r.getDestino()));
                    }
                }
                //si la clase es un Controlador
                if (((Clase) c).isEsControlador()) {
                    //almaceno el nombre del controlador
                    String claseController = c.getNombre().replaceAll("Controller", "");
                    //recorro las clases
                    buscarApuntador(claseController, clasificadorList, 2);
                }
            }
            //crea el paquete
            if (!(diagrama.getPackagesContenidos().size() > 0)) {
                //crea el primer paquete de todos, y le agrega el clasificador actual
                agregarPaquete(diagrama, c);
            } else {
                    /*
                    revisa que el paquete ya haya sido creado:
                      Si fue creado, le agrega el clasificador actual
                      Si no fue creado, crea un paquete nuevo y le agrega el clasificador actual
                    */
                boolean controlAux = true; //vale true si el paquete no existe, false caso contrario
                for (Package paquete : diagrama.getPackagesContenidos()) {
                    if (paquete.getNombre().equals(c.getNombrePaquete()) && (!c.isEsBase())) {
                        c.setIndice(Clasificador.contadorClasificadores);
                        Clasificador.contadorClasificadores++;
                        paquete.getClasificadoresContenidos().add(c);
                        controlAux = false;
                    }
                }
                if (controlAux) {
                    agregarPaquete(diagrama, c);
                }
            }
        }
    }

    public static void agregarPaquete(DiagramaClases diagrama, Clasificador c) {
        if (!(c.isEsControlador() || c.isEsBase() || c.isEsRepositorio() || c.isEsServicio())) {
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

    public static String comprobarDireccionalidad(Clasificador clase) {
        String direccionalidad = "unidireccional";
        if (((Clase) clase).getRelacionesContenidas().size() > 0) {
            for (Relacion r2 : ((Clase) clase).getRelacionesContenidas()) {
                if (clase.getNombre().equals(r2.getOrigen().getNombre())) {
                    direccionalidad = "bidireccional";
                }
            }
        }
        return direccionalidad;
    }

    public static Object buscarApuntador(String buscado, List<Clasificador> clasificadorList, int codigo) {
        for (Clasificador posible : clasificadorList) {
            if (posible.getNombre().equals(buscado)) {
                switch (codigo) {
                    case 1:
                        return posible;
                    case 2:
                        ((Clase) posible).setTieneABM("tieneABM");
                }
            }
        }
        return null;
    }
}