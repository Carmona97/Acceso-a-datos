package Actividad2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) throws IOException {

        File fichero1 = new File("nombres1.txt");
        File fichero2 = new File("nombres2.txt");
        File ordenado = new File("ordenado.txt");

        FileWriter escribirNombres = new FileWriter(ordenado);

        Scanner lectorNombres1 = new Scanner(fichero1);
        Scanner lectorNombres2 = new Scanner(fichero2);

        ArrayList<String> listaNombres1 = new ArrayList<String>();
        ArrayList<String> listaNombres2 = new ArrayList<String>();

        listaNombres1 = ordenarNombres(lectorNombres1,listaNombres1);
        listaNombres2 = ordenarNombres(lectorNombres2,listaNombres2);

        for(String i : listaNombres2){
            listaNombres1.add(i);
        }

        Collections.sort(listaNombres1);

        for(String i : listaNombres1){
            escribirNombres.write(i+"\n");
        }
        escribirNombres.close();
    }

    public static ArrayList<String> ordenarNombres(Scanner lectorNombres,ArrayList<String> listaNombres){
        while (lectorNombres.hasNextLine()){
            listaNombres.add(lectorNombres.nextLine());
        }
        lectorNombres.close();
        Collections.sort(listaNombres);
        return listaNombres;
    }
}
