import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Hero extends Object{
	Handler handler;
	Game game;
	Hero(int x, int y, ID id,Handler handler,Game game) {
		super(x, y, id);
		this.handler = handler;
		this.game = game;
	}

	@Override
	public void update() {
		x+=xVelocity;
		y+=yVelocity;
		
		collision();
		
		if (handler.isUp()) yVelocity=-5;
		else if (!handler.isDown()) yVelocity=0;
		
		if (handler.isDown()) yVelocity=5;
		else if (!handler.isUp()) yVelocity=0;
		
		if (handler.isRight()) xVelocity=5;
		else if (!handler.isLeft()) xVelocity=0;
		
		if (handler.isLeft()) xVelocity=-5;
		else if (!handler.isRight()) xVelocity=0;
		
		if(game.heroHealth<0) {
			xVelocity=0;
			yVelocity=0;
		}
		
		
	}
	
	private void collision() {
		for(int i=0;i<handler.object.size();i++) {
			Object tempObject = handler.object.get(i);
			if(tempObject.getId()==ID.Block) {
				if(getBounds().intersects(tempObject.getBounds())) {//getBounds at the start of this line refers to this hero class if it intersects with blocks or not
					x += xVelocity * -1;
					y += yVelocity * -1;
				}
				
			}
			if(tempObject.getId()==ID.Box) {
				if(getBounds().intersects(tempObject.getBounds())) {//getBounds at the start of this line refers to this hero class if it intersects with blocks or not
					game.ammo+=100;
					handler.removeObject(tempObject);
				}
				
			}
			if(tempObject.getId()==ID.Enemy) {
				if(getBounds().intersects(tempObject.getBounds())) {//getBounds at the start of this line refers to this hero class if it intersects with blocks or not
					game.heroHealth-=10;
					
				}
				
			}
		}
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(x, y, 32, 48);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x,y,32,48);
	}

}
