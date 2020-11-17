package com.example.demo.entities.entidadesTraductor;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Clase extends Clasificador {

    private String esAbstracta;
    private String esAsociativa; //falta
    private String esAuditable; //falta
    private String tieneABM; //falta
    private List<Relacion> relacionesContenidas= new ArrayList<>();
    private List<Realizacion>  realizacionesContenidas= new ArrayList<>();

    //auxiliares
    private List<String> implementaciones = new ArrayList<>();
    private boolean esControlador = false;

}
