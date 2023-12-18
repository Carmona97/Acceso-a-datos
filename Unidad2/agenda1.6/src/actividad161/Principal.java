package actividad161;

import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

import static actividad161.Agenda.agenda;


public class Principal {
    public static void main(String[] args) {

        Scanner scn = new Scanner(System.in);
        int num = 1;
        String nombre = "";
        String apellido = "";
        String numeroMovil = "";
        String numeroTrabajo = "";
        String numeroCasa = "";

        try {
            while (num > 0 && num <= 4) {
                System.out.println("Indique que desea hacer:\n" +
                        "1.Añadir contacto\n" +
                        "2.Mostrar contactos\n" +
                        "3.Buscar contacto\n" +
                        "4.Convertir el archivo de la agenda\n" +
                        "Pulse cualquier otro número para finalizar el programa");
                num = scn.nextInt();
                switch (num) {

                    case 1 -> {
                        scn.nextLine();
                        System.out.println("Indique su nombre");
                        nombre = scn.nextLine();

                        System.out.println("Indique su apellido");
                        apellido = scn.nextLine();
                        System.out.println("Indique su numero de movil");
                        numeroMovil = scn.nextLine();
                        System.out.println("Indique su numero de trabajo");
                        numeroTrabajo = scn.nextLine();
                        System.out.println("Indique su numero de casa");
                        numeroCasa = scn.nextLine();

                        Contacto nuevo = new Contacto(nombre, apellido, numeroMovil,numeroTrabajo,numeroCasa);
                        Agenda.anadirContacto(nuevo);

                    }
                    case 2 -> {
                        Agenda.mostrarContactos();
                    }
                    case 3 -> {
                        scn.nextLine();
                        System.out.println("Que nombre de contacto desea buscar?");
                        String nombreContacto = scn.nextLine();
                        System.out.println(Agenda.buscarContacto(nombreContacto));

                    }
                }
            }
        } catch (InputMismatchException e) {
            e.printStackTrace();
        }

        try(ObjectOutputStream objetoOut = new ObjectOutputStream(new FileOutputStream("contactos.obj"))) {
            objetoOut.writeObject(agenda);
        }catch(IOException e) {
            e.printStackTrace();
        }

    }

}
