package world;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Set;

import fluidWorld.IFluidHandler;
import pipeWorld.World;

public abstract class BlockBase implements Block {
	private static final String resetColor = "\u001B[0m";
	
	private final Set<EnumFacing> attachments = EnumSet.noneOf(EnumFacing.class);
	private World world;
	private Pos2D pos;
	
	/**
	 * Three line drawing of this pipe. 
	 * 
	 * @return string array with three strings
	 */
	public final String[] draw() {
		// color
		final String top = " " + (attachments.contains(EnumFacing.NORTH) ? color(EnumFacing.NORTH) + "↑" + resetColor : " ") + " ";
		final String mid = (attachments.contains(EnumFacing.WEST) ? color(EnumFacing.WEST) + "←" + resetColor : " ")
				+ me() + (attachments.contains(EnumFacing.EAST) ? color(EnumFacing.EAST) + "→" + resetColor : " ");
		final String bottom = " " + (attachments.contains(EnumFacing.SOUTH) ? color(EnumFacing.SOUTH) + "↓" + resetColor : " ") + " ";
 		return new String[] { top, mid, bottom };
	}
	
	public boolean isConnected(BlockBase other) {
		if (attachments.isEmpty()) return false;
		for (EnumFacing side : attachments)
			if (pos.adjacentPos(side).equals(other.pos))
				return true;
		return false;
	}
	
	public Pos2D getPos() {
		return pos;
	}
	
	public World getWorld() {
		return world;
	}

	protected abstract String me();
	protected abstract String color(EnumFacing face);
	
	public void setPos(Pos2D pos) {
		this.pos = pos;
	}
	
	public void setWorld(World world) {
		this.world = world;
	}
	
	public void updateTick(World w, Pos2D pos) {
		// TODO
	}
	
	public void update() {
		boolean dirty = false;
		
		HashMap<EnumFacing, Block> adjacents = world.adjacents(pos);
		for (EnumFacing adj : adjacents.keySet())
			if (attachments.add(adj) & adjacents.get(adj) instanceof BlockBase)
				((BlockBase) adjacents.get(adj)).update(adj.invert());
	}
	
	private void update(EnumFacing face) {
		if (world.getPipe(pos.adjacentPos(face)) == null)
			attachments.remove(face);
		else
			attachments.add(face);
	}
	
	public Block adjacent(final EnumFacing face) {
		return world.getPipe(pos.adjacentPos(face));
	}
	
	@Override
	public abstract boolean equals(Object obj);
	
	@Override
	public abstract int hashCode();
}
