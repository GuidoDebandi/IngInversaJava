package com.example.demo.model.translator;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Clasificador {

    protected String nombre;
    protected List<Herencia> herenciasContenidas= new ArrayList<>();
    protected List<Operacion> operacionesContenidas= new ArrayList<>();
    protected List<Atributo> atributosContenidos= new ArrayList<>();

    //Auxiliares
    protected String tipo;//variable que necesita StringTemplate para armar la etiqueta Clasificador
    protected int indice; //variable que necesita StringTemplate para armar las Relaciones,Herencias,Realizaciones
    public static int contadorClasificadores = 0; //variable global para poder asignar el indice

    private String nombrePaquete; //nombre del paquete al cual pertenece
    private List<String> herencias = new ArrayList<>(); //lista de clasificadores padre
    private boolean esControlador = false;//bandera para saber si es Controlador
    private boolean esRepositorio = false;//bandera para saber si es Repositorio
    private boolean esServicio = false;//bandera para saber si es Servicio
    private boolean esBase = false;//bandera para saber si una clase es Base
}
