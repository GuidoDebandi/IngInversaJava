package com.example.demo.translator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Relacion {

    private String nombre;

    private String tipo;

    private String multiplicidad;

    private String visibilidad;

    private String esFinal;

    private String esStatic;

    private String tipoABM;

    private Clase origen;

    private Clasificador destino;

    private List<Clase> clasesAsociativasContenidas = new ArrayList<>();

}
