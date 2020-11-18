package com.example.demo.translator;


import com.example.demo.translator.enumeraciones.TipoRetorno;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParametroInterno {

    private TipoRetorno tipo= TipoRetorno.Void;
}
