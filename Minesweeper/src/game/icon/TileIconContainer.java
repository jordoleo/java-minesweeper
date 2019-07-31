package game.icon;

import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;

public class TileIconContainer {

	private static TileIconContainer instance;
	private HashMap<String, ImageIcon> imageIconContainer;
	private TileIconFactory factory;
	
	private TileIconContainer() {
		imageIconContainer = new HashMap<String, ImageIcon>();
		factory = new TileIconFactory();
	}
	
	public ImageIcon get(TileIcon t, int size) {
		String key = t + "_" + size;
		if(!imageIconContainer.containsKey(key)) {
			ImageIcon imageIcon = new ImageIcon(factory.generateIcon(t).getImage().getScaledInstance(size, size, Image.SCALE_SMOOTH));
			imageIconContainer.put(key, imageIcon);
		}
		return imageIconContainer.get(key);
	}

	public synchronized static TileIconContainer getInstance() {
		if(instance == null) {
			instance = new TileIconContainer();
		}
		return instance;
	}
}
