package com.example.demo.entities.entidadesTraductor;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Package {

    private String nombre;
    private List<Clasificador> clasificadoresContenidos = new ArrayList<>();

    //Auxiliar
    private int indice;




}
