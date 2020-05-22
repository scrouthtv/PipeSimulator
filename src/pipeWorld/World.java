package pipeWorld;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import world.Block;
import world.BlockBase;
import world.EnumFacing;
import world.Pos2D;

public class World {
	private final HashMap<Pos2D, Block> blocks = new HashMap<Pos2D, Block>();
	private final int height, width;
	
	private static final String[] empty = new String[] {"···", "···", "···"};
	
	public World(final int height, final int width) {
		this.height = height;
		this.width = width;
	}
	
	public void tick() {
		for (int x = 0; x <= height; x++) for (int y = 0; y <= width; y++) {
			final Pos2D pos = new Pos2D(x, y);
			// TODO real Minecraft does not have instances of Block at every position but one
			// instance for every Block Type. We need to check in Block#updateTick() which one
			// is the right one and select it.
			if (blocks.containsKey(pos) && blocks.get(pos) != null)
				blocks.get(pos).updateTick(this, pos);
		}
	}
	
	public boolean isValid(final Pos2D pos) {
		if (pos.getX() < 0 || pos.getY() < 0) return false;
		if (pos.getX() > width || pos.getY() > height) return false;
		return true; 
	}
	
	public boolean addBlock(final Pos2D pos, final BlockBase block) {
		if (!isValid(pos)) return false;
		if (blocks.containsKey(pos)) return false;;
		blocks.put(pos, block);
		block.setWorld(this);
		block.setPos(pos);
		
		if (block instanceof Pipe)
			((Pipe) block).setNetwork();
		
		block.update();
		
		return true;
	}
	
	public Block getPipe(Pos2D pos) {
		return blocks.get(pos);
	}
	
	public HashMap<EnumFacing, Block> adjacents(Pos2D pos) {
		final HashMap<EnumFacing, Block> adjacents = new HashMap<EnumFacing, Block>();
		
		final HashMap<Pos2D, EnumFacing> aPs = adjacentPos(pos);
		for (Pos2D aP : aPs.keySet())
			if (blocks.containsKey(aP))
				adjacents.put(aPs.get(aP), blocks.get(aP));
		
		return adjacents;
	}
	
	private HashMap<Pos2D, EnumFacing> adjacentPos(Pos2D pos) {
		final HashMap<Pos2D, EnumFacing> adjacents = new HashMap<Pos2D, EnumFacing>();
		
		adjacents.put(pos.adjacentPos(EnumFacing.NORTH), EnumFacing.NORTH);
		adjacents.put(pos.adjacentPos(EnumFacing.SOUTH), EnumFacing.SOUTH);
		adjacents.put(pos.adjacentPos(EnumFacing.WEST), EnumFacing.WEST);
		adjacents.put(pos.adjacentPos(EnumFacing.EAST), EnumFacing.EAST);
		
		return adjacents;
	}
	
	public String[] draw() {
		// + 4 for last column + 2 for axis ident
		String[] screen = new String[3*height + 4 + 2];
		String line = "";
		for (int x = 0; x <= width; x++)
			line += "  " + x;
		screen[0] = line + " x->";
		
		for (int y = 0; y <= height; y++) {
			String[] row = new String[3];
			row[0] = " ";
			row[1] = "" + y;
			row[2] = " ";

			for (int x = 0; x <= width; x++) {
				final String[] elem;
				if (blocks.containsKey(new Pos2D(x, y)))
					elem = blocks.get(new Pos2D(x, y)).draw();
				else
					elem = empty;
				row[0] += elem[0];
				row[1] += elem[1];
				row[2] += elem[2];
				
			}
			for (int rown = 0; rown < 3; rown++)
				screen[3*y + rown + 1] = row[rown];
		}
		
		screen[screen.length - 2] = "y";
		screen[screen.length - 1] = "v";
		
		return screen;
	}
}
