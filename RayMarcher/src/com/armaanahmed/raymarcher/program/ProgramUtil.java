package com.armaanahmed.raymarcher.program;

import java.util.LinkedList;

public class ProgramUtil {

	public final int FPS = 60;
	public final int UPS = 100;

	private final int AVERAGE_SCOPE = 3;
	private boolean prematureFPS = true;
	private boolean prematureUPS = true;
	private LinkedList<Integer> avgFPS;
	private LinkedList<Integer> avgUPS;

	public ProgramUtil() {
		avgFPS = new LinkedList<Integer>();
		avgUPS = new LinkedList<Integer>();
	}

	public void addFPS(int FPS) {
		avgFPS.add(FPS);
		if (prematureFPS) {
			prematureFPS = avgFPS.size() < AVERAGE_SCOPE;
			return;
		}
		avgFPS.removeFirst();
	}

	public void addUPS(int UPS) {
		avgUPS.add(UPS);
		if (prematureUPS) {
			prematureUPS = avgUPS.size() < AVERAGE_SCOPE;
			return;
		}
		avgUPS.removeFirst();
	}

	public int getAverageFPS() {
		int averageFPS = 0;

		for (Integer i : avgFPS) {
			averageFPS += i;
		}
		int size = (prematureFPS) ? avgFPS.size() : AVERAGE_SCOPE;
		averageFPS /= size;
		return averageFPS;
	}

	public int getAverageUPS() {
		int averageUPS = 0;

		for (Integer i : avgUPS) {
			averageUPS += i;
		}
		int size = (prematureUPS) ? avgUPS.size() : AVERAGE_SCOPE;
		averageUPS /= size;
		return averageUPS;
	}

}
