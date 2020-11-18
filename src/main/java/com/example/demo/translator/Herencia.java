package com.example.demo.translator;

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
