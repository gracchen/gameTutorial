package DynamicAnimation;

public class Sprite {
	private Animation a;
	private Main m;
	private float x, y, dx, dy; //fraction of a pixel movement
	private long duration;
	
	public Sprite(Main m, String path, int numImages, long duration) {
		this.duration = duration;
		this.m = m;
		a = new Animation(path, numImages);
	}
	
	public void start() {
		duration += System.currentTimeMillis();
		do {
			m.plsDraw(a.getImage(), Math.round(x), Math.round(y));
			long timePassed = System.currentTimeMillis();
			a.update();
			update(System.currentTimeMillis()-timePassed);
			//System.out.println(x + "," + y);
		} while (System.currentTimeMillis() < duration);
	}
	
	public void update(long timePassed) {
		x += dx * timePassed;
		y += dy * timePassed;
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public void setX(float x) {
		this.x = x;
	}
	
	public void setY(float y) {
		this.y = y;
	}
	
	public int getWidth() {
		return a.getImage().getWidth(null);
	}
	
	public int getHeight() {
		return a.getImage().getHeight(null);
	}
	
	public float getVelocityX() {
		return dx;
	}
	
	public float getVelocityY() {
		return dy;
	}
	
	public void setVelocityX (float dx) {
		this.dx = dx;
	}

	public void setVelocityY (float dy) {
		this.dy = dy;
	}
	
}
