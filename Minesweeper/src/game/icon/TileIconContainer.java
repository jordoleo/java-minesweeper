package game.icon;

import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;

public class TileIconContainer {

	private static TileIconContainer instance;
	private HashMap<TileIcon, ImageIcon> imageIconContainer;
	private TileIconFactory factory;
	
	private TileIconContainer() {
		imageIconContainer = new HashMap<TileIcon, ImageIcon>();
		factory = new TileIconFactory();
	}
	
	public ImageIcon get(TileIcon t) {
		if(!imageIconContainer.containsKey(t)) {
			imageIconContainer.put(t, factory.generateIcon(t));
		}
		return imageIconContainer.get(t);
	}

	public synchronized static TileIconContainer getInstance() {
		if(instance == null) {
			instance = new TileIconContainer();
		}
		return instance;
	}
}
