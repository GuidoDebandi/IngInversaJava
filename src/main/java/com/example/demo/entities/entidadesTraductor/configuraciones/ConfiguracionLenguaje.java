package com.example.demo.entities.entidadesTraductor.configuraciones;

import com.example.demo.entities.entidadesTraductor.enumeraciones.Lenguaje;
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
