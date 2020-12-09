package com.example.demo.gen.translator;

import com.example.demo.gen.properties.*;
import com.example.demo.model.translator.configuraciones.ConfiguracionDB;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class VisitorProperties extends PropertiesBaseVisitor<Object> {
    private ConfiguracionDB configuracionDB = new ConfiguracionDB();

    @Override
    public Object visitDecl(PropertiesParser.DeclContext ctx) {

        String key = ctx.key().getText();
        switch (key){
            case "spring.datasource.url" : segmentar(ctx); break;
            case "spring.datasource.username" : configuracionDB.setDbUsuario(guardarValor(ctx)); break;
            case "spring.datasource.password" : configuracionDB.setDbContrasena(guardarValor(ctx)); break;
            case "server.port" : configuracionDB.setServerPuerto(guardarValor(ctx)); break;
        }

        return super.visitDecl(ctx);
    }

    public String guardarValor(PropertiesParser.DeclContext ctx){
        String a = null;
        if(ctx.children.contains(ctx.value())) a = ctx.value().getText();

        return a;
    }
    public void segmentar(PropertiesParser.DeclContext ctx){
        String completo = ctx.value().getText();
        String proveedor = completo.replace("jdbc:","");
        proveedor = proveedor.substring(0,proveedor.indexOf(":"));
        configuracionDB.setTipoProveedor(proveedor);
        String ip = completo.replace("jdbc:"+proveedor+"://","");
        ip = ip.substring(0,ip.indexOf(":"));
        configuracionDB.setIp(ip);
        String dbPuerto = completo.replace("jdbc:"+proveedor+"://"+ip+":","");
        dbPuerto = dbPuerto.substring(0,dbPuerto.indexOf("/"));
        configuracionDB.setDbPuerto(dbPuerto);
        String nombreDb = completo.replace("jdbc:"+proveedor+"://"+ip+":"+dbPuerto+"/","");
        configuracionDB.setDbNombre(nombreDb);
    }
}
