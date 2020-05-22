package world;

import java.util.Objects;

public class Pos2D {
	private int x, y;
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public Pos2D(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (obj instanceof Pos2D) {
			Pos2D other = (Pos2D) obj;
			return x == other.x && y == other.y;
		}
		return false;
	}
	
	/**
	 * returns a new position at the specified face
	 */
	public Pos2D adjacentPos(EnumFacing face) {
		switch (face) {
		case NORTH: return new Pos2D(x + 0, y - 1);
		case SOUTH: return new Pos2D(x + 0, y + 1);
		case WEST: 	return new Pos2D(x - 1, y + 0);
		case EAST:	return new Pos2D(x + 1, y + 0);
		default: return null;
		}
	}
	
	@Override
	public String toString() {
		return "[" + x + "," + y + "]";
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}
}
