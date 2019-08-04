import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Block extends Object{

	Block(int x, int y, ID id) {
		super(x, y, id);
	}

	@Override
	public void update() {
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(x, y, 32, 32);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x,y,32,32);
	}

}
