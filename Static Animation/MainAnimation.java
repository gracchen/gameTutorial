package FullScreen;

import java.awt.DisplayMode;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class MainAnimation {

	public static void main(String[] args) {
		DisplayMode dm = new DisplayMode(800,600,16, DisplayMode.REFRESH_RATE_UNKNOWN);
		MainAnimation b = new MainAnimation();
		b.run(dm);   
	}
	
	private Screen screen;
	private Image bg, face1, face2;
	private Animation a;
	
	public void loadPics() {
		System.out.println("loading pics...");
		bg = new ImageIcon("C:\\Users\\thankYouGod\\Pictures\\java\\back.png").getImage();	
		face1 = new ImageIcon("C:\\Users\\thankYouGod\\Pictures\\java\\face1.png").getImage();	
		face2 = new ImageIcon("C:\\Users\\thankYouGod\\Pictures\\java\\face2.png").getImage();	
		a = new Animation();
		a.addScene(face1, 500); //in ms
		a.addScene(face2, 500);
	}
	
	public void run(DisplayMode dm) {
		System.out.println("main run():");
		screen = new Screen();
		try {
			screen.setFullScreen(dm, new JFrame());
			loadPics();
			movieLoop();
		} finally {
			screen.restoreScreen();
		}
	}
	
	public void movieLoop() {
		long startingTime = System.currentTimeMillis(); //0
		long cumTime = startingTime; //0
		
		while (cumTime - startingTime < 5000) {//limit to 5 sec only
			long timePassed = System.currentTimeMillis() - cumTime;
			cumTime += timePassed;
			a.update(timePassed);
			
			Graphics g = screen.getFullScreenWindow().getGraphics();
			draw(g);
			g.dispose();
			
			try {
				Thread.sleep(20); //time for background processes to catch up
			} catch (Exception e) {}
		}
	}
	
	public void draw(Graphics g) {
		g.drawImage(bg, 0, 0, null);
		g.drawImage(a.getImage(), 0, 0, null);
	}
}
