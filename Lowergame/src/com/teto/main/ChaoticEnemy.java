package com.teto.main;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;
public class ChaoticEnemy extends GameObject {
	private Handler handler;
	Random r = new Random();
	public int g = 0;
	public int h = 0;
	public static int ChaoticEnemy_Bullet_Speed = 30;
	public ChaoticEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
	}
	public void tick() {
		if (Game.startPressed == false) {
			handler.removeObject(this);
		}
		if (Game.gamePaused == false) {
			x += velX;
			y += velY;
		}
		if (HUD.HEALTH == 0) {
			handler.removeObject(this);
		}
		if (HUD.HEALTH != 0) {
			if (Game.gamePaused == false) {
				g++;
				if (g >= ChaoticEnemy_Bullet_Speed) {
					g = 0;
					h++;
					if (h == 1) {
						handler.addObject(new ChaoticEnemy_Bullet(x, y-30, ID.ChaoticEnemy_Bullet, handler, 1));
					} else if (h == 2) {
						handler.addObject(new ChaoticEnemy_Bullet(x+16, y, ID.ChaoticEnemy_Bullet, handler, 2));
					} else if (h == 3) {
						handler.addObject(new ChaoticEnemy_Bullet(x+35, y-30, ID.ChaoticEnemy_Bullet, handler, 3));
					} else if (h == 4) {
						handler.addObject(new ChaoticEnemy_Bullet(x+16, y-60, ID.ChaoticEnemy_Bullet, handler, 4));
						h = 0;
					}
				}
			}
			if ((HUD.playerLevel >= 9) && (HUD.playerLevel <= 19)) {
				if ((y-16) <= 64 || y >= Game.HEIGHT - 64) {
					velY *= -1;
					if ((y) <= 64) {
						y += 15;
					} else if (y >= Game.HEIGHT - 64) {
						y -= 15;
					}
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
				if ((x) <= 64 || x >= Game.WIDTH - 64) {
					velX *= -1;
					if ((x) <= 64) {
						x += 15;
					} else if (x >= Game.WIDTH - 64) {
						x -= 15;
					}
					int c = r.nextInt(2);
					if (c == 1) {
						velY *= -1;
					}
				}
			} else if (HUD.playerLevel >= 20) {
				if ((y-64) <= 64 || y >= Game.HEIGHT - 64) {
					velY *= -1;
					if ((y) <= 64) {
						y += 15;
					} else if (y >= Game.HEIGHT - 64) {
						y -= 15;
					}
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
				if ((x) <= 64 || x >= Game.WIDTH - 64) {
					velX *= -1;
					if ((x) <= 64) {
						x += 15;
					} else if (x >= Game.WIDTH - 64) {
						x -= 15;
					}
					int c = r.nextInt(2);
					if (c == 1) {
						velY *= -1;
					}
				}
			}
		}
	}
	@SuppressWarnings("hiding")
	public void render(Graphics g) {
		g.setFont(new Font("Verdana", Font.PLAIN, 60));
		g.setColor(Color.black);
		g.drawString("A", x+1, y-6);
		g.setFont(new Font("Verdana", Font.PLAIN, 62));
		g.setColor(Color.red.darker());
		g.drawString("A", x+1, y+2);
		g.setFont(new Font("Verdana", Font.PLAIN, 64));
		g.setColor(Color.red);
		g.drawString("A", x, y);
	}
	public Rectangle getBounds() {
		return new Rectangle(x, y, 64, 64);
	}
}