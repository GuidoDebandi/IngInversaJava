package com.example.demo.entities.entidadesTraductor.configuraciones;

import com.example.demo.entities.entidadesTraductor.enumeraciones.TipoReinicio;
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
