package actividad_233;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Principal {
    public static void main(String[] args) {
        FileInputStream fIn = null;
        byte datos[] = new byte[54];
        try {
            fIn = new FileInputStream("C:\\Users\\Juanma\\Desktop\\lisboa.bmp");
            fIn.read(datos,0,2); //lee la firma del fichero
            fIn.read(datos,2,4); //Tamaño del fichero en bytes
            fIn.read(datos,18,4); //Ancho de la imagen en pixeles
            fIn.read(datos,22,4); //Alto de la imagen en pixeles
            fIn.read(datos,30,4); //Compresion del fichero
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
