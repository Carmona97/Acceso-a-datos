import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        File videojuegos = new File("videojuegos.xml");
        ControladorEventos controlador = new ControladorEventos();
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = factory.newSAXParser();
            saxParser.parse(videojuegos, controlador);
        }catch (ParserConfigurationException | SAXException | IOException e){
            e.printStackTrace();
        }
    }
}