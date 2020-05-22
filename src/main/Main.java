package main;

import java.io.IOException;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import fluidWorld.Fluid;
import fluidWorld.FluidStack;
import fluidWorld.FluidTank;
import pipeWorld.Pipe;
import pipeWorld.World;
import world.Pos2D;

public class Main {
	private static final World w = new World(5, 5); 
	
	private static final boolean INTERACTIVE = false;

	public static void main(String[] args) {
		final FluidTank tank1 = new FluidTank(5000);
		final FluidTank tank2 = new FluidTank(5000);
		tank1.fill(new FluidStack(Fluid.WATER, 1000), true);
		tank2.fill(new FluidStack(Fluid.WATER, 5000), true);
		w.addBlock(new Pos2D(1, 1), new Pipe());
		w.addBlock(new Pos2D(2, 1), new Pipe());
		w.addBlock(new Pos2D(1, 2), new Pipe());
		w.addBlock(new Pos2D(0, 1), tank1);
		w.addBlock(new Pos2D(3, 1), tank2);
		
		final FluidTank tank3 = new FluidTank(5000);
		final FluidTank tank4 = new FluidTank(5000);
		tank3.fill(new FluidStack(Fluid.WATER, 3000), true);
		tank4.fill(new FluidStack(Fluid.WATER, 4000), true);
		w.addBlock(new Pos2D(1, 4), tank3);
		w.addBlock(new Pos2D(4, 4), tank4);
		w.addBlock(new Pos2D(2, 4), new Pipe());
		
		//Timer t = new Timer();
		//t.scheduleAtFixedRate(new Main(), 0, 1000);
		
		if (!INTERACTIVE) {
			final String[] screen = w.draw();
			for (String row : screen)
				System.out.println(row);
			return;
		}
		
		Scanner in = new Scanner(System.in);
		Console c = new Console(w, in);
		String cmd;
		do {
			final String[] screen = w.draw();
			for (String row : screen)
				System.out.println(row);
			System.out.println("Add [T]ank or [P]ipe, [R]un once, [Q]uit");
			cmd = in.nextLine();
		} while (c.parse(cmd));
	}

}
