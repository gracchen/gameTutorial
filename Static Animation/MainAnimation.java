package FullScreen;

import java.awt.DisplayMode;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class MainAnimation {
	private Screen screen;
	private ArrayList<Image> frames;
	private Image bg; //background
	private Animation a;
	private static String path;
	
	public static void main(String[] args) {
		DisplayMode dm = new DisplayMode(800,600,16, DisplayMode.REFRESH_RATE_UNKNOWN);
		MainAnimation b = new MainAnimation();
		
		path = "C:\\Users\\thankYouGod\\Pictures\\java\\";
		b.run(dm);   
	}
	
	public void loadPics(String path) {
		a = new Animation(5000, this);
		bg = new ImageIcon(path + "back.png").getImage();	
		for (int i = 1; i <= 3; i++) {
			frames.add(new ImageIcon(path + "face" + i + ".png").getImage());
			a.addScene(frames.get(i-1), 601);
		}
	}
	
	public void run(DisplayMode dm) {
		frames = new ArrayList<Image>();
		screen = new Screen();
		try {
			screen.setFullScreen(dm, new JFrame());
			loadPics(path);
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
