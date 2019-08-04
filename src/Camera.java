
public class Camera {
	private float x,y;
	public Camera(float x,float y) {
		this.x=x;
		this.y=y;
	}
	
	public void update(Object object) {
		x+=((object.getX()-x)-1000/2)*0.05f;
		y+=((object.getY()-y)-563/2)*0.05f;
		
		if(x<=0) x=0;
		if(x>=1500) x=1500;//specify how much of left part of the map you want
		if(y<=0) y=0;
		if(y>=1520) y=1520;//how much of bottom
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
}
