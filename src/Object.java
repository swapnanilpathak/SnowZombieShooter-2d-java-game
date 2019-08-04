import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Object {
	protected int x,y;
	protected float xVelocity=0,yVelocity=0;
	protected ID id;
	Object(int x,int y,ID id){
		this.x=x;
		this.y=y;
		this.id=id;
		
	}
	
	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}

	public abstract void update();
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public float getxVelocity() {
		return xVelocity;
	}

	public void setxVelocity(float xVelocity) {
		this.xVelocity = xVelocity;
	}

	public float getyVelocity() {
		return yVelocity;
	}

	public void setyVelocity(float yVelocity) {
		this.yVelocity = yVelocity;
	}
	
	
}
