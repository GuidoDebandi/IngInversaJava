package com.example.demo.translator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Paquete {

    private String nombre;

    private List<Clase> clasesContenidas = new ArrayList<>();

    private List<Interface> interfacesContenidas = new ArrayList<>();
}
