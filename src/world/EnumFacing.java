package world;

public enum EnumFacing {
	NORTH, SOUTH, WEST, EAST;
	
	public EnumFacing invert() {
		switch (this) {
		case NORTH: return SOUTH;
		case SOUTH: return NORTH;
		case WEST: return EAST;
		case EAST: return WEST;
		default: return null;
		}
	}
}
