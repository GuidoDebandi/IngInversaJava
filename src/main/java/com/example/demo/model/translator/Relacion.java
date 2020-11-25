package com.example.demo.model.translator;


import com.example.demo.model.translator.enumeraciones.TipoABM;
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
public class Relacion{

    private String nombre;
    private String esFinal;
    private String esStatic;
    private String visibilidad;
    private Clase origen;
    private Clasificador destino;



    private String tipo; //falta
    private String multiplicidad;


    private TipoABM tipoABM=TipoABM.sinRepresentacion;
    private List<Clase> clasesAsociativasContenidas=new ArrayList<>(); //falta

    //Auxiliares
    private String nombreClasificadorDestino;


}
