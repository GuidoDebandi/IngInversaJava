package com.example.demo.model.translator;


import com.example.demo.model.translator.enumeraciones.TipoRetorno;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ParametroInterno {

    private TipoRetorno tipo= TipoRetorno.Void;
}
