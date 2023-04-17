package KeyInput;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Robot;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;

public class Look extends Core implements MouseMotionListener, KeyListener{
	
	private Image bg;
	private Robot robot; //moves mouse for you;
	private Point mouse; //mouse location
	private Point center; //center of screen
	private Point image; //background image's location
	private boolean centering; //false if mouse already in center
	
	public static void main(String[] args) {
		new Look().run(); //scroll or move cursor forever like in first person game
	}
	
	public void init() {
		System.out.println("init()");
		super.init();
		mouse = new Point(); 
		center = new Point();
		image = new Point();
		centering = false;
		
		try {
			robot = new Robot();
			recenterMouse(); //prevents mouse from going to edge
			mouse.x = center.x;
			mouse.y = center.y;
		} catch (Exception e) { System.out.println("Exception A");}
		
		Window w = s.getFullScreenWindow();
		w.addMouseMotionListener(this);
		w.addKeyListener(this);
		bg = new ImageIcon("C:\\Users\\thankYouGod\\Pictures\\java\\back.png").getImage();
	}
	
	public synchronized void draw(Graphics2D g) {
		//System.out.println("draw()");
		image.x %= s.getWidth(); // if image.x > width, gives how much it overlaps by it.
		image.y %= s.getHeight();
		if (image.x < 0) image.x += s.getWidth(); //wraps around if negative
		if (image.y < 0) image.y += s.getHeight();
		
		//draw 2x2 tiles
		g.clearRect(0,0,s.getWidth(),s.getHeight());
		g.drawImage(bg, image.x, image.y, null);
		g.drawImage(bg, image.x-s.getWidth(), image.y, null);
		g.drawImage(bg, image.x, image.y-s.getHeight(), null);
		g.drawImage(bg, image.x-s.getWidth(), image.y-s.getHeight(), null);
	}
	
	private synchronized void recenterMouse() {
		//System.out.println("recenterMouse()");
		Window w = s.getFullScreenWindow();
		if (robot != null && w.isShowing()) {
			center.x = w.getWidth() / 2;
			center.y = w.getHeight() / 2;
			SwingUtilities.convertPointToScreen(center, w); //transforms to coords of window
			centering = true;
			robot.mouseMove(center.x, center.y);
		}
	}

	public void keyTyped(KeyEvent e) { }

	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if (keyCode == KeyEvent.VK_ESCAPE) stop(); //sets flag and while loop stops
	}

	public void keyReleased(KeyEvent e) {}

	public void mouseDragged(MouseEvent e) {
		mouseMoved(e);
	}

	public synchronized void mouseMoved(MouseEvent e) {
		//System.out.println("mouseMoved(e)");
		if (centering && center.x == e.getX() && center.y == e.getY()) { //already done centering
			centering = false;
		} else {
			int dx = e.getX() - mouse.x;
			int dy = e.getY() - mouse.y; //how much it moved from last time
			image.x += dx;
			image.y += dy;
			recenterMouse();
		}
		
		mouse.x = e.getX();
		mouse.y = e.getY();
	}

}
