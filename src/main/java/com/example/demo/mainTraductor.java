package com.example.demo;


import com.example.demo.translator.Clase;
import com.example.demo.translator.Clasificador;
import com.example.demo.translator.Interface;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class mainTraductor {
    public static void main(String[] args){



        /*

        //CONTROLADOR

        try{
            STGroup group= new STGroupFile("src/main/resources/templates/main.stg");
            ST plantilla = group.getInstanceOf("traducir");
            plantilla.add("paquetes",paquetes);

            String ruta = "src/main/resources/generated/resultado.xmi";
            File archivo = new File(ruta);
            BufferedWriter bw;
            bw = new BufferedWriter(new FileWriter(archivo));


            //VISTA

            bw.write(plantilla.render());
            bw.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        */
    }
}