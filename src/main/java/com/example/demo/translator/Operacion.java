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
public class Operacion extends Retornable{

    private String esAbstracta;

    private List<Retornable> parametrosContenidos = new ArrayList<>();
}