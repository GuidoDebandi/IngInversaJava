package com.example.demo.model.translator.configuraciones;


import com.example.demo.model.translator.enumeraciones.Lenguaje;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ConfiguracionLenguaje {

    private String version;
    private String lenguaje = "java";
}
