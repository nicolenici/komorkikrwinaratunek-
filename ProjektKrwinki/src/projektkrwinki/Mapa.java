package projektkrwinki;
import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;

public class Mapa extends JPanel{
    private static final int SZEROKOSC = 49;
    private static final int WYSOKOSC = 30;
    public int[][] mapka;
    private CzerwonaKrwinka[] czkrwinki;
    private BialaKrwinka[] bkrwinki;
    private PlytkaKrwi[] plytkakrwi;
    private Wirus[] wirus;
    private Rana[] rana;
    private Bakteria[] bakteria;
    private BrakTlenu[] braktlenu;
     
       
    public Mapa()
    {   
        PanelStart panelStart = new PanelStart();
        panelStart.stopBackgroundMusic();
        int currentLevel = panelStart.getLevel();
        setOpaque(false);
        System.out.println(currentLevel);
        mapka = new int[WYSOKOSC][SZEROKOSC];
        inicjalizujMape();
        int liczbaKrwinekCzerownych=0;
        int liczbaKrwinekBialych=0;
        int liczbaPlytekKrwi=0;
        int liczbaWirusow=0;
        int liczbaBakterii=0;
        int liczbaRan=0;
        int liczbaBrakTlenu=0;
        
        if (currentLevel == 0) 
        {
            liczbaKrwinekCzerownych = 20;
            liczbaKrwinekBialych=6;
            liczbaPlytekKrwi=9;
            liczbaWirusow=1;
            liczbaBakterii=1;
            liczbaRan=1;
            liczbaBrakTlenu=1;
        }
        else if (currentLevel == 1) 
        {
            liczbaKrwinekCzerownych = 18;
            liczbaKrwinekBialych=5;
            liczbaPlytekKrwi=8;
            liczbaWirusow=2;
            liczbaBakterii=2;
            liczbaRan=2;
            liczbaBrakTlenu=2;
        }
        else if (currentLevel == 2) 
        {
            liczbaKrwinekCzerownych = 15; 
            liczbaKrwinekBialych=4;
            liczbaPlytekKrwi=6;
            liczbaWirusow=3;
            liczbaBakterii=3;
            liczbaRan=3;
            liczbaBrakTlenu=3;
        }
        inicjalizujObiekty(liczbaKrwinekCzerownych, liczbaKrwinekBialych, liczbaPlytekKrwi, liczbaWirusow,liczbaBakterii, liczbaRan, liczbaBrakTlenu);
        /*addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Get mouse coordinates in terms of map
                int x = e.getX()/20;
                int y = e.getY()/20;
               
                System.out.println("Value at (" + x + ", " + y + ") is: " );
                System.out.println(mapka[y][x] );
               if(mapka[y][x]==6 || mapka[y][x]==7 ||mapka[y][x]==8 )
               {
                   System.out.println("Krwinka " );
                   
               }
               else
               {
                System.out.println(" nie Krwinka " );
                }                  
            }
            
        });*/
    }
    
    private void inicjalizujMape() {
        int[][] mapaDane={
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,0,0,0,0},
            {0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,2,2,2,2,2,2,1,0,0,0,0},
            {0,0,0,1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,2,2,2,2,2,2,1,0,0,0,0},
            {0,0,0,3,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1,0,0,0,0,1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1,0,0,0,0},
            {0,0,0,1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1,0,0,0,0,1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1,0,0,0,0},
            {0,0,0,1,1,1,1,1,1,1,1,2,2,2,2,2,2,2,2,2,1,0,0,0,0,1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,1,2,2,2,2,2,2,2,2,2,1,0,0,0,0,1,1,1,1,1,1,1,2,2,2,2,2,2,1,1,1,1,1,1,1,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,1,2,2,2,2,2,2,2,2,2,1,0,0,0,0,0,0,0,0,0,0,1,2,2,2,2,2,2,1,0,0,0,0,0,0,0,0,0,0},
            {0,1,1,1,1,1,1,1,1,1,1,2,2,2,2,2,2,2,2,2,1,1,1,1,1,1,1,1,1,1,1,1,2,2,2,2,2,2,1,1,1,1,1,1,1,1,1,1,0},
            {0,1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1,0},
            {0,1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1,0},
            {0,1,4,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,5,1,0},
            {0,1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1,0},
            {0,1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1,0},
            {0,1,1,1,1,1,1,2,2,2,2,2,2,2,2,1,1,1,1,1,1,3,1,1,1,1,1,1,1,1,1,1,1,2,2,2,2,2,2,2,2,2,1,1,1,1,1,1,0},
            {0,0,0,0,0,0,1,2,2,2,2,2,2,2,2,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,2,2,2,2,2,2,2,2,2,1,0,0,0,0,0,0},
            {0,0,0,0,0,0,1,2,2,2,2,2,2,2,2,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,2,2,2,2,2,2,2,2,2,1,0,0,0,0,0,0},
            {0,1,1,1,1,1,1,2,2,2,2,2,2,2,2,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,1,2,2,2,2,2,2,2,2,2,1,0,0,0,0,0,0},
            {0,1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1,0,0,0,0,0,0,0,0,0,0,0,0,3,2,2,2,2,2,2,2,2,2,1,0,0,0,0,0,0},
            {0,1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,3,0,0,0,0,0,0,0,0,0,0,0,0,1,2,2,2,2,2,2,2,2,2,1,0,0,0,0,0,0},
            {0,1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1,0,0,0,1,1,1,1,1,1,0,0,0,1,2,2,2,2,2,2,2,2,2,1,0,0,0,0,0,0},
            {0,1,1,1,2,2,2,2,2,1,1,1,1,1,1,1,1,1,1,1,0,0,0,1,2,2,2,2,1,0,0,0,1,2,2,2,2,2,2,2,2,2,3,0,0,0,0,0,0},
            {0,0,0,1,2,2,2,2,2,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,2,2,2,2,1,0,0,0,1,2,2,2,2,2,2,2,2,2,1,0,0,0,0,0,0},
            {0,0,0,1,2,2,2,2,2,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,2,2,2,2,1,1,1,1,1,2,2,2,2,2,2,2,2,2,1,0,0,0,0,0,0},
            {0,0,0,1,2,2,2,2,2,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1,0,0,0,0,0,0},
            {0,0,0,3,2,2,2,2,2,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1,0,0,0,0,0,0},
            {0,0,0,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,1,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
        };
        for (int y = 0; y < WYSOKOSC; y++) {
            for (int x = 0; x < SZEROKOSC; x++) {
                mapka[y][x] = mapaDane[y][x];
                
            }
        }
    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        rysujMape(g);
    }
    
    private void rysujMape(Graphics g) {
        int szerokoscKomorki = 20;  
        int wysokoscKomorki = 20;
        
        for (int y = 0; y < WYSOKOSC; y++) {
            for (int x = 0; x < SZEROKOSC; x++) {
                Color kolor = getKolorDlaKomorki(mapka[y][x]);
                g.setColor(kolor);
                g.fillRect(x * szerokoscKomorki, y * wysokoscKomorki, szerokoscKomorki, wysokoscKomorki);
               
                if (mapka[y][x] == 4) 
         {
                ImageIcon imageIcon = new ImageIcon("C:/Users/PC/OneDrive/Pulpit/ProjektKrwinki/dopluc.png");
                Image krwinkaImage = imageIcon.getImage();
                g.setColor(Color.RED);
                g.fillRect(x * szerokoscKomorki, y * wysokoscKomorki, szerokoscKomorki, wysokoscKomorki);
                g.drawImage(krwinkaImage, x * szerokoscKomorki, y * wysokoscKomorki, szerokoscKomorki, wysokoscKomorki, this);
         }
                if (mapka[y][x] == 5) 
         {
                ImageIcon imageIcon = new ImageIcon("C:/Users/PC/OneDrive/Pulpit/ProjektKrwinki/zpluc.png");
                Image krwinkaImage = imageIcon.getImage();
                g.setColor(Color.RED);
                g.fillRect(x * szerokoscKomorki, y * wysokoscKomorki, szerokoscKomorki, wysokoscKomorki);
                g.drawImage(krwinkaImage, x * szerokoscKomorki, y * wysokoscKomorki, szerokoscKomorki, wysokoscKomorki, this);
         }
                 if (mapka[y][x] == 6) 
         {
                ImageIcon imageIcon = new ImageIcon("C:/Users/PC/OneDrive/Pulpit/ProjektKrwinki/krwinkac.png");
                Image krwinkaImage = imageIcon.getImage();
                g.setColor(Color.RED);
                g.fillRect(x * szerokoscKomorki, y * wysokoscKomorki, szerokoscKomorki, wysokoscKomorki);
                g.drawImage(krwinkaImage, x * szerokoscKomorki, y * wysokoscKomorki, szerokoscKomorki, wysokoscKomorki, this);
         }
                if (mapka[y][x] == 7) 
         {
                ImageIcon imageIcon = new ImageIcon("C:/Users/PC/OneDrive/Pulpit/ProjektKrwinki/krwinkabiala.png");
                Image krwinkaImage = imageIcon.getImage();
                g.setColor(Color.RED);
                g.fillRect(x * szerokoscKomorki, y * wysokoscKomorki, szerokoscKomorki, wysokoscKomorki);
                g.drawImage(krwinkaImage, x * szerokoscKomorki, y * wysokoscKomorki, szerokoscKomorki, wysokoscKomorki, this);
         }
               if (mapka[y][x] == 8) 
         {
                ImageIcon imageIcon = new ImageIcon("C:/Users/PC/OneDrive/Pulpit/ProjektKrwinki/plytkakrwi.png");
                Image krwinkaImage = imageIcon.getImage();
                g.setColor(Color.RED);
                g.fillRect(x * szerokoscKomorki, y * wysokoscKomorki, szerokoscKomorki, wysokoscKomorki);
                g.drawImage(krwinkaImage, x * szerokoscKomorki, y * wysokoscKomorki, szerokoscKomorki, wysokoscKomorki, this);
         }
               if (mapka[y][x] == 9) 
         {
                ImageIcon imageIcon = new ImageIcon("C:/Users/PC/OneDrive/Pulpit/ProjektKrwinki/wirus.png");
                Image krwinkaImage = imageIcon.getImage();
                g.setColor(Color.RED);
                g.fillRect(x * szerokoscKomorki, y * wysokoscKomorki, szerokoscKomorki, wysokoscKomorki);
                g.drawImage(krwinkaImage, x * szerokoscKomorki, y * wysokoscKomorki, szerokoscKomorki, wysokoscKomorki, this);
         }
                if (mapka[y][x] == 10) 
         {
                ImageIcon imageIcon = new ImageIcon("C:/Users/PC/OneDrive/Pulpit/ProjektKrwinki/bakteria.png");
                Image krwinkaImage = imageIcon.getImage();
                g.setColor(Color.RED);
                g.fillRect(x * szerokoscKomorki, y * wysokoscKomorki, szerokoscKomorki, wysokoscKomorki);
                g.drawImage(krwinkaImage, x * szerokoscKomorki, y * wysokoscKomorki, szerokoscKomorki, wysokoscKomorki, this);
         }
                    if (mapka[y][x] == 11) 
         {
                ImageIcon imageIcon = new ImageIcon("C:/Users/PC/OneDrive/Pulpit/ProjektKrwinki/rana.png");
                Image krwinkaImage = imageIcon.getImage();
                g.setColor(Color.RED);
                g.fillRect(x * szerokoscKomorki, y * wysokoscKomorki, szerokoscKomorki, wysokoscKomorki);
                g.drawImage(krwinkaImage, x * szerokoscKomorki, y * wysokoscKomorki, szerokoscKomorki, wysokoscKomorki, this);
         }
                      if (mapka[y][x] == 12) 
         {
                ImageIcon imageIcon = new ImageIcon("C:/Users/PC/OneDrive/Pulpit/ProjektKrwinki/braktlenu.png");
                Image krwinkaImage = imageIcon.getImage();
                g.setColor(Color.RED);
                g.fillRect(x * szerokoscKomorki, y * wysokoscKomorki, szerokoscKomorki, wysokoscKomorki);
                g.drawImage(krwinkaImage, x * szerokoscKomorki, y * wysokoscKomorki, szerokoscKomorki, wysokoscKomorki, this);
         }
            }
        }
         
    }

    private Color getKolorDlaKomorki(int wartosc) {
        switch (wartosc) {
            case 0: return new Color(0,0,0,0);           // 0 - czerwona
            case 1: return new Color(139, 0, 0); // 1 - ciemny czerwony
            case 2: return Color.RED; // 2 - jasny czerwony
            default: return new Color(139,0,0);         // domyślny kolor
        }
    }
    
    private void inicjalizujObiekty(int liczbaKrwinekCzerownych, int liczbaKrwinekBialych, int liczbaPlytekKrwi, int liczbaWirusow, int liczbaBakterii, int liczbaRan, int liczbaBrakTlenu) 
    {  
        czkrwinki = new CzerwonaKrwinka[liczbaKrwinekCzerownych];
        bkrwinki= new BialaKrwinka [liczbaKrwinekBialych];
        plytkakrwi= new PlytkaKrwi [liczbaPlytekKrwi];
        wirus= new Wirus [liczbaWirusow];
        bakteria=new Bakteria [liczbaBakterii];
        Random rand = new Random();
            
        for (int i = 0; i < liczbaKrwinekCzerownych; i++) {
            
            int x = rand.nextInt(SZEROKOSC);
            int y = rand.nextInt(WYSOKOSC);
             
            if (mapka[y][x] == 2) 
            {
                mapka[y][x] = 6;
                czkrwinki[i] = new CzerwonaKrwinka(x, y );
                 System.out.println(czkrwinki[i].x);
                
            } 
            else 
            {
                i--;
            }
        }
        for (int i = 0; i < liczbaKrwinekBialych; i++) {
            
            int x = rand.nextInt(SZEROKOSC);
            int y = rand.nextInt(WYSOKOSC);
             
            if (mapka[y][x] == 2) 
            {
                mapka[y][x] = 7;
            } 
            else 
            {
                i--;
            }
        }
        for (int i = 0; i < liczbaPlytekKrwi; i++) {
            
            int x = rand.nextInt(SZEROKOSC);
            int y = rand.nextInt(WYSOKOSC);
             
            if (mapka[y][x] == 2) 
            {
                mapka[y][x] = 8;
            } 
            else 
            {
                i--;
            }
        }
         for (int i = 0; i < liczbaWirusow; i++) {
            
            int x = rand.nextInt(SZEROKOSC);
            int y = rand.nextInt(WYSOKOSC);
             
            if (mapka[y][x] == 2) 
            {
                mapka[y][x] = 9;
            } 
            else 
            {
                i--;
            }
        }
         for (int i = 0; i < liczbaBakterii; i++) {
            
            int x = rand.nextInt(SZEROKOSC);
            int y = rand.nextInt(WYSOKOSC);
             
            if (mapka[y][x] == 2) 
            {
                mapka[y][x] = 10;
            } 
            else 
            {
                i--;
            }
        }
           for (int i = 0; i < liczbaRan; i++) {
            
            int x = rand.nextInt(SZEROKOSC);
            int y = rand.nextInt(WYSOKOSC);
             
            if (mapka[y][x] == 2) 
            {
                mapka[y][x] = 11;
            } 
            else 
            {
                i--;
            }
        }
          for (int i = 0; i < liczbaBrakTlenu; i++) {
            
            int x = rand.nextInt(SZEROKOSC);
            int y = rand.nextInt(WYSOKOSC);
             
            if (mapka[y][x] == 3) 
            {
                mapka[y][x] = 12;
            } 
            else 
            {
                i--;
            }
        }
    }
    public void zapiszMapeDoPliku(String sciezkaDoPliku) {
        try (FileWriter writer = new FileWriter(sciezkaDoPliku)) {
            for (int y = 0; y < WYSOKOSC; y++) {
                for (int x = 0; x < SZEROKOSC; x++) {
                    writer.write(mapka[y][x] + (x == SZEROKOSC - 1 ? "" : " "));
                }
                writer.write("\n");
            }
            System.out.println("Mapa została zapisana do pliku: " + sciezkaDoPliku);
        } catch (IOException e) {
            System.err.println("Błąd podczas zapisywania mapy: " + e.getMessage());
        }
    }

    
    public void zapiszMape() {
        String sciezka = "mapa.txt";
        zapiszMapeDoPliku(sciezka);
    }
}
