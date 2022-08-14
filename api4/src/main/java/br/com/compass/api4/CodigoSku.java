package br.com.compass.api4;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Random;

@Component
public class CodigoSku {

    public String skuId(){
        Random random = new Random();
        ArrayList<Integer> numeros = new ArrayList<Integer>();

        while (numeros.size() < 6) {
            int a = random.nextInt(49)+1;

            if (!numeros.contains(a)) {
                numeros.add(a);
            }
        }
        String nome = "mar";

        return nome+numeros;
    }
}
