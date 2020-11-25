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

public class Operacion extends Retornable{

    private String tipoRetorno;
    private String esAbstracta;
    private List<Atributo> parametrosContenidos = new ArrayList<>();


    //no se cargan
    private List<ParametroInterno> parametrosInternos = new ArrayList<>();
    private EndPoint endPoint;

}


