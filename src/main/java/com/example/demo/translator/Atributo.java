package com.example.demo.translator;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Atributo extends Retornable {

    private String esAuditable;

    private String pseudonimo;



}