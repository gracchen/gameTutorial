package FullScreen;

import java.awt.DisplayMode;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class MainAnimation {
	private Screen screen;
	private Image face1, face2, face3;
	private Animation a;
	
	public static void main(String[] args) {
		DisplayMode dm = new DisplayMode(800,600,16, DisplayMode.REFRESH_RATE_UNKNOWN);
		MainAnimation b = new MainAnimation();
		b.run(dm);   
	}
	
	public void loadPics() {
		face1 = new ImageIcon("C:\\Users\\thankYouGod\\Pictures\\java\\face1.png").getImage();	
		face2 = new ImageIcon("C:\\Users\\thankYouGod\\Pictures\\java\\face2.png").getImage();	
		face3 = new ImageIcon("C:\\Users\\thankYouGod\\Pictures\\java\\face3.png").getImage();	
		a = new Animation(5000, this);
		a.addScene(face1, 601); //in ms
		a.addScene(face2, 300);
		a.addScene(face3, 600);
		a.addScene(face2, 300);
	}
	
	public void run(DisplayMode dm) {
		screen = new Screen();
		try {
			screen.setFullScreen(dm, new JFrame());
			loadPics();
			a.start();
		} finally {
			screen.restoreScreen();
		}
	}
	
	public void draw(Image m) {
		screen.getFullScreenWindow().getGraphics().drawImage(m, 0, 0, null);
	}
}
