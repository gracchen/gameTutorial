package DynamicAnimation;

import java.awt.DisplayMode;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Window;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Screen {
	private GraphicsDevice vc; //card

	//let vc access monitor
	public Screen() {
		//need environment of vc (like struct of state)
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		vc = env.getDefaultScreenDevice();
	}
	
	public void setFullScreen(DisplayMode dm) {
		JFrame f = new JFrame();
		f.setUndecorated(true);
		f.setIgnoreRepaint(true);
		f.setResizable(false);
		vc.setFullScreenWindow(f);
		
		if(dm != null && vc.isDisplayChangeSupported()) {
			try {
				vc.setDisplayMode(dm);
			} catch (Exception e) {};
		}
		f.createBufferStrategy(2); //two drawings, flip between two
	}

	public DisplayMode[] getCompatibleDisplayModes() { 
		return vc.getDisplayModes();
	}

	public DisplayMode findFirstCompatibleMode(DisplayMode modes[]) {
		DisplayMode goodModes[] = vc.getDisplayModes();
		for (int i = 0; i < modes.length; i++) {
			for (int j = 0; j < goodModes.length; j++) {
				if (displayModesMatch(modes[i], goodModes[j])) {
					return modes[i]; //compatible with both program and graphics card
				}
			}
		}
		return null;
	}
	
	public DisplayMode getCurrentDisplayMode() {
		return vc.getDisplayMode();
	}
	
	public boolean displayModesMatch(DisplayMode m1, DisplayMode m2) {
		if(m1.getBitDepth() != DisplayMode.BIT_DEPTH_MULTI && m2.getBitDepth() != DisplayMode.BIT_DEPTH_MULTI && m1.getBitDepth() != m2.getBitDepth()) 
			return false;
		if(m1.getHeight() != m2.getHeight() || m1.getWidth() != m2.getWidth()) 
			return false;
		if(m1.getRefreshRate() != DisplayMode.REFRESH_RATE_UNKNOWN && m2.getRefreshRate()!= DisplayMode.REFRESH_RATE_UNKNOWN && m2.getRefreshRate() != m1.getRefreshRate()) 
			return false;
		return true;
	}
	
	public Graphics2D getGraphics() {
		Window w = vc.getFullScreenWindow(); //storse everything in f into this
		if (w != null) {
			BufferStrategy s = w.getBufferStrategy();
			return (Graphics2D)s.getDrawGraphics();
		}
		return null;
	}
	
	public void update() {
		Window w = vc.getFullScreenWindow();
		if(w != null) {
			BufferStrategy s = w.getBufferStrategy();
			Graphics graphics = s.getDrawGraphics();
			if (!s.contentsLost()) { //if not lost, have stuff to look at
				s.show();
			}
		}
	}
	
	public Window getFullScreenWindow() {
		return vc.getFullScreenWindow();
	}
	
	public int getWidth() {
		Window w = vc.getFullScreenWindow();
		if (w != null) return w.getWidth();
		return 0;
	}
	
	public int getHeight() {
		Window w = vc.getFullScreenWindow();
		if (w != null) return w.getHeight();
		return 0;
	}
	
	public void restoreScreen() {
		Window w = vc.getFullScreenWindow();
		if (w!= null) w.dispose();
		vc.setFullScreenWindow(null);
	}
	
	public BufferedImage createCompatibleImage(int w, int h , int t) { //width, height, transparency
		Window win = vc.getFullScreenWindow();
		if (win != null) {
			GraphicsConfiguration gc = win.getGraphicsConfiguration(); //get monitor's settings
			return gc.createCompatibleImage(w,h,t);
		}
		return null;
	}
}
