package FullScreen;

import java.awt.Image;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;

public class Animation {
	private ArrayList<SimpleEntry<Image,Long>> scenes;
	private int sceneIndex = 0; //curr index of scene
	private long totalTime = 0; //totalTime = total duration of 1 cycle of the animation
	private long duration = 0; //how long should animation be?
	private MainAnimation m;
	
	public Animation(long duration, MainAnimation m) {
		this.duration = duration;
		this.m = m;
		scenes = new ArrayList<SimpleEntry<Image,Long>>();
	}
	
	 //"synchronized" = cannot run this with something else at same time, to preserve atomicity
	public synchronized void addScene(Image i, long t) { //duration of image shown in sec
		scenes.add(new SimpleEntry<Image, Long>(i, t));
	}
	
	public synchronized void start() {
		while(totalTime < duration) {
			m.draw(scenes.get(sceneIndex).getKey());
			 //draw curr frame
			System.out.print("Frame " + sceneIndex + ": ");
			if (totalTime + scenes.get(sceneIndex).getValue() > duration) //if last frame, if shown in full, will exceed duration
			{
				try { Thread.sleep(duration - totalTime); //wait remaining sliver of time left
				} catch (InterruptedException e) {e.printStackTrace();}
				System.out.println(totalTime + "+" + (duration - totalTime) + "=" + duration);
				return; //exit, last frame done.
			}
			else {
				try { Thread.sleep(scenes.get(sceneIndex).getValue()); //wait entire curr frame's duration
				} catch (InterruptedException e) {e.printStackTrace();}
			}
			System.out.print(totalTime + "+" + (scenes.get(sceneIndex).getValue()) + "=");
			totalTime += scenes.get(sceneIndex).getValue(); //add to total time passed curr frame's duration
			System.out.println(totalTime);
			sceneIndex = (sceneIndex+1 == scenes.size())? 0 : sceneIndex+1; //cycles through array, if at end reset to 0.
		}
	}
}
