package com.teto.main;
import java.awt.Canvas;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
public class FullFrame extends Canvas {
	private static final long serialVersionUID = 5486926782194361510L;
	public static boolean setCursorHidden = false;
	static Cursor csr = new Cursor(Cursor.CROSSHAIR_CURSOR);
	static JFrame frame = new JFrame("Lowergame: The Rise of The B");
	public FullFrame(int width, int height, Game game) {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		String imageName = "lgico.png";
		URL imageUrl = frame.getClass().getResource(imageName);
		if (imageUrl == null) {
            System.out.println("imageUrl not using df classLoader, fixing...");
            imageUrl = Thread.currentThread().getContextClassLoader().getResource(imageName);
        }
		ImageIcon icon = new ImageIcon( imageUrl );
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setCursor(csr);
		frame.setIconImage(icon.getImage());
		frame.setResizable(false);
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		frame.setLocationRelativeTo(null);
		frame.add(game);
		frame.setVisible(true);
		game.start();
	}
	public static void tick() {
		if (Game.startPressed == true) {
			if (Game.gamePaused == false) {
				if (HUD.HEALTH != 0) {
					frame.setCursor( frame.getToolkit().createCustomCursor(
							new BufferedImage( 1, 1, BufferedImage.TYPE_INT_ARGB ),
							new Point(),
							null)
						);
				} else {
					frame.setCursor(csr);
				}
			} else {
				frame.setCursor(csr);
			}
		}
	}
}