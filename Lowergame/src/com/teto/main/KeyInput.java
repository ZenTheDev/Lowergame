package com.teto.main;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
public class KeyInput extends KeyAdapter {
	private Handler handler;
	public Random r = new Random();
	public static boolean removeRespawnScreen = false;
	public static boolean hoverOpt2 = false;
	public static boolean mHoverOpt2 = false;
	public KeyInput(Handler handler) {
		this.handler = handler;
	}
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if (tempObject.getID() == ID.Player) if (HUD.HEALTH != 0) {
				if (Game.startPressed == true) {	
					if (Game.gamePaused == false) {
					if (key == KeyEvent.VK_W) tempObject.setVelY(-4);
					if (key == KeyEvent.VK_S) tempObject.setVelY(3);
					if (key == KeyEvent.VK_A) tempObject.setVelX(-4);
					if (key == KeyEvent.VK_D) tempObject.setVelX(4);
					}
				}
			}
		}
		if (key == KeyEvent.VK_ESCAPE) {	
			if (HUD.HEALTH != 0) {
				if (Game.startPressed == true) {
					if (Game.gamePaused == false) {
						Game.gamePaused = true;
					}
				}
			}
		}
		if (key == KeyEvent.VK_DOWN) {
			if (HUD.HEALTH != 0) {
				if (Game.startPressed == true) {
					if (Game.gamePaused == true) {
						hoverOpt2 = true;
					}
				}
			}
		}
		if (key == KeyEvent.VK_UP) {
			if (HUD.HEALTH != 0) {
				if (Game.startPressed == true) {
					if (Game.gamePaused == true) {
						hoverOpt2 = false;
					}
				}
			}
		}
		if (key == KeyEvent.VK_DOWN) {
			if (Game.startPressed == false) {
				mHoverOpt2 = true;
			}
		}
		if (key == KeyEvent.VK_UP) {
			if (Game.startPressed == false) {
				mHoverOpt2 = false;
			}
		}
		if (key == KeyEvent.VK_ENTER) {
			if (Game.startPressed == false) {
				if (mHoverOpt2 == false) {
					Game.startPressed = true;
					hoverOpt2 = false;
					handler.addObject(new Player((Game.WIDTH/2)-21, (Game.HEIGHT/2)-24, ID.Player, handler));
					for (int i = 0; i < 5; i++) handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 17), r.nextInt(Game.HEIGHT - 17), ID.BasicEnemy, handler));
					handler.addObject(new BasicHP(r.nextInt(Game.WIDTH - 17), r.nextInt(Game.HEIGHT - 17), ID.BasicHP, handler));
				} else {
					System.exit(0);
				}
			}
			if (HUD.HEALTH != 0) {
				if (Game.startPressed == true) {
					if (Game.gamePaused == true) {
						if (hoverOpt2 == false) {
							Game.gamePaused = false;
						} else {
							Game.gamePaused = false;
							Game.startPressed = false;
							Game.splashStr = r.nextInt(3);
						}
					}
				}
			}
			if (HUD.HEALTH == 0) {
				handler.addObject(new BasicHP(r.nextInt(Game.WIDTH - 17), r.nextInt(Game.HEIGHT - 17), ID.BasicHP, handler));
				removeRespawnScreen = true;
				HUD.HEALTH = 100;
				for (int i = 0; i < 5; i++) handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 17), r.nextInt(Game.HEIGHT - 17), ID.BasicEnemy, handler));
				HUD.playerLevel = 1;
				Spawn.playerScoreKeep = 0;
				HUD.playerScore = 0;
				
				removeRespawnScreen = false;
				for (int i = 0; i < handler.object.size(); i++) {
					GameObject tempObject = handler.object.get(i);
					if (tempObject.getID() == ID.BasicEnemy) {
						int d;
						d = r.nextInt();
						if (d == 1) {
							tempObject.setVelX(5);
							tempObject.setVelY(5);
						} else if (d == 2) {
							tempObject.setVelX(-5);
							tempObject.setVelY(-5);
						} else if (d == 3) {
							tempObject.setVelX(5);
							tempObject.setVelY(-5);
						} else if (d == 6) {
							tempObject.setVelX(-5);
							tempObject.setVelY(5);
						} else {
							tempObject.setVelX(5);
							tempObject.setVelY(5);
						}
					}
				}
			}
		}
	}	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		char kchar = Character.toUpperCase(e.getKeyChar());
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if (tempObject.getID() == ID.Player) {
				if (key == KeyEvent.VK_W) tempObject.setVelY(0);
				if (key == KeyEvent.VK_S) tempObject.setVelY(0);
				if (key == KeyEvent.VK_A) tempObject.setVelX(0);
				if (key == KeyEvent.VK_D) tempObject.setVelX(0);
			}
		}
		if (HUD.HEALTH > 0) {
			System.out.println("\nKEYCODE: " + key + "\nKEYCHAR: " + kchar);
		}
	}
}