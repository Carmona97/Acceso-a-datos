import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ControladorEventos  extends DefaultHandler {

    protected String contenido;


    public void startElement(String uri, String nombre,String apodo,String puesto, int dorsal, String descripcion, String qName, Attributes atributos) throws SAXException {

        if (qName.equals("jugador")) {

            System.out.println(atributos.getValue("nombre")+" "+atributos.getValue("apodo")+" "+atributos.getValue("puesto")+" "+atributos.getValue("dorsal")+" "+atributos.getValue("descripcion"));
        }

    }


    public void characters(char[] ch, int start, int length) throws SAXException{
        contenido = new String(ch, start, length);

    }


    public void endElement(String uri, String localName, String qName) throws SAXException{

        if(!qName.isBlank()){
            if(qName.equals("jugador")){
                System.out.println(" "+qName+": "+contenido);
            }
        }
    }

}
