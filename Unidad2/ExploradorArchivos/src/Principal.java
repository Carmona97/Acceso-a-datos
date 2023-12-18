import javax.swing.*;

public class Principal {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Ventana explorador = new Ventana();
                explorador.setVisible(true);
            }
        });
    }
}
