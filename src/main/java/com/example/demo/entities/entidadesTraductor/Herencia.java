package com.example.demo.entities.entidadesTraductor;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Herencia {

    private Clasificador origen;
    private Clasificador destino;
}
