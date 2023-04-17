package KeyInput;

import java.awt.Graphics2D;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class MouseInput extends Core implements KeyListener, MouseMotionListener, MouseListener, MouseWheelListener {
	public static void main(String[] args) {
		new MouseInput().run();
	}
	
	private String mess = "";
	
	public void init() {
		super.init();
		Window w = s.getFullScreenWindow();
		w.setFocusTraversalKeysEnabled(false); //no weird buttons like tab selects different object
		w.addMouseListener(this);
		w.addMouseMotionListener(this);
		w.addMouseWheelListener(this);
		w.addKeyListener(this); //want window to be listening to keys
		mess = "press escape to exit";
	}
	
	public synchronized void draw(Graphics2D g) {
		Window w = s.getFullScreenWindow();
		g.setColor(w.getBackground());
		g.fillRect(0,0,s.getWidth(),s.getHeight()); //color entire window the background color
		g.setColor(w.getForeground());
		g.drawString(mess, 40, 50);
	}

	public void mouseWheelMoved(MouseWheelEvent e) {
		mess = "you are scrolling the mouse";
	}

	public void mousePressed(MouseEvent e) {
		mess = "you pressed down the mouse";
	}

	public void mouseReleased(MouseEvent e) {
		mess = "you released the mouse";
	}

	public void mouseClicked(MouseEvent e) {}
	
	public void mouseEntered(MouseEvent e) {}

	public void mouseExited(MouseEvent e) {}

	public void mouseDragged(MouseEvent e) {
		mess = "you are dragging the mouse";
	}

	public void mouseMoved(MouseEvent e) {
		mess = "you are moving the mouse";
	}

	public void keyTyped(KeyEvent e) { //quick tapping of key
		e.consume(); //ignore
	}

	public void keyPressed(KeyEvent e) { //long press of key
		int keyCode = e.getKeyCode();
		if (keyCode == KeyEvent.VK_ESCAPE) stop(); //sets flag and while loop stops
		else {
			mess = "Pressed : " + KeyEvent.getKeyText(keyCode);
			e.consume(); //clears from usr input stream
		}
	}

	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
		mess = "Released : " + KeyEvent.getKeyText(keyCode);
		e.consume();
	}
}
