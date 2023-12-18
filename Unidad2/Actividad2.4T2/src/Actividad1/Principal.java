package Actividad1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) throws IOException {
        Scanner scn = new Scanner(System.in);

        File fichero = new File("blocNotas.txt");  // Se puede modificar la ruta para crear nuevos ficheros de texto
        String sobreescribir = "";
        String linea = "";
        int numeroLinea = 0;

        if (fichero.exists()) {
            System.out.println("Desea sobreescribir el archivo? S/N");
            sobreescribir = scn.nextLine();
        }

        if ("N".equalsIgnoreCase(sobreescribir)) {
            FileWriter machacharFichero = new FileWriter(fichero);

            while (!"Exit".equalsIgnoreCase(linea)) {
                System.out.println("Ingrese una oración al fichero de texto. Escriba 'Exit' para salir.");
                linea = scn.nextLine();
                numeroLinea++;
                if (!"Exit".equalsIgnoreCase(linea)) {
                    machacharFichero.write(numeroLinea + " " + linea + "\n");
                }
            }

            machacharFichero.close();
        }else if ("S".equalsIgnoreCase(sobreescribir)){
            FileWriter sobreescribirFichero = new FileWriter(fichero,true);
            Scanner leerLineas = new Scanner(fichero);
            while (leerLineas.hasNextLine()){
                leerLineas.nextLine();
                numeroLinea++;
            }
            leerLineas.close();
            while (!"Exit".equalsIgnoreCase(linea)) {
                System.out.println("Ingrese una oración al fichero de texto. Escriba 'Exit' para salir.");
                linea = scn.nextLine();
                numeroLinea++;
                if (!"Exit".equalsIgnoreCase(linea)) {
                    sobreescribirFichero.write(numeroLinea + " " + linea + "\n");
                }
            }
            sobreescribirFichero.close();
        }

        scn.close();
    }

}
