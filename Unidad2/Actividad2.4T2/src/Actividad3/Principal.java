package Actividad3;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) throws IOException {

        File fichero = new File("ordenado.txt");

        Scanner lectorFichero = new Scanner(fichero);

        int lineas = 0;

        String lineaLeida = "";

        while(lectorFichero.hasNextLine()){
            lineaLeida =lectorFichero.nextLine();
            System.out.println(lineaLeida);
            lineas++;

            if(lineas % 23 == 0){
                System.out.println("Han pasado 23 lineas. Pulse espacio+intro para seguir leyendo");
                System.in.read();
            }
        }

        lectorFichero.close();

    }
}
