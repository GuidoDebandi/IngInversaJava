package com.example.demo.model.metrics;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Modificador {

    private String visibilidad = "Package";

    private boolean isFinal = false;

    private boolean isStatic = false;


}
