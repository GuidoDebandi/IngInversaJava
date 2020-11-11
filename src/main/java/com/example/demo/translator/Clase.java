package com.example.demo.translator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Clase extends Clasificador{


    private String esAbstracta;

    private String esAuditable;

    private String tieneABM;

    private String esAsociativa;

    private List<Realizacion> realizacionesContenidas = new ArrayList<>();

    private List<Relacion> relacionesContenidas = new ArrayList<>();
}
