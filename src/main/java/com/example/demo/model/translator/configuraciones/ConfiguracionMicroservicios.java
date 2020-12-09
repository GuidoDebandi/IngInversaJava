package com.example.demo.model.translator.configuraciones;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ConfiguracionMicroservicios {

    private String ipDiscovery;
    private String puertoDiscovery;
}
