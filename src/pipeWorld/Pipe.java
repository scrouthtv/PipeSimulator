package pipeWorld;

import java.util.Objects;

import main.PrinterUtils;
import world.Block;
import world.BlockBase;
import world.EnumFacing;

public class Pipe extends BlockBase {
	
	private PipeNetwork network;
	
	public PipeNetwork getNetwork() {
		return network;
	}
	
	public void setNetwork() {
		if (network != null) return;
		for (Block adj : getWorld().adjacents(getPos()).values())
			if (adj instanceof Pipe && ((Pipe) adj).network != null)
				network = ((Pipe) adj).network;
		network = new PipeNetwork(this);
	}
	
	@Override
	protected String me() {
		return "x";
	}

	@Override
	protected String color(EnumFacing face) {
		return PrinterUtils.getColor(network);
	}

	@Override
	public boolean equals(Object obj) {
		throw new UnsupportedOperationException("not implemented");
	}

	@Override
	public int hashCode() {
		return Objects.hash("pipe", getPos());
	}

}
