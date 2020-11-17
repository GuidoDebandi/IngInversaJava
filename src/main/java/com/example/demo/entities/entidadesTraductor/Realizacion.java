package com.example.demo.entities.entidadesTraductor;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Realizacion {

    private Clase origen;
    private Interface destino;
}
