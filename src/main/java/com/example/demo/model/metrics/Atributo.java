package com.example.demo.model.metrics;

import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Atributo {

    private Modificador modificador;

    private String nombre;

    private String tipo;

}
