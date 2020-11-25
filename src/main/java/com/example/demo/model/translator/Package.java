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

public class Package {

    private String nombre;
    private List<Clasificador> clasificadoresContenidos = new ArrayList<>();

    //Auxiliar
    private int indice;//variable que necesita StringTemplate para armar las Relaciones,Herencias,Realizaciones
    public static int contadorClasificadores = 0; //variable global para poder asignar el indice

}
