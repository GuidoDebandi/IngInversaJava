package com.example.demo.translator.configuraciones;


import com.example.demo.translator.enumeraciones.TipoReinicio;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConfiguracionDocker {

    private String nombreContenedor;
    private  int puertoInterior;
    private TipoReinicio tipoReinicio= TipoReinicio.siempre;
    private String nombreImagen;

}
