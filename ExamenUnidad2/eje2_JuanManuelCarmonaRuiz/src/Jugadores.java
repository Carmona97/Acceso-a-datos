import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

@XmlRootElement(name="jugadores")
@XmlType(propOrder = {"jugadores"})
public class Jugadores implements Serializable{
    static ArrayList<Jugador> jugadores = new ArrayList<Jugador>();

    //Añade un nuevo jugador a la lista jugadores

    public static void anadirJugador(Jugador nuevo){
        jugadores.add(nuevo);
    }

    //Devuelve los jugadores añadidos a la lista

    public static String mostrarJugadores(){
        String listaJugadores = "";
        for (Jugador i:jugadores) {
            listaJugadores += i.toString();
        }

        return listaJugadores;
    }

    //Busca un jugador que tenga el nombre, apodo y dorsal dados por el usuario

    public static String buscarJugador(String nombre,String apodo,int dorsal){
        String jugadorBuscado = "";
        for (Jugador i:jugadores) {
            if((nombre.equals(i.getNombre()) && apodo.equals(i.getApodo()) && dorsal == i.getDorsal())){
                jugadorBuscado = i.toString();
            }
        }
        return jugadorBuscado;
    }

    public static boolean guardarJugadores(File fichero){
        FileWriter writer = null;
        boolean escritura = true;
        try {
            writer = new FileWriter(fichero);
            for (Jugador i : jugadores){
                writer.write(i.toString()+"\n");
            }
        } catch (IOException e) {
            escritura = false;
        }
        try {
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return escritura;
    }

    @XmlElementWrapper(name="jugadores")
    @XmlElement(name="jugador")
    public static ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    public static void setJugadores(ArrayList<Jugador> jugadores) {
        Jugadores.jugadores = jugadores;
    }
}
