package com.example.demo.translator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Modificador {

    private String visibilidad;

    private String isFalse;

    private String isStatic;

}
