package KeyInput;

import java.awt.Graphics2D;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyTest extends Core implements KeyListener {
	private String mess = ""; //what you are doing
	
	public static void main(String[] args) {
		new KeyTest().run();
	}
	
	public void init() {
		super.init();
		Window w = s.getFullScreenWindow();
		w.setFocusTraversalKeysEnabled(false); //no weird buttons like tab selects different object
		w.addKeyListener(this); //want window to be listening to keys
		mess = "press escape to exit";
		
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
	
	public synchronized void draw(Graphics2D g) {
		Window w = s.getFullScreenWindow();
		g.setColor(w.getBackground());
		g.fillRect(0,0,s.getWidth(),s.getHeight()); //color entire window the background color
		g.setColor(w.getForeground());
		g.drawString(mess, 30, 30);
	}
	
}
