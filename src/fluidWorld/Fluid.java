package fluidWorld;

import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArraySet;

public class Fluid {
	
	private static final HashMap<Integer, Fluid> fluids = new HashMap<Integer, Fluid>();
	
	public static final Fluid WATER = new Fluid("water"), LAVA = new Fluid("lava");
	
	private final String name;
	
	private Fluid(final String name) {
		fluids.put(fluids.size(), this);
		this.name = name;
	}
	
	public static HashMap<Integer, Fluid> available() {
		return new HashMap<Integer, Fluid>(fluids);
	}
	
	@Override
	public String toString() {
		return name;
	}
}
