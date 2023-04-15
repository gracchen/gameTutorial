package FullScreen;

import java.awt.Image;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;

public class Animation {
	private ArrayList<SimpleEntry<Image,Long>> scenes;
	private int sceneIndex; //curr index of scene
	private long movieTime, totalTime; //totalTime = total duration of 1 cycle of the animation
	//movieTime = 
	
	public Animation() {
		System.out.println("new animation...");
		scenes = new ArrayList<SimpleEntry<Image,Long>>();
		totalTime = 0;
		start();
	}
	
	 //"synchronized" = cannot run this with something else at same time, to preserve atomicity
	public synchronized void addScene(Image i, long t) { //duration of image shown in sec
		System.out.println("adding scene " + i.toString() + " for " + t + " ms");
		System.out.println("\tTotaltime: " + String.valueOf(totalTime) + " + " + String.valueOf(t) + " = " + String.valueOf(totalTime+t));
		totalTime += t;
		scenes.add(new SimpleEntry<Image, Long>(i, totalTime));
	}
	
	public synchronized void start() {
		movieTime = 0;
		sceneIndex = 0;
	}
	
	public synchronized void update(long timePassed) { //time since last call to update()
		System.out.print("update(" + String.valueOf(timePassed) + ")");
		if (scenes.size() > 1) { //if just one scene, no animation
			System.out.println(" movieTime: " + String.valueOf(movieTime) + " + " + String.valueOf(timePassed) + " = " + String.valueOf(movieTime+timePassed));
			
			movieTime += timePassed; //movieTime = all time passed during animation
			if (movieTime >= totalTime) { //if animation done, need to restart
				System.out.println("movieTime(" + movieTime + ") >= totalTime(" + totalTime + "), resetting");
				movieTime = 0;
				sceneIndex = 0;
			}
			System.out.println("movieTime(" + movieTime + ") > getScene(" + sceneIndex +").endTime("+ scenes.get(sceneIndex).getValue() + ")");
			
			while(movieTime > scenes.get(sceneIndex).getValue()) { //gets animation slide you are on
				System.out.println("sceneIndex(" + sceneIndex + ")++ = " + String.valueOf(sceneIndex+1));
				sceneIndex++; //go to next scene
			}
		}
	}
	
	public synchronized Image getImage() {
		if (scenes.size() == 0) {
			return null;
		} else {
			return scenes.get(sceneIndex).getKey(); //get currently showing scene
		}
	}
	
}
