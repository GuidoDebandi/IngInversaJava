package com.example.demo.entities.entidadesTraductor.configuraciones;

import com.example.demo.entities.entidadesTraductor.enumeraciones.ProveedorDB;
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
