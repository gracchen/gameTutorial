package DynamicAnimation;

import java.awt.DisplayMode;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Main {
	private Screen screen;
	private Sprite sprite;
	private Image bg; //background
	private long duration = 15000; //5 sec

	public static void main(String[] args) {
		Main b = new Main();
		b.run();
	}

	private static final DisplayMode modes[] = {
			new DisplayMode(1920,1080,32,0),
			new DisplayMode(1920,1080,24,0),
			new DisplayMode(1920,1080,16,0),

			new DisplayMode(1680,1050,32,0),
			new DisplayMode(1680,1050,24,0),
			new DisplayMode(1680,1050,16,0),

			new DisplayMode(1600,900,32,0),
			new DisplayMode(1600,900,24,0),
			new DisplayMode(1600,900,16,0),

			new DisplayMode(1440,900,32,0),
			new DisplayMode(1440,900,24,0),
			new DisplayMode(1440,900,16,0),

			new DisplayMode(1400,1050,32,0),
			new DisplayMode(1400,1050,24,0),
			new DisplayMode(1400,1050,16,0),

			new DisplayMode(1366,768,32,0),
			new DisplayMode(1366,768,24,0),
			new DisplayMode(1366,768,16,0),

			new DisplayMode(1360,768,32,0),
			new DisplayMode(1360,768,24,0),
			new DisplayMode(1360,768,16,0),

			new DisplayMode(1280,1024,32,0),
			new DisplayMode(1280,1024,24,0),
			new DisplayMode(1280,1024,16,0),

			new DisplayMode(1280,960,32,0),
			new DisplayMode(1280,960,24,0),
			new DisplayMode(1280,960,16,0),

			new DisplayMode(1280,800,32,0),
			new DisplayMode(1280,800,24,0),
			new DisplayMode(1280,800,16,0),

			new DisplayMode(1280,768,32,0),
			new DisplayMode(1280,768,24,0),
			new DisplayMode(1280,768,16,0),

			new DisplayMode(1280,720,32,0),
			new DisplayMode(1280,720,24,0),
			new DisplayMode(1280,720,16,0),

			new DisplayMode(1152,864,32,0),
			new DisplayMode(1152,864,24,0),
			new DisplayMode(1152,864,16,0),

			new DisplayMode(1024,768,32,0),
			new DisplayMode(1024,768,24,0),
			new DisplayMode(1024,768,16,0),

			new DisplayMode(800,600,32,0),
			new DisplayMode(800,600,24,0),
			new DisplayMode(800,600,16,0),

			new DisplayMode(640,480,32,0),
			new DisplayMode(640,480,24,0),
			new DisplayMode(640,480,16,0),
	};


	public void run() {
		bg = new ImageIcon("C:\\Users\\thankYouGod\\Pictures\\java\\back.png").getImage();
		sprite = new Sprite(this, "C:\\Users\\thankYouGod\\Pictures\\java\\", 3, duration);
		sprite.setVelocityX(0.5f);
		sprite.setVelocityY(0.5f);
		screen = new Screen();
		try {
			DisplayMode dm = screen.findFirstCompatibleMode(modes); //finds perfect displaymode
			System.out.println("Perfect Displaymode: " + dm.getWidth() + "x" + dm.getHeight() + " @ " + dm.getBitDepth());
			screen.setFullScreen(dm); //sets that displaymode to fullscreen
			sprite.start(); //starts animation
		} finally {
			screen.restoreScreen();
		}
	}
	
	public void plsDraw(Image frame, int x, int y) {
		if (sprite.getX() < 0) 
			sprite.setVelocityX(Math.abs(sprite.getVelocityX()));
		else if (sprite.getX() + sprite.getWidth() > screen.getWidth()) 
			sprite.setVelocityX(-Math.abs(sprite.getVelocityX()));
		
		if (sprite.getY() < 0) 
			sprite.setVelocityY(Math.abs(sprite.getVelocityY()));
		else if (sprite.getY() + sprite.getHeight() > screen.getHeight()) 
			sprite.setVelocityY(-Math.abs(sprite.getVelocityY()));
		draw(frame, x, y);
	}

	public void draw(Image frame, int x, int y) {
		Graphics2D g = screen.getGraphics();
		g.drawImage(bg, 0, 0, null); //MUST be first, otherwise overlaps frame
		g.drawImage(frame, x, y, null);
		g.dispose();
		screen.update(); 
	}
}
