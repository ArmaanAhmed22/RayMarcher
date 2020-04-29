package com.armaanahmed.raymarcher.game;

import java.awt.Color;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import com.armaanahmed.raymarcher.program.Program;

public class Game {
	
	private RayMarcher rayMarcher;
	
	private ThreadPoolExecutor renderThread;
	private static final int NUM_THREADS = 25;
	
	private Program.ProgramScreen screen;
	
	public Game() {
		renderThread = (ThreadPoolExecutor) Executors.newFixedThreadPool(NUM_THREADS);
		
		screen = Program.getScreen();
		
		rayMarcher = new RayMarcher(screen.getDimensions());
	}

	public void update() {

	}

	public void render() {
		
		CountDownLatch latch = new CountDownLatch(NUM_THREADS);
		
		final int width = screen.getWidth();
		final int height = screen.getHeight();
		
		Color color = new Color((int) (255*Math.random()));
		
		final int increment = width*height/NUM_THREADS;
		
		for (int i = 0; i < width * height; i+=increment) {
			final int index = i;
			renderThread.execute(() -> {
				for (int j = index; j < index + increment; j++) {
					screen.setPixel(j, color);
					screen.setPixel(j, rayMarcher.rayToWorld(rayMarcher.pixelToRay(j % width, j / width)));
				}
				latch.countDown();
			});
		}
		try {
			latch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
