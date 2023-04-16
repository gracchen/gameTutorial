package DynamicAnimation;

import java.awt.Image;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class Animation {
	private int sceneIndex = 0; //curr index of scene
	private long lastUpdate;
	private long timeOnFrame = 0;
	private ArrayList<SimpleEntry<Image, Long>> frames; //image and its duration

	public Animation(String path, int numImages) {
		frames = new ArrayList<SimpleEntry<Image, Long>>();
		for (int i = 1; i <= numImages; i++)
			frames.add(new SimpleEntry<Image, Long>(new ImageIcon(path+"face"+i+".png").getImage(),(long)600));
		lastUpdate = System.currentTimeMillis();
	}

	public Image getImage() {
		return frames.get(sceneIndex).getKey();
	}
	
	public long update() { //calculates the correct currFrame(), returns time it took to run itself
		try {
			Thread.sleep(20);
		} catch (Exception e) {}
		
		long timePassed = System.currentTimeMillis() - lastUpdate;
		timeOnFrame += timePassed; //add time passed since last update call
		lastUpdate = System.currentTimeMillis(); //increment lastUpdate for next update call

		if (timeOnFrame >= frames.get(sceneIndex).getValue()) //if spent enough time on the current frame
		{
			sceneIndex = (sceneIndex+1 == frames.size())? 0 : sceneIndex+1; //cycles through array, if at end reset to 0.
			timeOnFrame = 0; //new frame, reset time on curr frame
		}
		return timePassed;
	}
}
