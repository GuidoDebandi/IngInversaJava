package com.example.demo.gen.translator;


import com.example.demo.gen.pomxml.XMLParser;
import com.example.demo.gen.pomxml.XMLParserBaseVisitor;
import com.example.demo.model.translator.Artefacto;
import com.example.demo.model.translator.configuraciones.ConfiguracionLenguaje;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VisitorPom extends XMLParserBaseVisitor<Object> {
    private Artefacto artefacto = new Artefacto();
    private ConfiguracionLenguaje configuracionLenguaje = new ConfiguracionLenguaje();

    @Override
    public Object visitElement(XMLParser.ElementContext ctx) {
        if(ctx.Name().toString().equals("[project, project]")){
            cargarArtefacto(ctx.content());

        }
        return super.visitElement(ctx);
    }
    public void cargarArtefacto(XMLParser.ContentContext ctx){

        for (XMLParser.ElementContext elementContext : ctx.element() ) {
            String nombre = elementContext.Name().toString();
            switch (nombre){
                case "[groupId, groupId]": artefacto.setGrupoId(guardar(elementContext));break;
                case "[artifactId, artifactId]": artefacto.setArtefactoId(guardar(elementContext)); ;break;
                case "[version, version]": artefacto.setVersion(guardar(elementContext));break;
                case "[name, name]": artefacto.setNombre(guardar(elementContext));break;
                case "[description, description]": artefacto.setDescripcion(guardar(elementContext));break;
                case "[properties, properties]": configuracionLenguaje.setVersion(elementContext.content().element(0).content().getText());
                
            }
        }

    }
    public String guardar(XMLParser.ElementContext ctx ){
        return ctx.content().getText();
    }
}
