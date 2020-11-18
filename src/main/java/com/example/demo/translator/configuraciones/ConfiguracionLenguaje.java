package com.example.demo.translator.configuraciones;


import com.example.demo.translator.enumeraciones.Lenguaje;
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
