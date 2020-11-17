package com.example.demo.entities.entidadesTraductor;

import com.example.demo.entities.entidadesTraductor.enumeraciones.TipoTransferencia;
import com.example.demo.entities.entidadesTraductor.enumeraciones.VerbosHTTP;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EndPoint {

    private VerbosHTTP tipoVerbo = VerbosHTTP.GET;
    private TipoTransferencia tipoTransferenciaEntrada=TipoTransferencia.String;
    private TipoTransferencia tipoTransferenciaSalida=TipoTransferencia.String;
    private String nombrePublicado;

}
