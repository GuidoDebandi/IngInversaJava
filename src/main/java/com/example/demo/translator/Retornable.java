package com.example.demo.translator;


import lombok.Data;

@Data

public abstract class Retornable {

    private String nombre;

    private Modificador modificador;

    private String tipo;
}
