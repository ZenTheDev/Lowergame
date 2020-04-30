package com.teto.main;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;
public class BasicEnemy extends GameObject {
	private Handler handler;
	Random r = new Random();
	public BasicEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		int f = r.nextInt(4);
		if (f == 1) {
			velX = 5;
			velY = 5;
		} else if (f == 2) {
			velX = -5;
			velY = 5;
		} else if (f == 3) {
			velX = 5;
			velY = -5;
		} else if (f == 4) {
			velX = -5;
			velY = -5;
		} else {
			velX = 5;
			velY = 5;
		}
	}
	public Rectangle getBounds() {
		return new Rectangle(x, y, 16, 16);
	}
	public void tick() {
		if (Game.startPressed == false) {
			handler.removeObject(this);
		}
		if (Game.gamePaused == false) {
			handler.addObject(new Trail(x, y, ID.Trail, Color.red, 16, 16, 0.4f, handler, true, "b"));
			handler.addObject(new Glow(x-7, y-13, ID.Glow, handler, 255, 0, 0, 0.50f, "Oval"));
			x += velX;
			y += velY;
			if ((y-16) <= 0 || y >= Game.HEIGHT - 16) {
				if ((y-16) <= 0) {
					y += 2;
				}
				if (y >= Game.HEIGHT - 16) {
					y -= 2;
				}
				velY *= -1;
				int c = r.nextInt(4);
				if (c == 1) {
					velX *= -1;
				} else if (c == 2) {
					velY *= -1;
					velX = 0;
				} else if (c == 3) {
					velX = 5;
				}
			}
			if ((x) <= 0 || x >= Game.WIDTH - 16) {
				if ((x) <= 0) {
					x += 2;
				}
				if (x >= Game.WIDTH - 16) {
					x -= 2;
				}
				velX *= -1;
				int c = r.nextInt(2);
				if (c == 1) {
					velY *= -1;
				}
			}
		}
		if (HUD.HEALTH == 0) {
			handler.removeObject(this);
		}
	}
	public void render(Graphics g) {
		Color rOrange = new Color(255, 38, 0);
		Color shadowColor = new Color(30, 30, 30);
		g.setColor(shadowColor);
		g.setFont(new Font("Verdana", Font.PLAIN, 20));
		g.drawString("b", x, y+4);
		g.setColor(rOrange);
		g.setFont(new Font("Verdana", Font.PLAIN, 20));
		g.drawString("b", x, y+4);
	}
}