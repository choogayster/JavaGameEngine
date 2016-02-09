package sample.old;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class StartingClass extends Applet implements Runnable, KeyListener {
	
	enum GameState {
		Running, Dead;
	}
	
	GameState state = GameState.Running;
	
	private static sample.old.Robot robot;
	public static Heliboy hb, hb2;
	private Image image;
	private Image currentSprite;
	private Image character, character2, character3;
	private Image characterDown;
	private Image characterJumped;
	private Image background;
	private Image heliboy, heliboy2, heliboy3, heliboy4, heliboy5;
	private URL base;
	private Graphics second;
	private static Background bg1; 
	private static Background bg2;
	private Animation anim, hanim;
	
	public static Image tiledirt;
	public static Image tilegrassTop;
	public static Image tilegrassBot;
	public static Image tilegrassLeft;
	public static Image tilegrassRight;
	
	private ArrayList<Tile> tilearray = new ArrayList<Tile>();
	
	public static int score = 0;
	private Font font = new Font(null, Font.BOLD, 30);
	
	@Override
	public void init() {
		setSize(800,480);
		setBackground(Color.BLACK);
		setFocusable(true);
		addKeyListener(this);
		Frame frame = (Frame) this.getParent().getParent();
		frame.setTitle("Test Game");
		try {
			base = getDocumentBase();
		} catch(Exception e) {
			
		}
		background = getImage(base, "background.png");
		character = getImage(base, "character.png");
		character2 = getImage(base, "character2.png");
		character3 = getImage(base, "character3.png");
		
		characterDown = getImage(base, "down.png");
		characterJumped = getImage(base, "jumped.png");
		
		heliboy = getImage(base, "heliboy.png");
		heliboy2 = getImage(base, "heliboy2.png");
		heliboy3 = getImage(base, "heliboy3.png");
		heliboy4 = getImage(base, "heliboy4.png");
		heliboy5 = getImage(base, "heliboy5.png");
		
		tiledirt = getImage(base, "tiledirt.png");
		tilegrassTop = getImage(base, "tilegrasstop.png");
		tilegrassBot = getImage(base, "tilegrassbot.png");
		tilegrassLeft = getImage(base, "tilegrassleft.png");
		tilegrassRight = getImage(base, "tilegrassright.png");
		
		anim = new Animation();
		anim.addFrame(character, 1250);
		anim.addFrame(character2, 50);
		anim.addFrame(character3, 50);
		anim.addFrame(character2, 50);
		
		hanim = new Animation();
		hanim.addFrame(heliboy, 100);
		hanim.addFrame(heliboy2, 100);
		hanim.addFrame(heliboy3, 100);
		hanim.addFrame(heliboy4, 100);
		hanim.addFrame(heliboy5, 100);
		hanim.addFrame(heliboy4, 100);
		hanim.addFrame(heliboy3, 100);
		hanim.addFrame(heliboy2, 100);
		
		currentSprite = anim.getImage();
		
	}
	
	@Override
	public void start() {
		bg1 = new Background(0, 0);
		bg2 = new Background(2160, 0);
		robot = new sample.old.Robot();
		
		try {
			loadMap("map1.txt");
		} catch (IOException e) {
			
		}
		
		hb = new Heliboy(340, 360);
		hb2 = new Heliboy(700, 360);
		Thread thread = new Thread(this);
		thread.start();
	}
	
	private void loadMap(String filename) throws IOException {
		ArrayList lines = new ArrayList();
		int width = 0;
		int height = 0;
		BufferedReader reader = new BufferedReader(new FileReader(filename));
		while (true) {
			String line = reader.readLine();
			if (line == null) {
				reader.close();
				break;
			}
			if (!line.startsWith("!")) {
				lines.add(line);
				width = Math.max(width, line.length());
			}
		}
		height = lines.size();
		for (int j = 0; j < 12; j++) {
			String line = (String) lines.get(j);
			for (int i = 0; i < width; i++) {
				System.out.println(i + "is i ");
				if (i < line.length()) {
					char ch = line.charAt(i);
					Tile t = new Tile (i, j, Character.getNumericValue(ch));
					tilearray.add(t);
				}
			}
		}
	}

	@Override
	public void stop() {
		 
	}
	
	@Override
	public void destroy() {
		 
	}

	@Override
	public void run() {
		
		if (state == GameState.Running) {
			while(true) {
				robot.update();
				if(robot.isJumped()) {
					currentSprite = characterJumped;
				} else if (robot.isJumped() == false && robot.isDucked() == false) {
					currentSprite = anim.getImage();
				}
				ArrayList projectiles = robot.getProjectiles();
				for (int i = 0; i < projectiles.size(); i++) {
					Projectile p = (Projectile) projectiles.get(i);
					if(p.isVisible() == true) {
						p.update();
					} else {
						projectiles.remove(i);
					}
				}
				updateTiles();
				hb.update();
				hb2.update();
				bg1.update();
				bg2.update();
				
				animate();
				repaint();
				try {
					Thread.sleep(17);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (robot.getCenterY() > 500) {
					state= GameState.Dead;
				}
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_UP:
			System.out.println("Move up");
			break;
		case KeyEvent.VK_DOWN:
			currentSprite = characterDown;
			if (robot.isJumped() == false) {
				robot.setDucked(true);
				robot.setSpeedX(0);
			}
			break;
		case KeyEvent.VK_LEFT:
			robot.moveLeft();
			robot.setMovingLeft(true);
			break;
		case KeyEvent.VK_RIGHT:
			robot.moveRight();
			robot.setMovingRight(true);
			break;
		case KeyEvent.VK_SPACE:
			robot.jump();
			break;
		case KeyEvent.VK_CONTROL:
			if (robot.isDucked() == false && robot.isJumped() == false) {
				robot.shoot();
				robot.setReadyToFire(false);
			}
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_UP:
			System.out.println("Stop moving up");
			break;
		case KeyEvent.VK_DOWN:
			currentSprite = anim.getImage();
			robot.setDucked(false);
			break;
		case KeyEvent.VK_LEFT:
			robot.stopLeft();
			break;
		case KeyEvent.VK_RIGHT:
			robot.stopRight();
			break;
		case KeyEvent.VK_SPACE:
			break;
		case KeyEvent.VK_CONTROL:
			robot.setReadyToFire(true);
			break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	
	@Override
	public void update(Graphics g) {
		
		if (image == null) {
			image = createImage(this.getWidth(), this.getHeight());
			second = image.getGraphics();
		}
		
		second.setColor(getBackground());
		second.fillRect(0, 0, getWidth(), getHeight());
		second.setColor(getForeground());
		paint(second);
		
		g.drawImage(image, 0, 0, this);
		
	}
	
	public void animate() {
		anim.update(10);
		hanim.update(50);
	}
	
	@Override
	public void paint (Graphics g) {
		
		if (state == GameState.Running) {
		
			g.drawImage(background, bg1.getBgX(), bg1.getBgY(), this);
			g.drawImage(background, bg2.getBgX(), bg2.getBgY(), this);
		
			paintTiles(g);
			
			ArrayList projectiles = robot.getProjectiles();
			for(int i = 0; i < projectiles.size(); i++) {
				Projectile p  = (Projectile) projectiles.get(i);
				g.setColor(Color.YELLOW);
				g.fillRect(p.getX(), p.getY(), 10, 5);
			}
			
			g.drawImage(currentSprite, robot.getCenterX() - 61, robot.getCenterY()-63, this);
			
			g.drawRect((int)robot.rect.getX(), (int)robot.rect.getY(), (int)robot.rect.getWidth(), (int)robot.rect.getHeight());
			g.drawRect((int)robot.rect2.getX(), (int)robot.rect2.getY(), (int)robot.rect2.getWidth(), (int)robot.rect2.getHeight());
			
			g.drawImage(hanim.getImage(), hb.getCenterX()-48, hb.getCenterY()-48, this);
			g.drawImage(hanim.getImage(), hb2.getCenterX()-48, hb2.getCenterY()-48, this);
		
			
			g.setFont(font);
			g.setColor(Color.WHITE);
			g.drawString(Integer.toString(score), 740, 30);
		} else if (state == GameState.Dead) {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, 800, 480);
			g.setColor(Color.WHITE);
			g.drawString("Dead", 360, 240);
		}
	}

	private void updateTiles() {
		for (int i = 0; i < tilearray.size(); i++) {
			Tile t = (Tile) tilearray.get(i);
			t.update();
		}
	}
	
	private void paintTiles(Graphics g) {
		for (int i = 0; i < tilearray.size(); i++) {
			Tile t = (Tile) tilearray.get(i);
			g.drawImage(t.getTileImage(), t.getTileX(), t.getTileY(), this);
		}
	}
	
	public static Background getBg1() {
		return bg1;
	}
	public static Background getBg2() {
		return bg2;
	}
	
	public static sample.old.Robot getRobot() {
		return robot;
	}
	
	
}
