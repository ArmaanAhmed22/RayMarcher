package com.armaanahmed.raymarcher.handlers.awthandlers;

import java.awt.AWTEvent;
import java.util.ArrayList;
import java.util.Queue;

public abstract class AWTHandler {
	
	private boolean active;
	
	private static ArrayList<AWTHandler> handlerReferenceList;
	
	static {
		handlerReferenceList = new ArrayList<AWTHandler>();
	}
	
	public AWTHandler() {
		handlerReferenceList.add(this);
	}
	
	public final void activate() {
		active = true;
	}
	
	public final void deactivate() {
		active = false;
	}
	
	protected final boolean getActiveState() {
		return active;
	}

}
