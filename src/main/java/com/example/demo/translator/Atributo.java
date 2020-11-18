package com.example.demo.translator;


import com.example.demo.translator.enumeraciones.RepresentacionGrafica;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Atributo extends Retornable{

    private String tipo;
    private String esAuditable;
    private String pseudonimo;


    private RepresentacionGrafica representacionGrafica= RepresentacionGrafica.text;




}
