package com.teto.main;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;
public class Player extends GameObject {
	Random r = new Random();
	Handler handler;
	int sizeX = 36;
	int sizeY = 36;
	public static int ShieldCounter = 0;
	public static int clShieldCounter = 0;
	public static int secLeftClShield = 10;
	public static boolean hasShield = false;
	String secLeft;
	public Player(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
	}
	public Rectangle getBounds() {
		return new Rectangle(x, y, 36, 36);
	}
	public void tick() {
		secLeft = String.valueOf(secLeftClShield);
		Game.clamp(secLeftClShield, 0, 15);
		if (Game.startPressed == false) {
			handler.removeObject(this);
		}
		if (Game.gamePaused == false) {
			if (HUD.HEALTH != 0) {
				x += velX;
				y += velY;
				if (hasShield == true) {
					ShieldCounter++;
					clShieldCounter++;
					if (ShieldCounter >= 500) {
						ShieldCounter = 0;
						hasShield = false;
					}
					if (clShieldCounter >= 50) {
						secLeftClShield--;
						clShieldCounter = 0;
					}
				}
			}
		}
		x = Game.clamp(x, 2, Game.WIDTH-44);
		y = Game.clamp(y, 2, Game.HEIGHT-66);
		Color opaqueAqua = new Color(120, 120, 255, 10);
		handler.addObject(new Trail(x, y, ID.Trail, opaqueAqua, 38, 38, 0.09f, handler, false, ""));
		collision();
	}
	private void collision() {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if (tempObject.getID() == ID.BasicEnemy) {
				if (getBounds().intersects(tempObject.getBounds())) {
					if (Game.gamePaused == false) {
						if (hasShield == false) {
							HUD.HEALTH -= 2;
						}
					}
				}
			} else if (tempObject.getID() == ID.AdvancedEnemy) {
				if (getBounds().intersects(tempObject.getBounds())) {
					if (Game.gamePaused == false) {
						if (hasShield == false) {
							HUD.HEALTH -= 10;
						}
					}
				}
			} else if (tempObject.getID() == ID.BasicHP) {
				if (getBounds().intersects(tempObject.getBounds())) {
					if (Game.gamePaused == false) {
						HUD.HEALTH += 10;
						if (HUD.HEALTH > 100) {
							HUD.HEALTH = 100;
						}
					}
				}
			}
		}
	}
	public void render(Graphics g) {
		if (velX != 0) {
			if (HUD.HEALTH != 0) {
				sizeY = 34;
			}
		} else if (velX == 0) {
			sizeY = 36;
		}
		if (velY != 0) {
			if (HUD.HEALTH != 0) {
				sizeX = 34;
			}
		} else if (velY == 0) {
			sizeX = 36;
		}
		Color playerBC = new Color(40, 40, 255, 255);
		Color shadowColor = new Color(30, 30, 30, 10);
		int playerArc = 4;
		g.setColor(shadowColor);
		g.fillRoundRect(x-6, y+1, sizeX+6, sizeY+6, playerArc, playerArc);
		g.setColor(playerBC.brighter().brighter());
		g.fillRoundRect(x-2, y-2, sizeX+4, sizeY+4, playerArc, playerArc);
		g.setColor(playerBC.brighter().brighter().brighter().brighter());
		g.fillRoundRect(x, y, sizeX, sizeY, playerArc, playerArc);
		if (hasShield == true) {
			if (secLeftClShield >= 10) {
				g.drawString(secLeft, x+7, y-10);
			} else {
				g.drawString("0" + secLeft, x+7, y-10);
			}
		}
		g.setFont(new Font("Comic Sans", Font.PLAIN, 36));
		g.setColor(new Color(30, 30, 30, 50));
		g.drawString("a", x+8, y+27);
	}
}