package fluidWorld;

import java.util.Objects;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import main.PrinterUtils;
import pipeWorld.Pipe;
import world.Block;
import world.BlockBase;
import world.EnumFacing;

public class FluidTank extends BlockBase implements IFluidHandler {

	private FluidStack fluid = null;
	private final int maxStore;

	public FluidTank(final int maxStore) {
		this.maxStore = maxStore;
	}

	@Override
	@Nullable
	public FluidStack drain(@Nullable FluidStack resource, boolean doDrain) {
		if (fluid == null || resource == null)
			return null;
		// check if fluid type & nbt are equal
		if (!fluid.isFluidEqual(resource))
			return null;
		else {
			// if more fluid is requested than there is available, return only fluid.amount
			if (resource.amount >= fluid.amount) {
				final FluidStack result = fluid.copy();
				if (doDrain)
					fluid = null;
				return result;
			} else {
				if (doDrain)
					fluid.amount -= resource.amount;
				return resource.copy();
			}
		}
	}

	@Override
	public FluidStack drain(int maxDrain, boolean doDrain) {
		if (maxDrain >= fluid.amount) {
			final FluidStack result = fluid.copy();
			if (doDrain)
				fluid = null;
			return result;
		} else {
			if (doDrain)
				fluid.amount -= maxDrain;
			final FluidStack result = fluid.copy();
			result.amount -= maxDrain;
			return result;
		}
	}
	
	public void setAmount(int amount) {
		fluid.amount = Math.min(amount, maxStore);
	}

	@Override
	public int fill(@Nullable FluidStack resource, boolean doFill) {
		if (resource == null)
			return 0;
		if (fluid == null) {
			if (doFill) {
				fluid = resource.copy();
				fluid.amount = Math.min(maxStore, resource.amount);
			}
			return maxStore;
		} else if (fluid.isFluidEqual(resource)) {
			if (resource.amount >= fluid.amount) {
				System.out.println("overfill: " + resource.amount + " >= " + fluid.amount);
				if (doFill) {
					fluid = resource.copy();
					fluid.amount = maxStore;
				}
				return maxStore;
			} else {
				System.out.println("fill: " + resource.amount + " < " + fluid.amount);
				if (doFill)
					fluid.amount += resource.amount;
				return resource.amount;
			}
		} else
			return 0;
	}

	@Override
	@Nonnull
	public IFluidTankProperties[] getTankProperties() {
		// super.getTankProperties();
		return new FluidTankProperties[] { new FluidTankProperties(fluid, maxStore) };
	}

	@Override
	protected String me() {
		if (fluid == null) return "+";
		return PrinterUtils.printFluid(fluid.amount, maxStore);
	}

	@Override
	protected String color(EnumFacing face) {
		Block adjacent = adjacent(face);
		if (adjacent instanceof Pipe)
			return PrinterUtils.getColor(((Pipe) adjacent).getNetwork());
		return "";
	}

	@Override
	public boolean equals(Object obj) {
		throw new UnsupportedOperationException("not implmementd");
	}

	@Override
	public int hashCode() {
		return Objects.hash("tank", getPos());
	}

	
	
}
