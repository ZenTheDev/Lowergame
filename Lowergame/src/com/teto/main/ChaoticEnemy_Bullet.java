package com.teto.main;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;
public class ChaoticEnemy_Bullet extends GameObject {
	private Handler handler;
	Random r = new Random();
	public ChaoticEnemy_Bullet(int x, int y, ID id, Handler handler, int dir) {
		super(x, y, id);
		this.handler = handler;
		if (HUD.HEALTH != 0) {
			if (dir == 1) {
				velX = -6;
				velY = 0;
			} else if (dir == 2) {
				velX = 0;
				velY = 6;
			} else if (dir == 3) {
				velX = 6;
				velY = 0;
			} else if (dir == 4) {
				velX = 0;
				velY = -6;
			} else {
				System.out.println("\nInvalid direction, 1 - 4\n1_LEFT\n2_DOWN\n3_RIGHT\n4_UP");
				velX = -6;
				velY = 0;
			}
		} else {
			velX = 0;
			velY = 0;
		}
	}
	public void tick() {
		if (Game.startPressed == false) {
			handler.removeObject(this);
		}
		if (Game.gamePaused == false) {
			handler.addObject(new Trail(x, y+9, ID.Trail, new Color(255, 200, 50, 10), 32, 32, 0.1f, handler, true, "o"));
			x += velX;
			y += velY;
		}
		collision();
		if (((x) <= 0) || (x > (Game.WIDTH + 200))) {
			handler.removeObject(this);
		}
		if (((y) <= 0) || (y > (Game.HEIGHT + 200))) {
			handler.removeObject(this);
		}
	}
	private void collision() {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if (tempObject.getID() == ID.Player) {
				if (getBounds().intersects(tempObject.getBounds())) {
					if (Game.gamePaused == false) {
						HUD.HEALTH -= 8;
						handler.removeObject(this);
					}
				}
			}
		}
	}
	public void render(Graphics g) {
		g.setColor(new Color(255, 150, 40));
		g.fillOval(x, y, 10, 10);
	}
	public Rectangle getBounds() {
		return new Rectangle(x, y, 12, 12);
	}
}
