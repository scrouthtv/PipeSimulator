package main;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;

import fluidWorld.Fluid;
import fluidWorld.FluidStack;
import fluidWorld.FluidTank;
import fluidWorld.FluidTankProperties;
import pipeWorld.Pipe;
import pipeWorld.World;
import world.Pos2D;

public class Console {
	private World w;
	private Scanner in;
	
	public Console(World w, Scanner in) {
		this.w = w;
		this.in = in;
	}
	
	public boolean parse(final String s) {
		final Pos2D pos;
		switch (s.toLowerCase()) {
		case "t":
			pos = readCoords();
			FluidTank t = selectFluid();
			w.addBlock(pos, t);
			return true;
		case "p":
			pos = readCoords();
			w.addBlock(pos, new Pipe());
			return true;
		case "r":
			w.tick();
			return true;
		case "q":
			return false;
		default:
			System.out.println("command unknown");
			return true;
		}
	}
	
	private FluidTank selectFluid() {
		System.out.println("Specify <ID> <amount> <capacity>:");
		final HashMap<Integer, Fluid> available = Fluid.available();
		for (Entry<Integer, Fluid> i : available.entrySet())
			System.out.println(" - [" + i.getKey() + "] " + i.getValue());
		final String fluid = in.nextLine();
		final Fluid f = available.get(Integer.parseInt(fluid.split(" ")[0]));
		final int amount = Integer.parseInt(fluid.split(" ")[1]);
		final int capacity = Integer.parseInt(fluid.split(" ")[2]);
		
		final FluidTank t = new FluidTank(capacity);
		t.fill(new FluidStack(f, amount), true);
		return t;
	}
	
	private Pos2D readCoords() {
		System.out.println("Specify <x> <y>");
		final String coords = in.nextLine();
		final int x = Integer.parseInt(coords.split(" ")[0]);
		final int y = Integer.parseInt(coords.split(" ")[1]);
		
		final Pos2D pos = new Pos2D(x, y);
		if (!w.isValid(pos))
			return readCoords();
		return pos;
	}
}
