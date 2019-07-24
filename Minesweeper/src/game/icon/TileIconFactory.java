package game.icon;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class TileIconFactory {

	public TileIconFactory() {}
	
	public ImageIcon generateIcon(TileIcon t) {
		try {
			BufferedImage bi = ImageIO.read(getClass().getResource("/assets/panel/" + t + ".png"));
			return new ImageIcon(bi.getScaledInstance(40, 40, Image.SCALE_SMOOTH));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

}
