package com.antrl.examples;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.Builder;

@Data
@Builder
public class Persona<A, B> extends Animal implements JpaMock<Uno, Dos>, JpaTest {


    public void hacer() {
        do{

        }while(true);

        while(false){

        }
    }


}