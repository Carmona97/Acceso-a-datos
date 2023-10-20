import javax.swing.*;
import java.io.File;

public class ExploradorArchivos extends File {

    private String unidad;
    private String directorios;
    private String archivos;

    public ExploradorArchivos(String pathname, String unidad, String directorios, String archivos) {
        super(pathname);
        this.unidad = unidad;
        this.directorios = directorios;
        this.archivos = archivos;
    }

    public static String[] mostrarUnidades(){
        File[] unidades = File.listRoots();
        String[] leerUnidad = new String[unidades.length];

        for(int i = 0; i< unidades.length;i++){
            leerUnidad[i] = unidades[i].getAbsolutePath()+" ";

        }
        return leerUnidad;
    }

    public static String[] mostrarDirectorios(JComboBox unidadesBox){
        String unidadSeleccionada = unidadesBox.getSelectedItem().toString();
        File ficheros = new File(unidadSeleccionada);
        File[] ficherosExistentes = ficheros.listFiles();
        String[] directorios = new String[ficherosExistentes.length];
        for (int i = 0; i<ficherosExistentes.length;i++){
            if (ficherosExistentes[i].isDirectory()){
                directorios[i]=ficherosExistentes[i].toString();
            }
        }
        return directorios;
    }

    public static String[] mostrarArchivos(JComboBox directoriosBox){
        String directorioSeleccionado =  directoriosBox.getSelectedItem().toString();
        File directorio = new File(directorioSeleccionado);
        File[] directoriosExistentes = directorio.listFiles();
        String[] archivos = new String[directoriosExistentes.length];
        for (int i= 0;i<directoriosExistentes.length;i++){
            if (directoriosExistentes[i].isFile()){
                archivos[i]=directoriosExistentes[i].toString();
            }
        }
        return archivos;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public String getDirectorios() {
        return directorios;
    }

    public void setDirectorios(String directorios) {
        this.directorios = directorios;
    }

    public String getArchivos() {
        return archivos;
    }

    public void setArchivos(String archivos) {
        this.archivos = archivos;
    }
}
