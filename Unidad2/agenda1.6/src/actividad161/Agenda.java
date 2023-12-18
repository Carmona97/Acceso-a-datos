package actividad161;

import java.util.ArrayList;

public class Agenda extends ArrayList<Contacto> {

    static ArrayList<Contacto> agenda = new ArrayList<Contacto>();
    public static boolean anadirContacto(Contacto nuevoContacto){
        boolean anadidoCorrectamente = agenda.add(nuevoContacto);
        return anadidoCorrectamente;
    }

    public static void mostrarContactos(){
        for (Contacto i:agenda) {
            System.out.println(i.toString());
        }
    }

    public static String buscarContacto(String nombre){
        String contactoBuscado = "";
        for(Contacto i:agenda){
            if (nombre.equals(i.getNombre())){
                contactoBuscado = i.toString();
            }
        }
        return contactoBuscado;
    }
}
