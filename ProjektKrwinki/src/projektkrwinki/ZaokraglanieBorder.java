package projektkrwinki;
import javax.swing.*;
import java.awt.*;

public class ZaokraglanieBorder extends JButton {

    private static final int ARC_WIDTH = 30;
    private static final int ARC_HEIGHT = 30;

    public ZaokraglanieBorder(String text) {
        super(text);
        setOpaque(false); 
        setContentAreaFilled(false); 
        setFocusPainted(false); 
        setBorder(BorderFactory.createEmptyBorder()); 
        addButtonHoverAndClickEffects(this);
    }

    @Override
    protected void paintComponent(Graphics g) 
    {
        if (getModel().isPressed()) 
        {
            g.setColor(getBackground().darker()); 
        } else if (getModel().isRollover()) 
        {
            g.setColor(getBackground().brighter()); 
        } else 
        {
            g.setColor(getBackground()); 
        }

        g.fillRoundRect(0, 0, getWidth(), getHeight(), ARC_WIDTH, ARC_HEIGHT);

        super.paintComponent(g); 
    }
    private void addButtonHoverAndClickEffects(JButton button) 
    {
    button.setBackground((Color.WHITE));
    
    button.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));  
    button.setFocusPainted(false); 

    button.addMouseListener(new java.awt.event.MouseAdapter() {
        @Override
        public void mouseEntered(java.awt.event.MouseEvent evt)
        {
            button.setBackground(new Color(255, 160, 174)); 
            button.setForeground(new Color(139,0,0));
        }

        @Override
        public void mouseExited(java.awt.event.MouseEvent evt) {
            button.setBackground(Color.WHITE);
            button.setForeground(Color.RED); 
        }

        @Override
        public void mousePressed(java.awt.event.MouseEvent evt) {
            button.setBackground(new Color(255, 105, 97) ); 
        }

        @Override
        public void mouseReleased(java.awt.event.MouseEvent evt) {
            button.setBackground(Color.WHITE); 
        }
    });
     }
}
