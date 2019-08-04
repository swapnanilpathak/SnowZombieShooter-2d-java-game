import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game extends Canvas implements Runnable{
	private static int width=1000,height=563;
	
	private static final long serialVersionUID = 1L;
	
	private boolean isRunning = false;
	private Thread t;
	private Handler h;
	private Camera camera;
	
	private BufferedImage level = null;
	public int ammo=100;
	public int heroHealth =100;
	public int totalEnemies =0;
	public int killedEnemies =0;
	Game(){
		new Window(width,height,"Attack 1944",this);
		start();
		h = new Handler();
		camera = new Camera(0,0);
		this.addKeyListener(new KeyInput(h));//we are adding key listener to this game class which is actually a canvas
		this.addMouseListener(new MouseInput(h, camera,this));
		BufferedImageLoader loader = new BufferedImageLoader();
		level = loader.loadImage("/level1Snow.png");
		//h.addObject(new Box(100,100,ID.Box));//here we can add boxes
		loadLevel(level);
		
		//h.addObject(new Hero(width/2, height/2, ID.Player, h));

	}
	public void start() {
		isRunning = true;
		t = new Thread(this);
		t.start();
	}
	public void stop() {
		isRunning = false;
		try {
			t.join();
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
	}
	public void update() {
		
		for(int i=0;i<h.object.size();i++) {
			if(h.object.get(i).getId()==ID.Player) {
				camera.update(h.object.get(i));
			}
		}
		h.update();
	}
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs==null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2D = (Graphics2D)g;
		//////////////////////////////////
		g.setColor(Color.white);//background will be white
		g.fillRect(0, 0, width, height);
		
		g2D.translate(-camera.getX(), -camera.getY());
		
		h.render(g);
		g2D.translate(camera.getX(), camera.getY());
		
		//heads up display
		g.setColor(Color.RED);
		g.fillRect(60, 50, 200, 32);
		g.setColor(Color.green);
		g.fillRect(60, 50, heroHealth*2, 32);
		g.setColor(Color.BLACK);
		g.drawRect(60, 50, 200, 32);
		g.drawString("HEALTH", 125,70);
		g.setColor(Color.red);
		g.drawString("Ammo "+ammo, 800, 80);
		g.drawString("Total Zombies "+totalEnemies, 800, 90);
		g.drawString("Zombies killed By You "+killedEnemies, 800, 100);
		
		//instructions
		g.setColor(Color.PINK);
		g.drawString("Instructions", 100, 450);
		g.drawString("Shooting: Left Mouse Button", 100, 460);
		g.drawString("Movement: W A S D keys", 100, 470);
		g.drawString("Regenerate ammo: collect", 100, 480);
		g.setColor(Color.magenta);
		g.fillRect(240, 475, 5, 5);
		g.setColor(Color.pink);
		g.drawString("Avoid These:", 100, 495);
		g.setColor(Color.red);
		g.fillRect(180, 480, 4, 16);
		
		g.setColor(Color.red);
		if(heroHealth<=0) {
			g.setFont(new Font("Verdana",0,100));
			g.drawString("Game Over", 200,300 );
		}
		if(killedEnemies == totalEnemies && totalEnemies!=0) {
			g.setFont(new Font("Verdana",0,100));
			g.drawString("You Win!!!", 200,300 );
		}
		
		
		
		//////////////////////////////////
		
		bs.show();
		g.dispose();
	}
	
	private void loadLevel(BufferedImage image) {
		int iw = image.getWidth();
		int ih = image.getHeight();
		for(int xx=0;xx<iw;xx++) {
			for(int yy=0;yy<ih;yy++) {
				int pixel = image.getRGB(xx, yy);
				int red = (pixel>>16)& 0xff;
				int green = (pixel>>8) & 0xff;
				int blue = (pixel) & 0xff;
				
				if(red==255)
					h.addObject(new Block(xx*32,yy*32,ID.Block));
				if(blue == 255 && green ==0)
					h.addObject(new Hero(xx*32,yy*32,ID.Player,h,this));
				if(green == 255 && blue==0) {
					h.addObject(new Enemy(xx*32,yy*32,ID.Enemy,h,this));
					totalEnemies++;
				}
					
				if(green == 255 && blue==255)
					h.addObject(new Box(xx*32, yy*32, ID.Box));
			}
		}
	}
	
	public void run() {
		this.requestFocus();//sets the focus on the canvas. without this line player will not move even when keys are pressed
		int fps = 60;
		double timePerTick = 1000000000/fps ; //1 billion nano sec = 1 sec
		double delta = 0;
		long now;
		long lastTime = System.nanoTime(); 
		long timer = 0;
		int ticks = 0;
		
		while(isRunning) {
			now = System.nanoTime();
			delta += (now - lastTime)/timePerTick;
			timer += now - lastTime;
			lastTime = now;
			
			if (delta >= 1) {
			update();
			render();
			ticks++;
			delta--;
			}
			if(timer >= 1000000000) {
				System.out.println("ticks and frames "+ ticks);
				ticks = 0;
				timer = 0;
			}
		}
		stop();
	}
	
	
	
	public static void main(String[] args) {
		
		//Game g = 
		new Game();
		//g.start();//without calling start thread wont run and project will be lifeless Jframe only

	}

}
