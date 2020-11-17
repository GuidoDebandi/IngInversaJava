package com.example.demo.entities.entidadesTraductor;

import com.example.demo.entities.entidadesTraductor.enumeraciones.TipoRetorno;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParametroInterno {

    private TipoRetorno tipo= TipoRetorno.Void;
}
