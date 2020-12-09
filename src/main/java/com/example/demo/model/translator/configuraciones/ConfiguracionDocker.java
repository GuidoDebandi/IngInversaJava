package com.example.demo.model.translator.configuraciones;


import com.example.demo.model.translator.enumeraciones.TipoReinicio;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ConfiguracionDocker {

    private String nombreContenedor;
    private String puertoInterior;
    private String tipoReinicio;
    private String nombreImagen;

}
