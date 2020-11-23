package com.example.demo.translator;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Operacion extends Retornable{

    private String tipoRetorno;
    private String esAbstracta;
    private List<String> parametrosContenidos = new ArrayList<>();


    //no se cargan
    private List<ParametroInterno> parametrosInternos = new ArrayList<>();
    private EndPoint endPoint;

}


