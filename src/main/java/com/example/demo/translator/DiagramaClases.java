package com.example.demo.translator;


import com.example.demo.translator.configuraciones.ConfiguracionDB;
import com.example.demo.translator.configuraciones.ConfiguracionDocker;
import com.example.demo.translator.configuraciones.ConfiguracionLenguaje;
import com.example.demo.translator.configuraciones.ConfiguracionMicroservicios;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DiagramaClases {

    private List<ConfiguracionDB> configuracionesDB= new ArrayList<>();
    private List<ConfiguracionDocker> configuracionesDocker= new ArrayList<>();
    private List<ConfiguracionLenguaje> configuracionesLenguaje= new ArrayList<>();
    private List<ConfiguracionMicroservicios> configuracionesMicroservicios= new ArrayList<>();
    private Artefacto artefactoContenido;
    private List<Package> packagesContenidos = new ArrayList<>();
}
