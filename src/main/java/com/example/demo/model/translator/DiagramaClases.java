package com.example.demo.model.translator;


import com.example.demo.model.translator.configuraciones.ConfiguracionDB;
import com.example.demo.model.translator.configuraciones.ConfiguracionDocker;
import com.example.demo.model.translator.configuraciones.ConfiguracionLenguaje;
import com.example.demo.model.translator.configuraciones.ConfiguracionMicroservicios;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class DiagramaClases {

    private List<ConfiguracionDB> configuracionesDB= new ArrayList<>();
    private List<ConfiguracionDocker> configuracionesDocker= new ArrayList<>();
    private List<ConfiguracionLenguaje> configuracionesLenguaje= new ArrayList<>();
    private List<ConfiguracionMicroservicios> configuracionesMicroservicios= new ArrayList<>();
    private Artefacto artefactoContenido;
    private List<Package> packagesContenidos = new ArrayList<>();
}
