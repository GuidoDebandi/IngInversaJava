package com.example.demo.model.translator.configuraciones;


import com.example.demo.model.translator.enumeraciones.ProveedorDB;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ConfiguracionDB {

    private String dbNombre;
    private String dbUsuario;
    private String dbPuerto;
    private String ip;
    private String dbContrasena;
    private String serverPuerto;
    private String tipoProveedor;
}
