package com.example.demo.translator;

import com.example.demo.translator.enumeraciones.TipoTransferencia;
import com.example.demo.translator.enumeraciones.VerbosHTTP;
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
