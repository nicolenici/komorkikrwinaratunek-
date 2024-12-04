package projektkrwinki;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.sound.sampled.*;
import java.io.IOException;

public class PanelStart extends JPanel{
    
    private static int level;
    private BufferedImage backgroundImage;
    private static final Dimension BUTTON_SIZE = new Dimension(200, 50);  
    private Clip backgroundMusicClip; 
    PanelStart()
    {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); 
        
        try 
        {
           backgroundImage = ImageIO.read(new File("C:/Users/PC/OneDrive/Pulpit/ProjektKrwinki/31215.jpg"));

        } catch (Exception e)
        {
            System.out.println("Nie udało się załadować obrazu: " + e.getMessage());
        }
        
        playBackgroundMusic();
        
        ImageIcon obrazek = new ImageIcon("C:/Users/PC/OneDrive/Pulpit/ProjektKrwinki/FONT5.png");
        
        Image img = obrazek.getImage(); 
        int newWidth = 600;  
        int newHeight = 261;
        Image scaledImg = img.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);  // Skalowanie obrazu
        ImageIcon scaledImageIcon = new ImageIcon(scaledImg);

        JLabel nazwa = new JLabel(scaledImageIcon);
        nazwa.setAlignmentX(Component.CENTER_ALIGNMENT);
        nazwa.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        nazwa.setForeground(Color.WHITE);
        
        ZaokraglanieBorder przyciskstart = new ZaokraglanieBorder("Rozpocznij gre");
        ustawRozmiarPrzycisku(przyciskstart);
        
        ZaokraglanieBorder wczytajgre  = new ZaokraglanieBorder("Wczytaj zapis gry");
        ustawRozmiarPrzycisku(wczytajgre);
        
        
        ZaokraglanieBorder wybierzpoziom  = new ZaokraglanieBorder("Wybierz poziom trudnosci");
        ustawRozmiarPrzycisku(wybierzpoziom);
        
        add(nazwa);
        add(Box.createVerticalStrut(20));
        add(przyciskstart);
        add(Box.createVerticalStrut(10)); 
        add(wybierzpoziom);
        add(Box.createVerticalStrut(10));
        add(wczytajgre);
        add(Box.createVerticalStrut(10));
        
        przyciskstart.addActionListener(e -> setLevel(0));
        wybierzpoziom.addActionListener(e -> pokazOknoPoziomow());
    }
    
     protected void paintComponent(Graphics g) 
     {
        super.paintComponent(g);

        if (backgroundImage != null) 
        {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        } else {
            System.out.println("Obraz tła nie został załadowany.");
        }
    }
     
    private void ustawRozmiarPrzycisku(JButton button) 
    {
        button.setPreferredSize(BUTTON_SIZE);
        button.setMaximumSize(BUTTON_SIZE);
        button.setMinimumSize(BUTTON_SIZE);
        button.setAlignmentX(Component.CENTER_ALIGNMENT); 
        button.setForeground(Color.RED);
        button.setFont(new Font("Arial", Font.BOLD, 14));  // Czerwona czcionka
        
        if (button instanceof ZaokraglanieBorder)
        {
        button.setPreferredSize(BUTTON_SIZE);
        }
        
    }
     
    private void startGame(int level)
    { 
        stopBackgroundMusic();
        Window window = SwingUtilities.windowForComponent(this);
        if (window != null) 
        {
            window.dispose();  
        }
        
        Gra gra = new Gra("Gra: Komórki krwi na ratunek!");
    }
    
    private void pokazOknoPoziomow()
    {
        JDialog dialog = new JDialog((Frame) null, "Wybierz poziom trudności", true);
        ImageIcon icon = new ImageIcon("C:/Users/PC/OneDrive/Pulpit/ProjektKrwinki/ikonka.jpg"); 
        dialog.setIconImage(icon.getImage());
       
        JPanel panelZtlem = new JPanel() 
        {
           private BufferedImage backgroundImage;
           {
                try 
                {
                    backgroundImage = ImageIO.read(new File("C:/Users/PC/OneDrive/Pulpit/ProjektKrwinki/tapetalevel3.png"));
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
        panelZtlem.setLayout(new BoxLayout(panelZtlem, BoxLayout.Y_AXIS));
        panelZtlem.setOpaque(true);
        
        ImageIcon tytuloknapoziomow=new ImageIcon("C:/Users/PC/OneDrive/Pulpit/ProjektKrwinki/wybierzpoziom1.png");
        JLabel wybor = new JLabel();
        wybor.setIcon(tytuloknapoziomow); 
        wybor.setAlignmentX(Component.CENTER_ALIGNMENT);
        wybor.setFont(new Font("Dialog", Font.BOLD, 24));
        wybor.setForeground(Color.WHITE); 
        
        JPanel przyciskiPanel = new JPanel();
        przyciskiPanel.setLayout(new BoxLayout(przyciskiPanel, BoxLayout.X_AXIS)); 
        przyciskiPanel.setOpaque(false); 
        JPanel panelzobrazkami=new JPanel();
        panelzobrazkami.setOpaque(false); 
        
        ZaokraglanieBorder latwy=new ZaokraglanieBorder("Łatwy");
        ustawRozmiarPrzycisku(latwy);
        ZaokraglanieBorder sredni=new ZaokraglanieBorder("Średni");
        ustawRozmiarPrzycisku(sredni);
        ZaokraglanieBorder trudny=new ZaokraglanieBorder("Trudny");
        ustawRozmiarPrzycisku(trudny);
    
        przyciskiPanel.add(latwy);
        przyciskiPanel.add(Box.createHorizontalStrut(10)); 
        przyciskiPanel.add(sredni);
        przyciskiPanel.add(Box.createHorizontalStrut(10)); 
        przyciskiPanel.add(trudny);
        
        ImageIcon zdjecie1=new ImageIcon("C:/Users/PC/OneDrive/Pulpit/ProjektKrwinki/poziom1.png");
        ImageIcon zdjecie2=new ImageIcon("C:/Users/PC/OneDrive/Pulpit/ProjektKrwinki/poziom2.png");
        ImageIcon zdjecie3=new ImageIcon("C:/Users/PC/OneDrive/Pulpit/ProjektKrwinki/poziom3.png");
        Image img1 = zdjecie1.getImage().getScaledInstance(120, 50, Image.SCALE_SMOOTH);
        Image img2 = zdjecie2.getImage().getScaledInstance(120, 60, Image.SCALE_SMOOTH);
        Image img3 = zdjecie3.getImage().getScaledInstance(120, 60, Image.SCALE_SMOOTH);

        zdjecie1 = new ImageIcon(img1);
        zdjecie2 = new ImageIcon(img2);
        zdjecie3 = new ImageIcon(img3);
        JLabel label1 = new JLabel(zdjecie1);
        JLabel label2 = new JLabel(zdjecie2);
        JLabel label3 = new JLabel(zdjecie3);
         
        panelzobrazkami.add(label1);
        panelzobrazkami.add(Box.createHorizontalStrut(90));
        panelzobrazkami.add(label2);
        panelzobrazkami.add(Box.createHorizontalStrut(70));
        panelzobrazkami.add(label3);
        panelzobrazkami.add(Box.createHorizontalStrut(0));
        
        panelZtlem.add(Box.createVerticalStrut(20)); // Odstęp od góry
        panelZtlem.add(wybor);
        panelZtlem.add(Box.createVerticalStrut(20));
        panelZtlem.add(panelzobrazkami);// Odstęp między etykietą a przyciskami
        panelZtlem.add(Box.createVerticalStrut(0));
        panelZtlem.add(przyciskiPanel);
        panelZtlem.add(Box.createVerticalStrut(70));
        
        latwy.addActionListener(e -> setLevelandClose(0,dialog)); // Ustawienie poziomu na 0 (łatwy)
        sredni.addActionListener(e -> setLevelandClose(1,dialog)); // Ustawienie poziomu na 1 (średni)
        trudny.addActionListener(e -> setLevelandClose(2,dialog));
        
        dialog.setContentPane(panelZtlem);
        dialog.setSize(800, 300);
        dialog.setLocationRelativeTo(null);
        dialog.setResizable(false);
        dialog.setVisible(true);
    };
   private void setLevelandClose(int nowylevel, JDialog dialog)
   {
     dialog.dispose();
     setLevel(nowylevel);
   }
    private void setLevel(int nowylevel) 
    {
        switch (nowylevel) 
        {
            case 0:
                System.out.println(" Wybrano level latwy");
                level=nowylevel;
                startGame(0);
                break;
            case 1:
                System.out.println(" Wybrano level sredni");
                level=nowylevel;
                startGame(1);
                break;
            case 2:
                System.out.println(" Wybrano level trudny");
                level=nowylevel;
                startGame(2);
                break;
            
        }
    }
    public int getLevel()
    {System.out.println(" Wybrano ...+"+level);
        return level;
    }

    
    private void playBackgroundMusic() 
    {
    try {
            File soundFile = new File("C:/Users/PC/OneDrive/Pulpit/ProjektKrwinki/TremLoadingloopl.wav");  
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
            backgroundMusicClip = AudioSystem.getClip(); 
            backgroundMusicClip.open(audioStream);
            backgroundMusicClip.loop(Clip.LOOP_CONTINUOUSLY);
            backgroundMusicClip.start();
    } 
    catch (UnsupportedAudioFileException | IOException | LineUnavailableException e)
    {
        System.out.println("Błąd przy ładowaniu dźwięku: " + e.getMessage());
    }
}
    public void stopBackgroundMusic() {
        if (backgroundMusicClip != null && backgroundMusicClip.isRunning()) {
            backgroundMusicClip.stop(); 
            backgroundMusicClip.close();
        }
    }

}
