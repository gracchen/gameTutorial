package FullScreen;

import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Window;

import javax.swing.JFrame;

public class Screen {
	private GraphicsDevice vc; //card
	
	public Screen() {
		//need environment of vc (like struct of state)
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		vc = env.getDefaultScreenDevice();
		
	}
	public void setFullScreen(DisplayMode dm, JFrame window) {
		
		window.setUndecorated(true); //purely one screen, no titlebar etc
		window.setResizable(false);
		vc.setFullScreenWindow(window); //wrapper for this function that does bulk of work
		
		//resolution, bit depth, bit rate
		if (dm != null && vc.isDisplayChangeSupported()) { //if any variables + allowed to change display
			try {
				vc.setDisplayMode(dm);
			} catch (Exception e) {}
		}
	}
	
	public Window getFullScreenWindow() {
		return vc.getFullScreenWindow();
	}
	
	public void restoreScreen() {
		Window w = vc.getFullScreenWindow();
		if (w != null) {
			w.dispose(); //freeing up resources when closing window
		}
		vc.setFullScreenWindow(null); //collapses full screensxcvsdaswe4ol
	}
}
