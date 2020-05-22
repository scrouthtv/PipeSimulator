package fluidWorld;

import javax.annotation.Nullable;

/**
 * Source code copied from net.minecraftforge.FluidStack
 * 
 * @author lenni
 *
 */
public class FluidStack {
	public int amount;
	private Fluid fluid;
	private String nbt;
	
	public FluidStack(final Fluid fluid, final int amount) {
		if (fluid == null) throw new IllegalArgumentException("Cannot create a fluidstack from a null fluid");
		this.fluid = fluid;
		this.amount = amount;
	}
	
	public FluidStack(final Fluid fluid, final int amount, final String nbt) {
		this(fluid, amount);
		this.nbt = nbt;
	}
	
	public FluidStack(final FluidStack other, int amount) {
		this.fluid = other.fluid;
		this.nbt = other.nbt;
		this.amount = amount;
	}
	
    public final Fluid getFluid() {
        return fluid;
    }

    /**
     * @return A copy of this FluidStack
     */
    public FluidStack copy() {
        return new FluidStack(getFluid(), amount, nbt);
    }

    /**
     * Determines if the FluidIDs and NBT Tags are equal. This does not check amounts.
     *
     * @param other
     *            The FluidStack for comparison
     * @return true if the Fluids (IDs and NBT Tags) are the same
     */
    public boolean isFluidEqual(@Nullable FluidStack other) {
    	if (other == null) 
    		return false;
    	else
        return getFluid() == other.getFluid() && isFluidStackTagEqual(other);
    }

    private boolean isFluidStackTagEqual(FluidStack other) {
    	if (nbt == null)
    		return other.nbt == null;
		else if (other.nbt == null)
			return false;
		else
			return nbt.equals(other.nbt);
    }

    /**
     * Determines if the NBT Tags are equal. Useful if the FluidIDs are known to be equal.
     */
    public static boolean areFluidStackTagsEqual(@Nullable FluidStack stack1, @Nullable FluidStack stack2) {
    	if (stack1 == null)
    		return stack2 == null;
        return stack1.isFluidStackTagEqual(stack2);
    }

    /**
     * Determines if the Fluids are equal and this stack is larger.
     *
     * @param other
     * @return true if this FluidStack contains the other FluidStack (same fluid and >= amount)
     */
    public boolean containsFluid(@Nullable FluidStack other) {
        return isFluidEqual(other) && amount >= other.amount;
    }

    /**
     * Determines if the FluidIDs, Amounts, and NBT Tags are all equal.
     *
     * @param other
     *            - the FluidStack for comparison
     * @return true if the two FluidStacks are exactly the same
     */
    public boolean isFluidStackIdentical(FluidStack other) {
        return isFluidEqual(other) && amount == other.amount;
    }

    @Override
    public final int hashCode() {
        int code = 1;
        code = 31*code + getFluid().hashCode();
        if (nbt != null)
            code = 31*code + nbt.hashCode();
        return code;
    }

    /**
     * Default equality comparison for a FluidStack. Same functionality as isFluidEqual().
     *
     * This is included for use in data structures.
     */
    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof FluidStack))
            return false;

        return isFluidEqual((FluidStack) o);
    }
}
