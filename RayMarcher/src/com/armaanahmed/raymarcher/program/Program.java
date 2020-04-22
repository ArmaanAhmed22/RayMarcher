package com.armaanahmed.raymarcher.program;

import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.LinkedList;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

import com.armaanahmed.raymarcher.game.Game;
import com.armaanahmed.raymarcher.handlers.awthandlers.WindowHandler;

public class Program {

	private static boolean programRunning;

	private static WindowHandler windowHandler;

	private static ProgramUtil pU;
	private static ProgramScreen pS;
	
	private static Game game;

	private static JFrame programWindow;
	private static int PROGRAM_WINDOW_WIDTH, PROGRAM_WINDOW_HEIGHT;

	private static Thread programThread;

	public static void init(int width, int height) {
		//System.setProperty("sun.java2d.trace", "timestamp,log,count");
		System.setProperty("sun.java2d.opengl", "True");
		System.setProperty("apple.eawt.quitStrategy", "CLOSE_ALL_WINDOWS");
		/*try {
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

		programRunning = true;
		PROGRAM_WINDOW_WIDTH = width;
		PROGRAM_WINDOW_HEIGHT = height;

		pU = new ProgramUtil();
		pS = new ProgramScreen();
		
		game = new Game();

		programWindow = new JFrame();
		programWindow.setSize(width, height);
		programWindow.setUndecorated(true);
		programWindow.setLocationRelativeTo(null);
		programWindow.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		programWindow.setVisible(true);

		windowHandler = new WindowHandler();
		windowHandler.activate();
		programWindow.addWindowListener(windowHandler);

		programThread = new Thread(() -> programLoop(), "programThread");
		programThread.start();
	}

	private static void programLoop() {

		int fps = 0;
		int ups = 0;

		long prevTime = System.currentTimeMillis();
		long deltaU = 0;
		long deltaS = 0;
		while (programRunning) {
			long now = System.currentTimeMillis();
			deltaU += (now - prevTime);
			deltaS += (now - prevTime);
			prevTime = now;

			while (deltaU >= 1000 / pU.UPS) {
				update();
				ups++;
				deltaU -= 1000 / pU.UPS;
			}
			pS.render();
			fps++;

			if (deltaS >= 1000) {
				deltaS = 0;
				pU.addFPS(fps);
				pU.addUPS(ups);
				System.out.println("fps: " + pU.getAverageFPS() + " | ups: " + pU.getAverageUPS());
				fps = 0;
				ups = 0;

			}

			try {
				TimeUnit.MILLISECONDS.sleep(1000 / pU.FPS - (System.currentTimeMillis() - now));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public static void setRunningStatus(boolean state) {
		programRunning = state;
		if (state && !programThread.isAlive()) {
			programThread = new Thread(() -> programLoop(), "programThread");
			programThread.start();
		}
	}

	private static void update() {
		game.update();
	}

	public static class ProgramScreen {

		private int width, height;
		private BufferedImage image;
		private int[] pixels;

		public ProgramScreen() {
			width = PROGRAM_WINDOW_WIDTH;
			height = PROGRAM_WINDOW_HEIGHT;
			
			image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
			
		}
		
		private void render() {
			BufferStrategy bs = programWindow.getBufferStrategy();
			if (bs == null) {
				programWindow.createBufferStrategy(2);
				bs = programWindow.getBufferStrategy();
			}
			Graphics g = bs.getDrawGraphics();
			game.render();
			g.drawImage(image, 0, 0, null);
			g.dispose();
			bs.show();
		}
		
		public void setPixel(int index, int color) {
			pixels[index] = color;
		}
		
		public void setPixel(int index, Color color) {
			int red = color.getRed();
			int green = color.getGreen();
			int blue = color.getBlue();
			int[] hexIndividual = new int[] {
					red / 16,
					red % 16,
					green / 16,
					green % 16,
					blue / 16,
					blue % 16
			};
			setPixel(index, (hexIndividual[0] << 4 * 5) + (hexIndividual[1] << 4 * 4) + (hexIndividual[2] << 4 * 3) + (hexIndividual[3] << 4 * 2) + (hexIndividual[4] << 4 * 1) + (hexIndividual[5] << 4 * 0));
		}
		
		public int getWidth() {
			return width;
		}
		
		public int getHeight() {
			return height;
		}

	}
	
	public static ProgramScreen getScreen() {
		return pS;
	}

}
