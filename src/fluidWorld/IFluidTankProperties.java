package fluidWorld;

public interface IFluidTankProperties {

	FluidStack getContents();

	int getCapacity();

	boolean canFill();

	boolean canDrain();

	boolean canFillFluidType(FluidStack fluidStack);

	boolean canDrainFluidType(FluidStack fluidStack);

}
