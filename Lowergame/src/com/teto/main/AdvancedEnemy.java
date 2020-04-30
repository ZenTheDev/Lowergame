package com.teto.main;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;
public class AdvancedEnemy extends GameObject {
	private Handler handler;
	Random r = new Random();
	Color advTrailColor = new Color(255, 20, 20, 100);
	public AdvancedEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		if (Spawn.advRandomDir == 1) {
			velX = 7;
		}
		else if (Spawn.advRandomDir == 2) {
			velY = 7;
		} else {
			velY = 7;
		}
	}
	public void tick() {
		if (Game.startPressed == false) {
			handler.removeObject(this);
		}
		if (Game.gamePaused == false) {
			handler.addObject(new Trail(x, y, ID.Trail, advTrailColor, 32, 32, 0.4f, handler, true, "B"));
			x += velX;
			y += velY;
		}
		if (Spawn.advRandomDir == 1) {
			if (x > (Game.WIDTH + 200)) {
				handler.removeObject(this);
			}
		} else if (Spawn.advRandomDir == 2) {
			if (y > (Game.HEIGHT + 200)) {
				handler.removeObject(this);
			}
		} else {
			if (y > (Game.HEIGHT + 200)) {
				handler.removeObject(this);
			}
		}
	}
	public void render(Graphics g) {
		g.setColor(advTrailColor.darker().darker());
		g.setFont(new Font("Verdana", Font.PLAIN, 32));
		g.drawString("B", x-2, y+2);
		g.setColor(Color.orange);
		g.setFont(new Font("Verdana", Font.PLAIN, 32));
		g.drawString("B", x, y+2);
	}
	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 32);
	}
}