import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Bullet extends Object{
	
	private Handler handler; 

	Bullet(int x, int y, ID id,Handler handler,int mx,int my) {
		super(x, y, id);
		this.handler = handler;
		xVelocity = (mx-x)/10;
		yVelocity = (my-y)/10;
	}

	@Override
	public void update() {
		x+=xVelocity;
		y+=yVelocity;
		for(int i=0;i<handler.object.size();i++) {
			Object tempObject = handler.object.get(i);
			if(tempObject.getId()==ID.Block) {
				if(getBounds().intersects(tempObject.getBounds())) {
					handler.removeObject(this);
				}
			}
		}
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillOval(x, y, 8, 8);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x,y,8,8);
	}

}
