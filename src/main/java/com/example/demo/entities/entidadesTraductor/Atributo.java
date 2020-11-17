package com.example.demo.entities.entidadesTraductor;

import com.example.demo.entities.entidadesTraductor.enumeraciones.RepresentacionGrafica;
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
    private String esAuditable; //falta
    private String pseudonimo;  //falta

    //no se cargan
    private RepresentacionGrafica representacionGrafica= RepresentacionGrafica.text;




}
