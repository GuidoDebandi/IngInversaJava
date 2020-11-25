package com.example.demo.gen.translator;

import com.example.demo.gen.java.JavaParser;

public class VisitorPackageDeclaration {
    public static String visitPackageDeclaration(JavaParser.PackageDeclarationContext ctx){
        String x = ctx.qualifiedName().IDENTIFIER().toString();
        x = x.replace("]", "").replaceAll("\\[", "");
        return x;
    }
}
