import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;



public class Box extends Object{

	Box(int x, int y, ID id) {
		super(x, y, id);
		//xVelocity = 10;//this will move the box
	}

	@Override
	public void update() {
		//x += xVelocity;
		//y+= yVelocity;
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.magenta);
		g.fillRect(x, y, 32, 32);
		
		
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x,y,32,32);
	}

}
