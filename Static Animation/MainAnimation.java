package FullScreen;

import java.awt.DisplayMode;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JFrame;

public class MainAnimation {
	private Screen screen;
	private ArrayList<Image> frames;
	private Image bg; //background
	private Animation a;
	
	public static void main(String[] args) {
		DisplayMode dm = new DisplayMode(800,600,16, DisplayMode.REFRESH_RATE_UNKNOWN);
		MainAnimation b = new MainAnimation();
		
		b.run(dm);   
	}
	
	public void loadPics() {
		/* for some reason loading from resource wayyyyyy slower than abs path:
		a = new Animation(5000, this);
		bg = new ImageIcon("C:\\Users\\thankYouGod\\Pictures\\java\\" + "back.png").getImage();	
		for (int i = 1; i <= 3; i++) {
			frames.add(new ImageIcon("C:\\Users\\thankYouGod\\Pictures\\java\\" + "face" + i + ".png").getImage());
			a.addScene( 601);
		}*/
		
		a = new Animation(6000, this);
		bg = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/resources/back.png"));
		
		for (int i = 1; i <= 3; i++) {
			frames.add(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/resources/face" + i + ".png")));
			a.addScene(601);
		}
	}
	
	public void run(DisplayMode dm) {
		frames = new ArrayList<Image>();
		screen = new Screen();
		try {
			screen.setFullScreen(dm, new JFrame());
			loadPics();
			a.start();
		} finally {
			screen.restoreScreen();
		}
	}
	
	public void draw(int framesIndex) {
		Graphics g = screen.getFullScreenWindow().getGraphics();
		g.drawImage(bg, 0, 0, null); //MUST be first, otherwise overlaps frame
		g.drawImage(frames.get(framesIndex), 0, 0, null);
	}
}
