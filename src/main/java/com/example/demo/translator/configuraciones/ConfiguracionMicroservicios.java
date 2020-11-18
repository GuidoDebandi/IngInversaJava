package com.example.demo.translator.configuraciones;

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
