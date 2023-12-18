import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class Ventana extends JFrame{
    private JPanel pnlMain;
    private JTextArea archivoTxt;
    private JButton directoriosBtn;
    private JButton unidadesBtn;
    private JTextArea unidadesTxt;
    private JButton archivosButton;
    private JComboBox unidadesBox;
    private JComboBox directoriosBox;

    public Ventana(){
        setTitle("Explorador de archivos");
        setSize(600,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(pnlMain);

        unidadesBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] unidades = new String[10];
                unidades = ExploradorArchivos.mostrarUnidades();
                for (int i=0;i<unidades.length;i++){
                    unidadesBox.addItem(unidades[i]);
                }
            }
        });
        directoriosBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] directorios = new String[100];
                if(directoriosBox.getItemCount() != 0 ){
                    directoriosBox.removeAllItems();
                }
                directorios = ExploradorArchivos.mostrarDirectorios(unidadesBox);
                for (int i =0 ;i<directorios.length;i++){
                    directoriosBox.addItem(directorios[i].toString());
                }

            }
        });
        archivosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] archivos = new String[100];
                archivoTxt.setText("");
                archivos = ExploradorArchivos.mostrarArchivos(directoriosBox);
                for (int i =0 ;i<archivos.length;i++){
                    archivoTxt.setText(archivos[i]);
                }
            }
        });

    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
