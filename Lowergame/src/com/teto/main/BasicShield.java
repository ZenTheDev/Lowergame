package com.teto.main;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
public class BasicShield extends GameObject {
	private Handler handler;
	public BasicShield(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
	}
	public void tick() {
		if (Game.startPressed == false) {
			handler.removeObject(this);
		}
		if (HUD.HEALTH == 0) handler.removeObject(this);
		collision();
	}
	private void collision() {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if (tempObject.getID() == ID.Player) {
				if (getBounds().intersects(tempObject.getBounds())) {
					handler.removeObject(this);
					if (Player.hasShield == false) {
						Player.hasShield = true;
						Player.ShieldCounter = 0;
						Player.clShieldCounter = 0;
						Player.secLeftClShield = 10;
					}
				}
			}
		}
	}
	public void render(Graphics g) {
		g.setColor(new Color(180, 180, 255, 10));
		g.fillOval(x-9, y-21, 30, 30);
		g.setColor(new Color(180, 180, 255, 20));
		g.fillOval(x-6, y-18, 24, 24);
		g.setColor(new Color(100, 100, 255, 100));
		g.setFont(new Font("Verdana", Font.BOLD, 20));
		g.drawString("a", x, y);
		g.setColor(new Color(100, 100, 255, 150));
		g.setFont(new Font("Verdana", Font.BOLD, 18));
		g.drawString("a", x+1, y-1);
	}
	public Rectangle getBounds() {
		return new Rectangle(x, y-20, 36, 36);
	}
}