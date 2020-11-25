package com.example.demo.model.translator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Clase extends Clasificador {

    private String esAbstracta;
    private String esAsociativa; //falta
    private String esAuditable;
    private String tieneABM; //solo se da cuenta si el controlador se llama igual que la clase
    private List<Relacion> relacionesContenidas= new ArrayList<>();
    private List<Realizacion>  realizacionesContenidas= new ArrayList<>();

    //auxiliares
    private List<String> implementaciones = new ArrayList<>();


    private boolean esRevision = false;//bandera para saber si es una clase de Revision

}
