import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.util.ArrayList;

public class CambiadorFormato implements Serializable {

    private String ficheroRuta;
    private String xmlRuta;

    public CambiadorFormato(String ficheroRuta, String xmlRuta) {
        this.ficheroRuta = ficheroRuta;
        this.xmlRuta = xmlRuta;
    }

    public String xmlGuardado(ArrayList<Jugador> jugadores) throws IOException {
        FileWriter writer = new FileWriter(xmlRuta);
        String xmlGuardado = "El XML ha sido guardado correctamente";

        try {
            JAXBContext contexto = JAXBContext.newInstance(Jugadores.class);
            Marshaller marshaller = contexto.createMarshaller();
            marshaller.marshal(jugadores, writer);
        } catch (JAXBException e) {
            xmlGuardado = "No se ha podido guardar correctamente el XML";
        }
        return xmlGuardado;
    }

    public Jugadores cargarXml() {
        Jugadores jugadores = null;
        try {

            JAXBContext context = JAXBContext.newInstance(Jugadores.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            jugadores = (Jugadores) ((Unmarshaller) unmarshaller).unmarshal(new File(xmlRuta));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return jugadores;
    }
    
    public String guardarArchivo() {
        String objGuardado = "Guardado correctamente";
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ficheroRuta))) {
            oos.writeObject(Jugadores.jugadores);
        } catch (IOException exception) {
            objGuardado = "Ha habido algun error de guardado";
        }
        return objGuardado;
    }

    public Jugadores cargarArchivo() {
        Jugadores jugadores = new Jugadores();
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ficheroRuta))) {
            oos.writeObject(jugadores);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jugadores;
    }
}
