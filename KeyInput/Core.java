package KeyInput;

import java.awt.Color;
import java.awt.DisplayMode;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Window;

import DynamicAnimation.Screen;

public abstract class Core {
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
	private boolean running;
	protected Screen s;

	public void stop() {
		running = false;
	}

	public void run() {
		try {
			init();
			gameLoop();
		} finally {
			s.restoreScreen();
		}
	}

	public void init() {
		s = new Screen();
		DisplayMode dm = s.findFirstCompatibleMode(modes);
		s.setFullScreen(dm);

		Window w = s.getFullScreenWindow();
		w.setFont(new Font("Arial", Font.PLAIN, 20));
		w.setBackground(Color.GREEN);
		w.setForeground(Color.WHITE);
		running = true;
	}

	public void gameLoop() {
		long startTime = System.currentTimeMillis();
		long cumTime = startTime;

		while (running) {
			long timePassed = System.currentTimeMillis() - cumTime;
			cumTime += timePassed;
			update(timePassed);

			Graphics2D g = s.getGraphics();
			draw(g);
			g.dispose();
			s.update();

			try {
				Thread.sleep(20);
			}catch (Exception e) {}
		}
	}

	public void update(long timePassed) {

	}

	public void draw(Graphics2D g) {

	}
}
