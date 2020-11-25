package com.example.demo.gen.translator;

import com.example.demo.gen.java.JavaParser;
import com.example.demo.gen.java.JavaParserBaseVisitor;
import com.example.demo.model.translator.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VisitorTraductorMain extends JavaParserBaseVisitor<Object> {

    private VisitorAnotation visitorAnotation = new VisitorAnotation(this);
    private VisitorFieldDeclaration visitorFieldDeclaration = new VisitorFieldDeclaration(this);
    private VisitorMethodDeclaration visitorMethodDeclaration = new VisitorMethodDeclaration(this);
    private Clasificador clasificador;
    private Retornable retornable;
    private String nombrePaquete=""; //Variable auxiliar para poder saber a que paquete pertenece la clase o la interfaz
    private String banderaAnotacion = "C";//variable auxiliar ["C" si es una clase, "A" si es atributo/relacion]
    //Almacenan temporalmente los valores necesarios para atributo/relacion
    private String esAuditableAtributo;
    private String pseudonimoAtributo;
    private String multiplicidadRelacion;

    //Se activa cuando se lee el paquete y guarda su nombre
    @Override
    public Object visitPackageDeclaration(JavaParser.PackageDeclarationContext ctx) {
        nombrePaquete = VisitorPackageDeclaration.visitPackageDeclaration(ctx);
        return super.visitPackageDeclaration(ctx);
    }
    @Override
    public Object visitTypeDeclaration(JavaParser.TypeDeclarationContext ctx) {
        //Si encuentra una clase
        if(ctx.children.contains(ctx.classDeclaration())) {
            clasificador = new Clase();
            VisitorTypeDeclaration.visitClassDeclaration(ctx.classDeclaration(), (Clase)clasificador,nombrePaquete);
        }
        //Si encuentra una interfaz
        if(ctx.children.contains(ctx.interfaceDeclaration())){
            clasificador = new Interface();
            VisitorTypeDeclaration.visitInterfaceDeclaration(ctx.interfaceDeclaration(),(Interface)clasificador,nombrePaquete);
        }
        return super.visitTypeDeclaration(ctx);
    }
    //carga los atributos y relaciones de una clase
    @Override
    public Object visitFieldDeclaration(JavaParser.FieldDeclarationContext ctx) {
        visitorFieldDeclaration.visitFieldDeclaration(ctx);
        return super.visitFieldDeclaration(ctx);
    }
    //agrega los atributos de una interface
    @Override
    public Object visitConstDeclaration(JavaParser.ConstDeclarationContext ctx) {
        VisitorConstDeclaration.visitConstDeclaration(clasificador,ctx);
        return super.visitConstDeclaration(ctx);
    }
    //carga los metodos de una clase
    @Override
    public Object visitMethodDeclaration(JavaParser.MethodDeclarationContext ctx) {
        visitorMethodDeclaration.visitClassMethodDeclaration(ctx);
        return super.visitMethodDeclaration(ctx);
    }
    //carga los metodos de una interface
    @Override
    public Object visitInterfaceMethodDeclaration(JavaParser.InterfaceMethodDeclarationContext ctx) {
        visitorMethodDeclaration.visitInterfaceMethodDeclaration(ctx);
        return super.visitInterfaceMethodDeclaration(ctx);
    }
    @Override
    public Object visitAnnotation(JavaParser.AnnotationContext ctx) {
        visitorAnotation.visitAnotaion(ctx);
        return super.visitAnnotation(ctx);
    }
    //cambia la bandera de anotaciones
    @Override
    public Object visitClassDeclaration(JavaParser.ClassDeclarationContext ctx) {
        banderaAnotacion = "A";
        return super.visitClassDeclaration(ctx);
    }
    //cambia la bandera de anotaciones
    @Override
    public Object visitInterfaceDeclaration(JavaParser.InterfaceDeclarationContext ctx) {
        banderaAnotacion = "A";
        return super.visitInterfaceDeclaration(ctx);
    }
}
