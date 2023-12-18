package actividad_231;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Principal {
    public static void main(String[] args) {
        FileInputStream fIn = null;
        FileOutputStream fOut = null;
        byte[] datos = new byte[128];
        int bytesLeidos = 0;

        try {
            fIn = new FileInputStream("C:\\Users\\Juanma\\Desktop\\entrada.txt");
            fOut = new FileOutputStream("C:\\Users\\Juanma\\Desktop\\salida.txt");

            while ((bytesLeidos = fIn.read(datos))  != -1) {
                fOut.write(datos);
            }
            fIn.close();
            fOut.close();
        } catch (FileNotFoundException e) {
            System.out.println("No se pudo abrir el fichero");
        } catch (IOException e) {
            System.out.println("Error de entrada/salida");
        }
    }
}