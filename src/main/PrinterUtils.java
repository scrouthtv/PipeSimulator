package main;

import java.util.HashMap;

import pipeWorld.PipeNetwork;

public class PrinterUtils {
	private static final HashMap<PipeNetwork, String> colors = new HashMap<>();
	
	private static final String[] availableColors = new String[] {
		"\u001B[30m", "\u001B[31m", "\u001B[32m", "\u001B[33m", "\u001B[34m", "\u001B[35m", "\u001B[36m", "\u001B[37m"
	};
	// reset is "\u001B[0m"
	
	public static String getColor(PipeNetwork network) {
		if (network == null) return "";
		if (!colors.containsKey(network))
			colors.put(network, availableColors[colors.size() + 1]);
		return colors.get(network);
	}
	
	public static String printFluid(int stored, int max) {
		switch (8 * stored / max) {
			case 0: return "░";
			case 1: return "▁";
			case 2: return "▂";
			case 3: return "▃";
			case 4: return "▄";
			case 5: return "▅";
			case 6: return "▆";
			case 7: return "▇";
			case 8: return "█";
			default: return "!";
		}
	}
	
}
