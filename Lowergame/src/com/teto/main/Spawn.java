package com.teto.main;
import java.util.Random;
public class Spawn {
	private Handler handler;
	@SuppressWarnings("unused")
	private HUD hud;
	private Random r = new Random();
	public static int playerScoreKeep;
	public static int playerScoreKeepShield;
	public static int advRandomDir;
	public Spawn(Handler handler, HUD hud) {
		this.handler = handler;
		this.hud = hud;
	}
	public void tick() {
		if (Game.startPressed == true) {
			if (HUD.HEALTH != 0) {
				if (Game.gamePaused == false) {
					playerScoreKeep++;
					playerScoreKeepShield++;
					if (playerScoreKeepShield >= 3000) {
						playerScoreKeepShield = 0;
						handler.addObject(new BasicShield(r.nextInt(Game.WIDTH - 17), r.nextInt(Game.HEIGHT - 17), ID.BasicShield, handler));
					}
					if (playerScoreKeep >= 1000) {
						playerScoreKeep = 0;
						HUD.playerLevel++;
						handler.addObject(new BasicHP(r.nextInt(Game.WIDTH - 17), r.nextInt(Game.HEIGHT - 17), ID.BasicHP, handler));
						for (int g = 0; g < 2; g++) handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 17), r.nextInt(Game.HEIGHT - 17), ID.BasicEnemy, handler));
						if (HUD.playerLevel >= 5) {
							advRandomDir = r.nextInt(3);
							if (advRandomDir == 1) { 
								handler.addObject(new AdvancedEnemy(10, r.nextInt(Game.HEIGHT), ID.AdvancedEnemy, handler));
							}
							else if (advRandomDir == 2) {
								handler.addObject(new AdvancedEnemy(r.nextInt(Game.WIDTH), 32, ID.AdvancedEnemy, handler));
							} else {
								handler.addObject(new AdvancedEnemy(r.nextInt(Game.WIDTH), 32, ID.AdvancedEnemy, handler));
							}
							handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 17), r.nextInt(Game.HEIGHT - 17), ID.BasicEnemy, handler));
							if (HUD.playerLevel == 8) {
								handler.addObject(new ChaoticEnemy(Game.WIDTH/2, Game.HEIGHT/2, ID.ChaoticEnemy, handler));
							} else if (HUD.playerLevel == 9) {
								ChaoticEnemy.ChaoticEnemy_Bullet_Speed = 25;
								for (int i = 0; i < handler.object.size(); i++) {
									GameObject tempObject = handler.object.get(i);
									if (tempObject.getID() == ID.ChaoticEnemy) if (HUD.HEALTH != 0) {
										tempObject.velX = 2;
										tempObject.velY = 2;
									}
								}
							} else if (HUD.playerLevel == 15) {
								ChaoticEnemy.ChaoticEnemy_Bullet_Speed = 20;
								for (int i = 0; i < handler.object.size(); i++) {
									GameObject tempObject = handler.object.get(i);
									if (tempObject.getID() == ID.ChaoticEnemy) if (HUD.HEALTH != 0) {
										tempObject.velX = 3;
										tempObject.velY = 3;
									}
								}
							}
						}
					}
				}
			}
		}
	}
}