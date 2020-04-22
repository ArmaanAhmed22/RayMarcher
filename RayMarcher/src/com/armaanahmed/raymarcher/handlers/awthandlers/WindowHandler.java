package com.armaanahmed.raymarcher.handlers.awthandlers;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JOptionPane;

import com.armaanahmed.raymarcher.program.Program;

public class WindowHandler extends AWTHandler implements WindowListener{

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		//System.out.println("hello");
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		if (getActiveState()) {
			Program.setRunningStatus(false);
			int i = JOptionPane.showConfirmDialog(null, "Do you want to exit?", "Exiting Menu", JOptionPane.YES_NO_OPTION);
			if (i == 0)
				System.exit(0);
			else Program.setRunningStatus(true);
		}
	}

	@Override
	public void windowClosed(WindowEvent e) {
		System.out.println("yes");
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		//System.out.println("hello");
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		//System.out.println("hello");
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		//System.out.println("hello");
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		//System.out.println("hello");
	}

}
