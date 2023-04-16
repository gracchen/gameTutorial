package FullScreen;

import java.awt.DisplayMode;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class MainAnimation {
	private Screen screen;
	private ArrayList<Image> frames;
	private Image bg; //background
	private Animation a;

	public static void main(String[] args) {
		MainAnimation b = new MainAnimation();
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

	public void loadPics() {
		//for some reason loading from resource wayyyyyy slower than abs path:
		a = new Animation(5000, this);
		a = new Animation(5000,this);
		bg = new ImageIcon("C:\\Users\\thankYouGod\\Pictures\\java\\" + "back.png").getImage();
		for (int i = 1; i <= 3; i++) {
			frames.add(new ImageIcon("C:\\Users\\thankYouGod\\Pictures\\java\\" + "face" + i + ".png").getImage());
			a.addScene( 601);
		}

		/*a = new Animation(6000, this);
               bg = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/resources/back.png"));

               for (int i = 1; i <= 3; i++) {
                       frames.add(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/resources/face" + i + ".png")));
                        a.addScene(601);
                }
		 */
	}

	public void run() {
		frames = new ArrayList<Image>();
		screen = new Screen();
		try {
			DisplayMode dm = screen.findFirstCompatibleMode(modes); //finds perfect displaymode
			System.out.println("Perfect Displaymode: " + dm.getWidth() + "x" + dm.getHeight() + " @ " + dm.getBitDepth());
			screen.setFullScreen(dm); //sets that displaymode to fullscreen
			loadPics(); //sets up animation frames
			a.start(); //starts animation
		} finally {
			screen.restoreScreen();
		}
	}

	public void draw(int framesIndex) {
		Graphics2D g = screen.getGraphics();
		g.drawImage(bg, 0, 0, null); //MUST be first, otherwise overlaps frame
		g.drawImage(frames.get(framesIndex), 0, 0, null);
		g.dispose(); 
		screen.update(); 
	}
}
