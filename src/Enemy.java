import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

public class Enemy extends Object {
	
	private Handler handler;
	Random r = new Random();
	int choose =0;
	int enemyHealth = 100;
	Game game;

	Enemy(int x, int y, ID id,Handler handler,Game game) {
		super(x, y, id);
		this.handler = handler;
		this.game = game;
	}

	@Override
	public void update() {
		x+=xVelocity;
		y+=yVelocity;
		
		choose = r.nextInt(10);
		for(int i=0;i<handler.object.size();i++) {
			Object tempObject = handler.object.get(i);
			if(tempObject.getId()==ID.Block) {
				if(getBoundsBig().intersects(tempObject.getBounds())) {
					
					x += (xVelocity*5)*-1;
					y += (yVelocity*5)*-1;
					xVelocity *= -1;
					yVelocity *= -1;
				}else if(choose == 0) {
					xVelocity = (r.nextInt(4 - -4)+ -4);
					yVelocity = (r.nextInt(4 - -4)+ -4);
				}
			}
			
			if(tempObject.getId()==ID.Bullet) {
				if(getBounds().intersects(tempObject.getBounds())) {
					enemyHealth-=100;
					handler.removeObject(tempObject);
					handler.removeObject(this);
					game.killedEnemies++;
				}
				
			}
		}
		//if(enemyHealth<=0)handler.removeObject(this);//if two three shots are required to kill enemy
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(x, y, 32, 48);
// 		if you want to see the bounding box		
//		Graphics2D g2d = (Graphics2D)g;
//		g.setColor(Color.cyan);
//		g2d.draw(getBoundsBig());
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x,y,32,48);
	}
	
	public Rectangle getBoundsBig() {
		return new Rectangle(x-16,y-16,64,96);
	}

}
