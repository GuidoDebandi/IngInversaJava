package com.example.demo.model.translator;

import com.example.demo.model.translator.enumeraciones.TipoTransferencia;
import com.example.demo.model.translator.enumeraciones.VerbosHTTP;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class EndPoint {

    private VerbosHTTP tipoVerbo = VerbosHTTP.GET;
    private TipoTransferencia tipoTransferenciaEntrada=TipoTransferencia.String;
    private TipoTransferencia tipoTransferenciaSalida=TipoTransferencia.String;
    private String nombrePublicado;

}
