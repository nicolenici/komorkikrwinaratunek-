package projektkrwinki;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.event.*;

public class Gra extends JFrame {
    
    ZaokraglanieBorder zapiszgre=new ZaokraglanieBorder("Zapisz gre");
    ZaokraglanieBorder zakonczgre=new ZaokraglanieBorder("Zakoncz gre");
    JLabel licznik=new JLabel(" Liczba kroków");
    JPanel panelgry=new JPanel();
    JPanel panelikonek=new JPanel();
    private static final Dimension BUTTON_SIZE = new Dimension(200, 50); 
    private BufferedImage backgroundImage;
    private Obiekt aktywnyObiekt= null;
   
    
    public Gra(String nazwa)
    {   super(nazwa);
    
        PanelStart panelStart = new PanelStart();
        panelStart.stopBackgroundMusic();
        int currentLevel = panelStart.getLevel();
        
        setResizable(false);
        setLayout(new FlowLayout());
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        setSize(screenSize);
        ImageIcon icon = new ImageIcon("C:/Users/PC/OneDrive/Pulpit/ProjektKrwinki/ikonka.jpg");
        setIconImage(icon.getImage());
        panelikonek.setLayout(new BoxLayout(panelikonek, BoxLayout.Y_AXIS));
        panelgry.setLayout(new BorderLayout());
        ustawRozmiarElementu(zapiszgre);
        ustawRozmiarElementu(zakonczgre);
        ustawRozmiarElementu(licznik);
        
        panelikonek.setOpaque(false);
        panelgry.setOpaque(false);
        
        setFocusable(true); 
        requestFocusInWindow(); 
        
        JPanel panelZtlem = new JPanel() 
        {
           private BufferedImage backgroundImage;
           {
                try 
                {
                    if(currentLevel==0 || currentLevel==1)
                    {
                    backgroundImage = ImageIO.read(new File("C:/Users/PC/OneDrive/Pulpit/ProjektKrwinki/ptapetalevel5.png"));
                    }
                    else if(currentLevel==2)
                    {
                        backgroundImage = ImageIO.read(new File("C:/Users/PC/OneDrive/Pulpit/ProjektKrwinki/tapetalevel3.png"));
                    }
                } 
                catch (Exception e) 
                {
                    System.out.println("Nie udało się załadować obrazu tła: " + e.getMessage());
                }
            }
           
        protected void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            
            if (backgroundImage != null) 
            {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        }
        };
        
        licznik.setFont(new Font("Arial", Font.BOLD, 20));
        licznik.setForeground(new Color(139, 0, 0)); 
        panelZtlem.setPreferredSize(screenSize);
        panelikonek.add(zapiszgre);
        panelikonek.add(Box.createVerticalStrut(20));
        panelikonek.add(zakonczgre);
        panelikonek.add(Box.createVerticalStrut(20));
        panelikonek.add(licznik);
        
        Mapa mapa = new Mapa(); 
        panelgry.setPreferredSize(new Dimension(1000, 600));
        panelgry.add(mapa, BorderLayout.CENTER);
       
            zapiszgre.addActionListener(e -> {
         try {
             mapa.zapiszMapeDoPliku("C:/Users/PC/OneDrive/Pulpit/ProjektKrwinki/mapka.txt");
             JOptionPane.showMessageDialog(this, "Mapa została zapisana pomyślnie!", "Sukces", JOptionPane.INFORMATION_MESSAGE);
         } catch (Exception ex) {
             JOptionPane.showMessageDialog(this, "Błąd podczas zapisywania mapy: " + ex.getMessage(), "Błąd", JOptionPane.ERROR_MESSAGE);
         }
     });
        addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                
                int x = e.getX()/20-2;
                int y = e.getY()/20-2;
               
                System.out.println("Value at (" + x + ", " + y + ") is: " );
                System.out.println(mapa.mapka[y][x] );
                    if (aktywnyObiekt != null) 
                        {
                             aktywnyObiekt.deactivate();
                        }
                   if (mapa.mapka[y][x] == 6) 
                   {
                    System.out.println("Czerwona Krwinka");
                    aktywnyObiekt = new CzerwonaKrwinka(x, y);
                   } 
                   else if (mapa.mapka[y][x] == 7) 
                   {
                        System.out.println("Biała Krwinka");
                        aktywnyObiekt = new BialaKrwinka(x, y);
                   } 
                   else if (mapa.mapka[y][x] == 8) 
                   {
                        System.out.println("Płytka Krwi");
                        aktywnyObiekt = new PlytkaKrwi(x, y);
                    } 
                   else 
                   {
                     System.out.println("Nie krwinka ani płytka");
                     return; 
                   }
                    for (KeyListener kl : getKeyListeners()) {
            removeKeyListener(kl);
        }
                   aktywnyObiekt.setPositionChangeListener((oldX, oldY, newX, newY) -> {
                if (mapa.mapka[newY][newX] != 2) {
                    newX = oldX;
                    newY = oldY;
                } else {
                    mapa.mapka[newY][newX] = mapa.mapka[oldY][oldX];
                    mapa.mapka[oldY][oldX] = 2;
                }
                aktywnyObiekt.setPosition(newX, newY);
                mapa.repaint();
                System.out.println("Map updated at: (" + newX + ", " + newY + ")");
            });
               addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                aktywnyObiekt.move(e);
            }
        });
         }
            
        });
        
        panelZtlem.add(panelgry);
        panelZtlem.add(panelikonek);
        add(panelZtlem);
         zakonczgre.addActionListener(e -> zakonczGre());
        
        revalidate();
        repaint();
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    
    private void ustawRozmiarElementu(JComponent component) 
{
    component.setPreferredSize(BUTTON_SIZE);
    component.setMaximumSize(BUTTON_SIZE);
    component.setMinimumSize(BUTTON_SIZE);
    component.setAlignmentX(Component.CENTER_ALIGNMENT); 
    component.setFont(new Font("Arial", Font.BOLD, 14));
}
    private void zakonczGre() 
    {
        dispose();
        SwingUtilities.invokeLater(() -> new Projekt()); 
    }
    
}
