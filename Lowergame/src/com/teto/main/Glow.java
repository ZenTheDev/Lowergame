package com.teto.main;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
public class Glow extends GameObject {
	private Handler handler;
	private float multiplier;
	private String shape;
	private int rVal;
	private int gVal;
	private int bVal;
	private int tickDec = 0;
	public Glow(int x, int y, ID id, Handler handler, int rVal, int gVal, int bVal, float multiplier, String shape) {
		super(x, y, id);
		this.handler = handler;
		this.multiplier = multiplier;
		this.shape = shape;
		this.rVal = rVal;
		this.gVal = gVal;
		this.bVal = bVal;
	}
	public void tick() {
		tickDec++;
		if (Game.startPressed == false) {
			handler.removeObject(this);
		}
		if (((x) <= 0) || (x > (Game.WIDTH + 200))) {
			handler.removeObject(this);
		}
		if (((y) <= 0) || (y > (Game.HEIGHT + 200))) {
			handler.removeObject(this);
		}
		if (tickDec >= 3) {
			handler.removeObject(this);
		}
	}
	public void render(Graphics g) {
		g.setColor(new Color(rVal, gVal, bVal, 10));
		if (shape == "Oval") {
			g.fillOval(x, y, (int)(50*multiplier), (int)(50*multiplier));
		} else if (shape == "Square") {
			g.fillRect(x, y, (int)(50*multiplier), (int)(50*multiplier));
		} else {
			System.out.println("specify a valid shape: Square or Oval");
			g.fillOval(x, y, (int)(50*multiplier), (int)(50*multiplier));
		}
	}
	public Rectangle getBounds() {
		return null;
	}
}