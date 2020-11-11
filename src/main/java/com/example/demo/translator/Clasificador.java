package com.example.demo.translator;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data

public class Clasificador {

    private String nombre;

    private List<Herencia> herenciasContenidas = new ArrayList<>();

    private List<Atributo> atributosContenidos = new ArrayList<>();

    private List<Operacion> operacionesContenidas = new ArrayList<>();


}
