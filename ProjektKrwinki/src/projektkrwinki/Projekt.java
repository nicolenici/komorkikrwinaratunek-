package projektkrwinki;
import javax.swing.*;
import java.awt.*; 

public class Projekt extends JFrame{

    public Projekt()
    {
        setTitle("Projekt Krwinki");
        ImageIcon icon = new ImageIcon("C:/Users/PC/OneDrive/Pulpit/ProjektKrwinki/ikonka.jpg");  // Zmień ścieżkę na własną
        setIconImage(icon.getImage());
        setSize(900, 560);
        setLayout(new BorderLayout());
        PanelStart panelStart = new PanelStart();
        add(panelStart, BorderLayout.CENTER);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
    }
    
    public static void main(String[] args) {
        
        new Projekt(); 
    }
    
}
