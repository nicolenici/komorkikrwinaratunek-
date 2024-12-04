package projektkrwinki;
import java.awt.event.KeyEvent;

public abstract class Obiekt {
    protected int x;
    protected int y;
    protected PositionChangeListener listener;

    public Obiekt(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void move(KeyEvent e) {
        int oldX = this.x;
        int oldY = this.y;

        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                if (this.y > 0) this.y--;
                break;
            case KeyEvent.VK_A:
                if (this.x > 0) this.x--;
                break;
            case KeyEvent.VK_S:
                this.y++;
                break;
            case KeyEvent.VK_D:
                this.x++;
                break;
        }

        if (listener != null) {
            listener.onPositionChange(oldX, oldY, this.x, this.y);
        }

        System.out.println(this.getClass().getSimpleName() + " moved to: (" + this.x + ", " + this.y + ")");
    }

    public void setPositionChangeListener(PositionChangeListener listener) {
        this.listener = listener;
    }

    public void setPosition(int newX, int newY) {
        this.x = newX;
        this.y = newY;
    }

    public void deactivate() {
        this.listener = null;
        System.out.println(this.getClass().getSimpleName() + " has been deactivated");
    }

    public interface PositionChangeListener {
        void onPositionChange(int oldX, int oldY, int newX, int newY);
    } 
}
