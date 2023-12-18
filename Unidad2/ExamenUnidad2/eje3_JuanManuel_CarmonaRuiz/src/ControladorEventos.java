import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ControladorEventos extends DefaultHandler {

    protected String contenido;
    protected String titulo;
    protected String semilla;
    protected String palabrasClave;
    protected String estado;

    protected boolean activo;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

    }


    public void characters(char[] ch, int start, int length) throws SAXException {
        contenido = new String(ch, start, length);

    }


    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

        if (qName.equals("Título") && !contenido.equals("null")) {
            titulo="Titulo " + contenido;
        } else if (qName.equals("Semilla") && !contenido.equals("null")) {
            semilla+="Semilla " + contenido ;
        } else if (qName.equals("Palabras_clave") && !contenido.equals("null")) {
            palabrasClave+="Palabras " + contenido ;
        } else if (qName.equals("Estado") && !contenido.equals("null")) {
            activo = false;
            if(contenido.equals("Activa")){
                estado+="Estado "+contenido;
                activo =true;
            }
        } else if (qName.equals("item") && !contenido.equals("null")) {
            if(activo = true){
                System.out.println(titulo+" "+semilla+" "+palabrasClave+" "+estado);
            }
        }

    }

}
