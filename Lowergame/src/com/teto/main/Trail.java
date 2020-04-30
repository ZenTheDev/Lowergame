package com.teto.main;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
public class Trail extends GameObject {
	private float alpha = 1;
	private Handler handler;
	private Color color;
	private float life;
	private int width, height;
	private boolean isString;
	private String str;
	public Trail(int x, int y, ID id, Color color, int width, int height, float life, Handler handler, boolean isString, String str) {
		super(x, y, id);
		this.handler = handler;
		this.color = color;
		this.width = width;
		this.height = height;
		this.life = life;
		this.isString = isString;
		this.str = str;
	}
	public void tick() {
		if (Game.startPressed == false) {
			handler.removeObject(this);
		}
		if (alpha > life) {
			alpha -= (life - 0.01f);
		} else handler.removeObject(this);
		if (((x) <= 0) || (x > (Game.WIDTH + 200))) {
			handler.removeObject(this);
		}
		if (((y) <= 0) || (y > (Game.HEIGHT + 200))) {
			handler.removeObject(this);
		}
	}
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setComposite(makeTransparent(alpha));
		g.setFont(new Font("Verdana", Font.PLAIN, 20));
		g.setColor(color);
		if (isString == true) g.drawString(str, x, y+2);
		else {
			g.fillRect(x, y, width, height);
		}
		g2d.setComposite(makeTransparent(1));	
	}
	private AlphaComposite makeTransparent(float alpha1) {
		int type = AlphaComposite.SRC_OVER;
		return(AlphaComposite.getInstance(type, alpha1));
	}
	public Rectangle getBounds() {
		return null;
	}
}