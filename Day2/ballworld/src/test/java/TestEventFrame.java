import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;

public class TestEventFrame extends JFrame implements KeyListener, MouseMotionListener {

    public TestEventFrame() {
        addKeyListener(this);
        addMouseMotionListener(this);
    }
    public static void main(String[] args) {
        TestEventFrame frame = new TestEventFrame();

        frame.setSize(600, 500);
        frame.setVisible(true);
    }
    @Override
    public void keyTyped(KeyEvent arg0) {
        System.out.println("key pressed : " + arg0.getKeyCode());
    }
    @Override
    public void keyPressed(KeyEvent e) {
    }
    @Override
    public void keyReleased(KeyEvent e) {
    }
    @Override
    public void mouseDragged(MouseEvent e) {
        System.out.println("mouse drag : " + e.getX() + "," + e.getY());
    }
    @Override
    public void mouseMoved(MouseEvent e) {
        System.out.println("mouse move : " + e.getX() + "," + e.getY());
    }
}
