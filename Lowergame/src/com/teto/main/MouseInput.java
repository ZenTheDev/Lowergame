package com.teto.main;
import java.awt.Desktop;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URI;
import java.util.Random;
public class MouseInput implements MouseListener {
	private Handler handler;
	public Random r = new Random();
	public MouseInput(Handler handler) {
		this.handler = handler;
	}
	public void mouseClicked(MouseEvent me) {
	}
	public void mouseEntered(MouseEvent me) {
	}
	public void mouseExited(MouseEvent me) {
	}
	public void mousePressed(MouseEvent me) {
	}
	public void mouseReleased(MouseEvent me) {
		int mouseX = me.getX();
		int mouseY = me.getY();
		if (Game.startPressed == false) {
			if ((mouseX > Game.WIDTH/2-63) && (mouseX < Game.WIDTH/2+20)) {
				if ((mouseY > Game.HEIGHT/2-50) && (mouseY < Game.HEIGHT/2-10)) {
					Game.startPressed = true;
					KeyInput.hoverOpt2 = false;
					handler.addObject(new Player((Game.WIDTH/2)-21, (Game.HEIGHT/2)-24, ID.Player, handler));
					for (int i = 0; i < 5; i++) handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 17), r.nextInt(Game.HEIGHT - 17), ID.BasicEnemy, handler));
					handler.addObject(new BasicHP(r.nextInt(Game.WIDTH - 17), r.nextInt(Game.HEIGHT - 17), ID.BasicHP, handler));
				}
			} 
			if ((mouseX > Game.WIDTH/2-173) && (mouseX < Game.WIDTH/2+145)) {
				if ((mouseY > Game.HEIGHT/2+10) && (mouseY < Game.HEIGHT/2+60)) {
					System.exit(0);
				}
			}
			if ((mouseX > 4) && (mouseX < 64)) {
				if ((mouseY > Game.HEIGHT-90) && (mouseY < Game.HEIGHT-32)) {
					try {
						Desktop desktop = java.awt.Desktop.getDesktop();
						URI oURL = new URI("https://zenthedev.itch.io/lowergame");
						desktop.browse(oURL);
					} catch (Exception err) {
						err.printStackTrace();
					}
				}
			}
		} else if (Game.startPressed == true) {
			if (Game.gamePaused == true) {
				if ((mouseX > Game.WIDTH/2-113) && (mouseX < Game.WIDTH/2-20)) {
					if ((mouseY > Game.HEIGHT/2-40) && (mouseY < Game.HEIGHT/2)) {
						Game.gamePaused = false;
					}
				}
				if ((mouseX > Game.WIDTH/2-113) && (mouseX < Game.WIDTH/2+145)) {
					if ((mouseY > Game.HEIGHT/2+1) && (mouseY < Game.HEIGHT/2+40)) {
						Game.gamePaused = false;
						Game.startPressed = false;
						Game.splashStr = r.nextInt(3);
		}}}}
	} // ends mouseReleased
}