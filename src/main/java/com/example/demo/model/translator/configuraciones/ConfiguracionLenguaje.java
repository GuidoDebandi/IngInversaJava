package com.example.demo.model.translator.configuraciones;


import com.example.demo.model.translator.enumeraciones.Lenguaje;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConfiguracionLenguaje {

    private String version;
    private Lenguaje lenguaje= Lenguaje.java;
}
