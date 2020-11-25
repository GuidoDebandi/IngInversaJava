package com.example.demo.model.translator;


import com.example.demo.model.translator.enumeraciones.RepresentacionGrafica;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Atributo extends Retornable{

    private String tipo;
    private String esAuditable;
    private String pseudonimo;

    //no se cargan
    private RepresentacionGrafica representacionGrafica= RepresentacionGrafica.text;




}
