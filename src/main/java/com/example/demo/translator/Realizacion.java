package com.example.demo.translator;

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
