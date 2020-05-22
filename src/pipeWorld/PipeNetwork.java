package pipeWorld;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import fluidWorld.FluidTank;
import world.BlockBase;

public class PipeNetwork {
	private final Set<FluidTank> connected = new CopyOnWriteArraySet<FluidTank>();
	private final Set<Pipe> pipes = new CopyOnWriteArraySet<Pipe>();
	
	public PipeNetwork(final Pipe initial) {
		pipes.add(initial);
	}
	
	/**
	 * Merges another pipe network into this one, and lastly deletes the other one.
	 * 
	 * @param other the other network to be merged & deleted.
	 */
	public boolean mergeIn(PipeNetwork other) {
		if (!isConnected(other)) return false; 
		
		// TODO
		
		
		other = null;
		return true;
	}
	
	private boolean isConnected(PipeNetwork other) {
		for (BlockBase b : other.connected)
			for (Pipe p : pipes)
				if (p.isConnected(b))
					return true;
		for (BlockBase b : other.pipes)
			for (Pipe p: pipes)
				if (p.isConnected(b))
					return true;

		return false;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (obj instanceof PipeNetwork) {
			PipeNetwork other = (PipeNetwork) obj;
			return pipes.equals(other.pipes); 
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		final Set<BlockBase> merge = new CopyOnWriteArraySet<BlockBase>();
		merge.addAll(connected);
		merge.addAll(pipes);
		return merge.hashCode();
	}
	
}
