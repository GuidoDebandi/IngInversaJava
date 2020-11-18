package com.example.demo.translator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Operacion extends Retornable{

    private String tipoRetorno;
    private String esAbstracta;
    private List<String> parametrosContenidos = new ArrayList<>();


    //no se cargan
    private List<ParametroInterno> parametrosInternos = new ArrayList<>();
    private EndPoint endPoint;

}


