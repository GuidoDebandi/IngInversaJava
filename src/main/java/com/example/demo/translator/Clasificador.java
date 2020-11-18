package com.example.demo.translator;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public abstract class Clasificador {

    protected String nombre;
    protected List<Herencia> herenciasContenidas= new ArrayList<>();
    protected List<Operacion> operacionesContenidas= new ArrayList<>();
    protected List<Atributo> atributosContenidos= new ArrayList<>();

    //Auxiliares
    protected String tipo;
    protected int indice;
    protected int indicePackage;

    private String nombrePaquete; //nombre del paquete al cual pertenece
    private List<String> herencias = new ArrayList<>(); //lista de clasificadores padre

}
