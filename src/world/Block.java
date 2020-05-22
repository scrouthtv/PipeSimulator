package world;

import pipeWorld.World;

public interface Block {
	/**
	 * Should return a String triplet, each 3 letters representing this block.
	 * @return
	 */
	public String[] draw();
	
	public void updateTick(World w, Pos2D pos);
}
