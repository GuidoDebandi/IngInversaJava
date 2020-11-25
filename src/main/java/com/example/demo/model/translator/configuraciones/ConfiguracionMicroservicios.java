package com.example.demo.model.translator.configuraciones;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConfiguracionMicroservicios {

    private String ipDiscovery="http://localhost";
    private int puertoDiscovery=8761;
}
