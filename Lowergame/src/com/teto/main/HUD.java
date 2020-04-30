package com.teto.main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Robot;
import java.awt.Toolkit;
public class HUD {
	public static int HEALTH = 100;
	public static int playerScore = 0;
	public static int playerLevel = 1;
	public static int blinkTimer = 0;
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	Robot robot;
	public Color darkerAqua = new Color(60, 60, 255);
	public void tick() {
		try {
			robot = new Robot();
		} 
			catch (Throwable e) {
		}
		HEALTH = Game.clamp(HEALTH, 0, 100);
		if (HUD.HEALTH > 0) {
			if (Game.startPressed == true) {
				if (Game.gamePaused == false) {
					playerScore++;
				}
			}
		}
		blinkTimer++;
		if (blinkTimer >= 100) {
			blinkTimer = 0;
		}
	}
	public void render(Graphics g) {
		if (Game.startPressed == true) {
			if (HUD.HEALTH != 0) {
				if (Game.gamePaused == false) {
					robot.mouseMove(screenSize.width/2, screenSize.height/2);
				}
			}
		}
		if (Game.startPressed == true) {
			Color darkRed = new Color(100, 0, 0);
			g.setColor(Color.black);
			g.fillRoundRect(10, 10, 204, 20, 10, 10);
			g.setColor(darkRed);
			g.fillRoundRect(12, 12, 200, 16, 10, 10);
			if (HEALTH >= 65) g.setColor(Color.green);
			else if ((HEALTH < 65) && HEALTH >= 25) {
				g.setColor(Color.yellow);
			} else {
				g.setColor(Color.red);
			}
			g.fillRoundRect(12, 12, HEALTH * 2, 16, 10, 10);
			g.setFont(new Font("Verdana", Font.PLAIN, 14));
			g.setColor(Color.black);
			g.drawString("Health: " + HEALTH + "/100", 14, 26);
			if (playerScore > 1000) g.setColor(Color.black);
			g.drawString("Pts: " + playerScore + " | " + (1000 - Spawn.playerScoreKeep) + " more before lvl " + (playerLevel+1), 12, 49);
			g.drawString("Lvl: " + playerLevel, 12, 65);
			g.setColor(new Color(0, 0, 0, 55));
			g.fillRoundRect(6, 33, 300, 36, 15, 15);
			if (playerScore == 0) g.setColor(Color.red);
			else if ((playerScore > 0) && (playerScore < 500)) g.setColor(Color.orange);
			else if ((playerScore > 500) && (playerScore < 1350)) g.setColor(Color.yellow);
			else if ((playerScore > 1350) && (playerScore < 5000)) g.setColor(Color.green);
			else g.setColor(darkerAqua);
			g.drawString("Pts: " + playerScore + " | " + (1000 - Spawn.playerScoreKeep) + " more before lvl " + (playerLevel+1), 10, 48);
			g.drawString("Lvl: " + playerLevel, 10, 64);
			g.setFont(new Font("Verdana", Font.BOLD, 24));
			if (Game.gamePaused == false) {
				Graphics2D g2d = (Graphics2D) g;
				g2d.setColor(new Color(255, 255, 255, 100));
				drawRotate(g2d, (Game.WIDTH/2)-25, (Game.HEIGHT/2)+2, 0, "-+-");
				drawRotate(g2d, (Game.WIDTH/2)-10, (Game.HEIGHT/2)-28, 90, "-+-");
			} else {
				if (blinkTimer >= 30) {
					if (KeyInput.hoverOpt2 == false) {
						g.setFont(new Font("Serif", Font.BOLD, 30));
						g.setColor(Color.black);
						g.drawString("<", 632-5, 352+2);
						g.setColor(new Color(100, 240, 255));
						g.drawString("<", 630-5, 350+2);
					} else if (KeyInput.hoverOpt2 == true) {
						g.setFont(new Font("Serif", Font.BOLD, 30));
						g.setColor(Color.black);
						g.drawString("<", 632-5+170, 352+43);
						g.setColor(new Color(100, 240, 255));
						g.drawString("<", 630-5+170, 350+43);
					}
				}
			}
		} else if (Game.startPressed == false) {
			if (blinkTimer >= 30) {
				if (KeyInput.mHoverOpt2 == false) {
					g.setFont(new Font("Serif", Font.BOLD, 30));
					g.setColor(Color.black);
					g.drawString("<", 682, 347-2);
					g.setColor(Color.orange);
					g.drawString("<", 680, 345-2);
				} else if (KeyInput.mHoverOpt2 == true) {
					g.setFont(new Font("Serif", Font.BOLD, 30));
					g.setColor(Color.black);
					g.drawString("<", 682+120, 347+60);
					g.setColor(Color.orange);
					g.drawString("<", 680+120, 345+60);
				}
			}
		}
	}
	public static void drawRotate(Graphics2D g2d, double x, double y, int angle, String text) {    
	    g2d.translate((float)x,(float)y);
	    g2d.rotate(Math.toRadians(angle));
	    g2d.drawString(text,0,0);
	    g2d.rotate(-Math.toRadians(angle));
	    g2d.translate(-(float)x,-(float)y);
	}
}