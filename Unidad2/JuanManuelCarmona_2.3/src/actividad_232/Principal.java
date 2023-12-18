package actividad_232;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

public class Principal {
    public static void main(String[] args) {
        FileInputStream fIn = null;
        byte[] datos = new byte[2];
        byte[] png = {(byte) 0x89, 0x50};
        byte[] bmp = {(byte) 0x42, 0x4D};
        byte[] gif = {(byte) 0x47, 0x49};
        byte[] ico = {(byte) 0x00, 0x00};
        byte[] jpeg = {(byte) 0xFF, (byte)0xD8};



        try{
            fIn = new FileInputStream("C:\\Users\\Juanma\\Desktop\\lisboa.png");
            fIn.read(datos);
            if(Arrays.equals(datos,png)){
                System.out.println("La imagen esta en formato png");
            } else if (Arrays.equals(datos,gif)) {
                System.out.println("La imagen esta en formato gif");
            } else if (Arrays.equals(datos,bmp)) {
                System.out.println("La imagen esta en formato bmp");
            } else if (Arrays.equals(datos,ico)) {
                System.out.println("La imagen esta en formato ico");
            } else if (Arrays.equals(datos,jpeg)) {
                System.out.println("La imagen esta en formato jpeg");
            }

            fIn.close();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
