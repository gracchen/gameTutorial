package FullScreen;

import java.awt.Image;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;

public class Animation {
	private ArrayList<SimpleEntry<Image,Long>> scenes;
	private int sceneIndex = 0; //curr index of scene
	private long totalTime = 0; //totalTime = total duration of 1 cycle of the animation
	private long duration = 0; //how long should animation be?
	private Screen s;
	
	public Animation(long duration, Screen s) {
		this.duration = duration;
		this.s = s;
		scenes = new ArrayList<SimpleEntry<Image,Long>>();
	}
	
	 //"synchronized" = cannot run this with something else at same time, to preserve atomicity
	public synchronized void addScene(Image i, long t) { //duration of image shown in sec
		scenes.add(new SimpleEntry<Image, Long>(i, t));
	}
	
	public synchronized void start() {
		while(totalTime < duration) {
			s.getFullScreenWindow().getGraphics().drawImage(scenes.get(sceneIndex).getKey(), 0, 0, null); //draw curr frame
			try {
				Thread.sleep(scenes.get(sceneIndex).getValue()); //wait curr frame's duration
			} catch (InterruptedException e) {e.printStackTrace();}
			
			totalTime += scenes.get(sceneIndex).getValue(); //add to total time passed curr frame's duration
			sceneIndex = (sceneIndex+1 == scenes.size())? 0 : sceneIndex+1; //cycles through array, if at end reset to 0.
		}
	}
}
