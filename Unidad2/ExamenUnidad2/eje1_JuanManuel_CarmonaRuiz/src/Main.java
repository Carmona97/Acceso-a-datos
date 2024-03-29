import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

/*
 * Realizado por Juan Manuel Carmona Ruiz
 * Fecha 23/10/2023
 * Programa que crea una lista de Jugadores y que permite añadirlos a la lista, ver los jugadores añadidos
 * y buscar un jugador en concreto*/

public class Main {
    public static void main(String[] args) {

        //Declaro las variables que se van a utilizar

        Scanner scn = new Scanner(System.in);
        int num = 0;
        String nombre = "";
        String apodo = "";
        String puesto = "";
        int dorsal = 0;
        String descripcion = "";

        File fichero = new File("Jugadores.txt");

        if(!fichero.exists()){
            try {
                fichero.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


        try {
                do {

                    //MENU

                    System.out.println("Indique que desea hacer:" +
                            "\n1.Añadir jugador" +
                            "\n2.Ver los jugadores añadidos" +
                            "\n3Buscar un jugador de la lista" +
                            "\nPulse cualquier otro numero para cerrar el programa");
                    num = scn.nextInt();
                    scn.nextLine();
                    switch (num) {

                        //Añade un jugador a la lista

                    case 1 -> {
                        System.out.println("Indique el nombre");
                        nombre = scn.nextLine();
                        System.out.println("Indique el apodo");
                        apodo = scn.nextLine();
                        System.out.println("Indique el puesto");
                        puesto = scn.nextLine();
                        System.out.println("Indique el dorsal");
                        dorsal = scn.nextInt();
                        scn.nextLine();
                        System.out.println("añadir descripcion");
                        descripcion = scn.nextLine();
                        Jugador nuevo = new Jugador(nombre, apodo, puesto, dorsal, descripcion);
                        Jugadores.jugadores.add(nuevo);
                    }

                        //Llama al arrayList de jugadores y lo imprime por pantalla

                    case 2 -> {
                        System.out.println(Jugadores.mostrarJugadores());
                    }

                    //Muestra al jugador de la lista usando parametros de entrada

                    case 3 -> {
                        System.out.println("Indique el nombre del jugador que desea buscar");
                        nombre = scn.nextLine();
                        System.out.println("Indique el apodo del jugador");
                        apodo = scn.nextLine();
                        System.out.println("Indique el dorsal del jugador");
                        dorsal = scn.nextInt();
                        System.out.println(Jugadores.buscarJugador(nombre, apodo, dorsal));
                    }
                }
            }while (num >= 1 && num <= 3);

            boolean jugadoresGuardadosCorrectamente = Jugadores.guardarJugadores(fichero);
            if(jugadoresGuardadosCorrectamente == true){
                System.out.println("Se ha guardado el archivo correctamente");
            }else{
                System.out.println("Ha habido un error al guardar los jugadores en el archivo");
            }
        } catch (InputMismatchException e) {
            e.printStackTrace();
        }





    }
}