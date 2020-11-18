package com.example.demo.translator;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Artefacto {

    private String nombre="Demo";
    private String grupoId="com.example";
    private String artefactoId="demo";
    private String descripcion="Demo project";
    private String version="0.0.1-SNAPSHOT";

}
