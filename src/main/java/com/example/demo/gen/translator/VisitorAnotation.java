package com.example.demo.gen.translator;

import com.example.demo.gen.java.JavaParser;
import com.example.demo.model.translator.Clase;
import com.example.demo.model.translator.enumeraciones.Multiplicidad;

public class VisitorAnotation {

    private VisitorTraductorMain visitorTraductorMain;
    public VisitorAnotation (VisitorTraductorMain visitorTraductorMain){
        this.visitorTraductorMain = visitorTraductorMain;
    }
    public void visitAnotaion(JavaParser.AnnotationContext ctx){
        String anotacion = ctx.qualifiedName().getText();
        //si es anotacion de clase
        if(visitorTraductorMain.getBanderaAnotacion().equals("C")){
            switch (anotacion){
                case "Audited": ((Clase)visitorTraductorMain.getClasificador()).setEsAuditable("esAuditable");
                                break;
                case "RestController": ((Clase)visitorTraductorMain.getClasificador()).setEsControlador(true);
                                break;
            }
        //si es anotacion de atributo/relacion
        }else{
            switch (anotacion){
                case "OneToOne": visitorTraductorMain.setMultiplicidadRelacion(Multiplicidad.oneToOne.toString());
                                 break;
                case "OneToMany": visitorTraductorMain.setMultiplicidadRelacion(Multiplicidad.oneToMany.toString());
                                 break;
                case "ManyToOne": visitorTraductorMain.setMultiplicidadRelacion(Multiplicidad.manyToOne.toString());
                                 break;
                case "ManyToMany": visitorTraductorMain.setMultiplicidadRelacion(Multiplicidad.manyToMany.toString());
                                 break;
            }
            switch (anotacion){
                case "Audited" : visitorTraductorMain.setEsAuditableAtributo("Es Auditable");
                                break;
                case "Column" : setearPseudonimo(ctx);
                                break;
            }
        }
    }
    public void setearPseudonimo(JavaParser.AnnotationContext ctx){
        if(ctx.children.contains(ctx.elementValuePairs())){
            visitorTraductorMain.setPseudonimoAtributo(ctx.elementValuePairs().getText().replaceAll("\"","").replaceAll("name","").replaceAll("=",""));
        }
    }
}

