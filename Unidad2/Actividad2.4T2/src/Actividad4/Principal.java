package Actividad4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        File fichero = new File("frases.txt");

        if(fichero.exists()){
            System.out.println("Ingrese la cadena a buscar:");
            String cadenaTexto = scn.nextLine(); //"Todos somos aficionados. La vida es tan corta que no da para más." Esta es la cadena que se repite en el archivo

            try {
                BufferedReader lectorFichero = new BufferedReader(new FileReader(fichero));

                String linea;
                int numeroLinea = 0;

                while ((linea = lectorFichero.readLine()) != null) {
                    numeroLinea++;

                    if (linea.contains(cadenaTexto)) {
                        System.out.println("Linea " + numeroLinea + ": " + linea);
                    }
                }

                lectorFichero.close();
                scn.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            System.out.println("El fichero no existe");
        }

    }
}
