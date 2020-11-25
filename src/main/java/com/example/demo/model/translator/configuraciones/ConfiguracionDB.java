package com.example.demo.model.translator.configuraciones;


import com.example.demo.model.translator.enumeraciones.ProveedorDB;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConfiguracionDB {

   private String dbNombre;
    private String dbUsuario;
   private int dbPuerto;
    private String ip;
    private String dbContrasena;
    private int serverPuerto;
    private ProveedorDB tipoProveedor=ProveedorDB.Mysql;
}
