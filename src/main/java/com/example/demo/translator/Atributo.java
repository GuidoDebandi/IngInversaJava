package com.example.demo.translator;


import com.example.demo.translator.enumeraciones.RepresentacionGrafica;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Atributo extends Retornable{

    private String tipo;
    private String esAuditable; //falta
    private String pseudonimo;  //falta

    //no se cargan
    private RepresentacionGrafica representacionGrafica= RepresentacionGrafica.text;




}
