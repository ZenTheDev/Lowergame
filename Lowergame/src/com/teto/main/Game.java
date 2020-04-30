package com.teto.main;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Robot;
import java.awt.image.BufferStrategy;
import java.util.Random;
import javax.swing.ImageIcon;
public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = -2953640318162687714L;
	@SuppressWarnings("hiding") public static final int WIDTH = 1280, HEIGHT = 720;
	private Thread thread;
	private boolean running = false;
	private Handler handler;
	private Random r;
	private Robot robot;
	private HUD hud;
	private Spawn spawner;
	public static boolean startPressed = false;
	public Color f_ = new Color(0, 0, 0, 100);
	public Color f__ = new Color(0, 0, 0, 50);
	public static volatile boolean gamePaused = false;
	public static int splashStr;
	private int overlayX_1;private int overlayY_1;
	private int overlayX_2;private int overlayY_2;
	private int overlayX_3;private int overlayY_3;
	private int overlayX_4;private int overlayY_4;
	private int overlayX_5;private int overlayY_5;
	private int overlayX_6;private int overlayY_6;
	public Game() {
		handler = new Handler();
		this.addKeyListener(new KeyInput(handler));
		this.addMouseListener(new MouseInput(handler));
		new FullFrame(WIDTH, HEIGHT, this);
		hud = new HUD();
		spawner = new Spawn(handler, hud);
		r = new Random(); 
		splashStr = r.nextInt(3);
		overlayX_1 = r.nextInt(WIDTH);overlayY_1 = r.nextInt(HEIGHT);
		overlayX_2 = r.nextInt(WIDTH);overlayY_2 = r.nextInt(HEIGHT);
		overlayX_3 = r.nextInt(WIDTH);overlayY_3 = r.nextInt(HEIGHT);
		overlayX_4 = r.nextInt(WIDTH);overlayY_4 = r.nextInt(HEIGHT);
		overlayX_5 = r.nextInt(WIDTH);overlayY_5 = r.nextInt(HEIGHT);
		overlayX_6 = r.nextInt(WIDTH);overlayY_6 = r.nextInt(HEIGHT);
	}
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch (Exception err) {
			err.printStackTrace();
		}
	}
	public void run() {
		try {
			robot = new Robot();
		} 
			catch (Throwable e) {
		}
		this.requestFocus();
		long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        @SuppressWarnings("unused") int frames = 0;
        while(running) {
        	long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >=1) {
            	tick();
            	delta--;
            }
            if(running)
            	render();
            frames++;
            if(System.currentTimeMillis() - timer > 500) {
            	timer += 500;
                frames = 0;
            }
        }
        stop();
	}
	private void tick() {
		if (startPressed == true) {
			if (HUD.HEALTH != 0) {
				if (gamePaused == false) {
					robot.mouseMove(WIDTH/2, HEIGHT/2);
				}
			}
		}
		handler.tick();
		hud.tick();
		spawner.tick();
		FullFrame.tick();
	}
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		Color modDarkGrey = new Color(30, 30, 40);Color midGrey = new Color(28, 28, 38);Color midCyan = new Color(40, 70, 255);
		Color d1mC = new Color(30, 40, 220);Color d2mC = new Color(35, 40, 235);Color d3mC = new Color(10, 20, 190);
		Color omidCyan = new Color(40, 70, 255, 40);Color od1mC = new Color(30, 40, 220, 40);Color od2mC = new Color(35, 40, 235, 40);
		Color od3mC = new Color(10, 20, 190, 40);if (startPressed == false) {
			Graphics2D g2d = (Graphics2D) g;
			g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g.setColor(modDarkGrey.darker()); g.fillRect(0, 0, WIDTH, HEIGHT);String t = "LOWERGAME";
			g.setColor(new Color(0, 0, 0, 30)); g.fillRoundRect((WIDTH/2-168)-12, HEIGHT/3-45,310,64,30,30);g.setFont(new Font("Verdana",Font.PLAIN,45));
			g.setColor(od3mC);g.drawString(t,(WIDTH/2-168)-8,HEIGHT/3+7);g.setColor(od2mC);g.drawString(t,(WIDTH/2-170)-6,HEIGHT/3+4);
			g.setColor(od1mC);g.drawString(t,(WIDTH/2-173)-4,HEIGHT/3+3);g.setColor(omidCyan);g.drawString(t,(WIDTH/2-175)-2,HEIGHT/3);
			g.setColor(d3mC);g.drawString(t,WIDTH/2-168,HEIGHT/3+7);g.setColor(d2mC.darker());g.drawString(t,WIDTH/2-170,HEIGHT/3+4);
			g.setColor(d1mC);g.drawString(t,WIDTH/2-173, HEIGHT/3+3);g.setColor(midCyan);g.drawString(t,WIDTH/2-175,HEIGHT/3);
			g.setColor(new Color(0,0,0,20)); 
			g.fillRoundRect((WIDTH/2-63)-10,HEIGHT/3+65,100,50,15,15);
			g.setFont(new Font("Courier",Font.BOLD,35));
			g.setColor(Color.black);
			g.drawString("Play",WIDTH/2-63,HEIGHT/2-18);
			g.setColor(new Color(255,180,0));
			g.drawString("Play",WIDTH/2-65,HEIGHT/2-20);
			g.setColor(new Color(0,0,0,20)); 
			g.fillRoundRect((WIDTH/2-63)-120,HEIGHT/3+128,330,50,15,15);
			g.setFont(new Font("Courier",Font.BOLD,35));
			g.setColor(Color.black);
			g.drawString("Exit to Desktop",WIDTH/2-173,HEIGHT/2+46);
			g.setColor(new Color(255,180,0));
			g.drawString("Exit to Desktop",WIDTH/2-175,HEIGHT/2+44);
			g.setFont(new Font("Verdana", Font.PLAIN, 15));
			g.setColor(new Color(255,255,255,100));
			int splashX = 685;int splashY = 310;g.setFont(new Font("Verdana", Font.BOLD, 12));
			g2d.setColor(new Color(255,200,50,200));
			if (splashStr == 1) {
				drawRotate(g2d, splashX, splashY, -35, "The title isn't uppercase it's just");
				drawRotate(g2d, splashX-50, splashY+50, -35, "                        a different font I swear!");
			} else if (splashStr == 2) {
				drawRotate(g2d, splashX, splashY, -35, "A game based off of a discord server!");
			} else {
				drawRotate(g2d, splashX, splashY, -35, "This game only uses three sprites!");
			}
			Image image = new ImageIcon(this.getClass().getResource("/lgico.png")).getImage();
			g.drawImage(image, 5, HEIGHT-89, 128, HEIGHT+132, 0, 0, 272, 512, null);
			g.setFont(new Font("Verdana", Font.PLAIN, 12));
			g.drawString("<- Visit the game page", 70, HEIGHT-36);
		}
		if (startPressed == true) {
			Graphics2D g2d = (Graphics2D) g;
			g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g2d.setColor(midGrey);g.fillRect(0, 0, WIDTH, HEIGHT);
				g2d.setColor(modDarkGrey);
				g2d.fillRoundRect(20,20, WIDTH-40, HEIGHT-70, 50, 50);
			g2d.setFont(new Font("Verdana", Font.PLAIN, 15));g.setColor(new Color(255, 255, 255, 100));
			Image overlay_1 = new ImageIcon(this.getClass().getResource("/brick_overlay_1.png")).getImage();
			Image overlay_2 = new ImageIcon(this.getClass().getResource("/brick_overlay_2.png")).getImage();
			Image overlay_3 = new ImageIcon(this.getClass().getResource("/brick_overlay_3.png")).getImage();
			g.drawImage(overlay_1, overlayX_1, overlayY_1, overlayX_1+64, overlayY_1+64, 0, 0, 64, 64, null);
			g.drawImage(overlay_2, overlayX_2, overlayY_2, overlayX_2+64, overlayY_2+64, 0, 0, 64, 64, null);
			g.drawImage(overlay_3, overlayX_3, overlayY_3, overlayX_3+64, overlayY_3+64, 0, 0, 64, 64, null);
			g.drawImage(overlay_1, overlayX_4, overlayY_4, overlayX_4+64, overlayY_4+64, 0, 0, 64, 64, null);
			g.drawImage(overlay_2, overlayX_5, overlayY_5, overlayX_5+64, overlayY_5+64, 0, 0, 64, 64, null);
			g.drawImage(overlay_3, overlayX_6, overlayY_6, overlayX_6+64, overlayY_6+64, 0, 0, 64, 64, null);
		}
		handler.render(g);
		hud.render(g);
		if (HUD.HEALTH == 0) {
			Graphics2D g2d = (Graphics2D) g;
			g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			g2d.setColor(f__);g.fillRoundRect(WIDTH/2-130, HEIGHT/2-30, 255, 50, 20, 20);
			g2d.setColor(f_);g.fillRect(0,0,WIDTH,HEIGHT);g2d.setFont(new Font("Verdana",Font.PLAIN,45));
			g2d.setColor(Color.black);g2d.drawString("You died...",(WIDTH/2)-118,(HEIGHT/2)+12);
			g2d.setColor(Color.red);g2d.drawString("You died...",(WIDTH/2)-120,(HEIGHT/2)+10);
			g2d.setFont(new Font("Verdana", Font.PLAIN, 25));g2d.setColor(Color.black);
			g2d.drawString("<ENTER>",(WIDTH/2)-140, (HEIGHT/2)+62);g2d.setColor(Color.yellow);
			g2d.drawString("<ENTER>",(WIDTH/2)-142, (HEIGHT/2)+60);g2d.setColor(Color.black);
			g2d.drawString("to retry...",((WIDTH/2)-140)+150,(HEIGHT/2)+60);g2d.setColor(Color.yellow);
			g2d.drawString("to retry...",((WIDTH/2)-143)+150,(HEIGHT/2)+60);g2d.setFont(new Font("Verdana",Font.BOLD,15));
			g2d.setColor(Color.black);g2d.drawString("Score: "+HUD.playerScore,(WIDTH/2)-118,(HEIGHT/3)+80);
			g2d.setColor(Color.yellow);g2d.drawString("Score: "+HUD.playerScore,(WIDTH/2)-120,(HEIGHT/3)+78);
			g2d.setColor(Color.black);g2d.drawString("Level: "+HUD.playerLevel,(WIDTH/2)-118,(HEIGHT/3)+60);
			g2d.setColor(Color.yellow);g2d.drawString("Level: "+HUD.playerLevel,(WIDTH/2)-120,(HEIGHT/3)+58);
		}
		if (startPressed == true) {
			if (gamePaused == true) {
				// PAUSE TITLE
				Graphics2D g2d = (Graphics2D) g;
				g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
				g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE);
				g2d.setColor(new Color(0, 0, 0, 50));
				g2d.fillRect(0, 0, WIDTH, HEIGHT);
				g2d.setFont(new Font("Verdana",Font.BOLD,25));
				g2d.setColor(new Color(40, 40, 155));
				g2d.drawString("Game Paused...", WIDTH/2-108-10, HEIGHT/2+2-50);
				g2d.setColor(new Color(70, 100, 240));
				g2d.drawString("Game Paused...", WIDTH/2-110-10, HEIGHT/2-50);
				int xpoints[] = {25+735-10, 50+735-10, 25+735-10};
			    int ypoints[] = {(25+280)-20, (42+279)-20, (145+190)-20};
			    int xpoints2[] = {25+737-10, 50+737-10, 25+737-10};
			    int ypoints2[] = {(28+280)-20, (45+279)-20, (148+190)-20};
			    int npoints = 3;
			    g2d.setColor(new Color(40, 40, 155));
				g2d.fillPolygon(xpoints2, ypoints2, npoints);
				g2d.setColor(new Color(70, 100, 240));
				g2d.fillPolygon(xpoints, ypoints, npoints);
				// PAUSE OPTION 1 (RESUME)
				g2d.setColor(new Color(0,0,0,50));
				g2d.fillRoundRect((WIDTH/2-63)-58,HEIGHT/3+85,100,35,15,15);
				g2d.setFont(new Font("Courier",Font.BOLD,25));
				g2d.setColor(Color.black);
				g2d.drawString("Resume",WIDTH/2-113,HEIGHT/2-18+10);
				g2d.setColor(new Color(0,180,255));
				g2d.drawString("Resume",WIDTH/2-115,HEIGHT/2-20+10);
				// PAUSE OPTION 2 (EXIT TO MAIN MENU)
				g2d.setColor(new Color(0,0,0,50));
				g2d.fillRoundRect((WIDTH/2-63)-58,HEIGHT/3+125,268,35,15,15);
				g2d.setFont(new Font("Courier",Font.BOLD,25));
				g2d.setColor(Color.black);
				g2d.drawString("Exit to Main Menu",WIDTH/2-113,HEIGHT/2-8+40);
				g2d.setColor(new Color(0,180,255));
				g2d.drawString("Exit to Main Menu",WIDTH/2-115,HEIGHT/2-10+40);
				g.setColor(new Color(0,180,255));
			}
		}
		g.dispose();
		bs.show();
	}
	public static int clamp(int var, int min, int max) {
		if (var >= max)
			return var = max;
		else if (var <= min)
			return var = min;
		return var;
	}
	public static void drawRotate(Graphics2D g2d, double x, double y, int angle, String text) {    
		g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2d.translate((float)x,(float)y);
	    g2d.rotate(Math.toRadians(angle));
	    g2d.drawString(text,0,0);
	    g2d.rotate(-Math.toRadians(angle));
	    g2d.translate(-(float)x,-(float)y);
	}    
	public static void main(String[] args) {
		new Game();
	}
}