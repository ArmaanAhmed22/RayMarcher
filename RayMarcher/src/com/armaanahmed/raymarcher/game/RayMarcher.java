package com.armaanahmed.raymarcher.game;

import java.awt.Color;
import java.util.ArrayList;

import com.armaanahmed.raymarcher.math.Vector2;
import com.armaanahmed.raymarcher.math.Vector3;
import com.armaanahmed.raymarcher.objects.Camera;
import com.armaanahmed.raymarcher.objects.shapes.Shape;
import com.armaanahmed.raymarcher.objects.shapes.Sphere;

public class RayMarcher {
	
	private static final int MAX_MARCHES = 100;
	private static final int MAX_DIST = 100;
	private static final double EPSILON = 0.001;
	
	private Camera cam;
	private Vector2 screenDim;
	
	private ArrayList<Shape> shapes;
	
	public RayMarcher(Vector2 _screenDim) {
		cam = new Camera( new Vector3(-21,0,0), new Vector3(1,0,0),Math.PI/30, true);
		screenDim = _screenDim;
		
		shapes = new ArrayList<Shape>();
		shapes.add(new Sphere(new Vector3(80,0,0),2));
	}
	
	public Vector3 pixelToRay(int x, int y) {
		
		Vector3 forwardDir = cam.getDir();
		Vector3 rightDir = forwardDir.cross(cam.UP);
		Vector3 upDir = rightDir.cross(forwardDir);
		
		
		int cartX = (int) (x - screenDim.getX() * 0.5f);
		int cartY = -(int) (y - screenDim.getY() * 0.5f);
		Vector3 right = rightDir.mult(cartX);
		Vector3 up = upDir.mult(cartY);
		double dist = (screenDim.getX() * 0.5d) / (Math.tan(cam.getFOV()));
		Vector3 forward = forwardDir.mult(dist);
		return right.add(up).add(forward).normalize();
	}
	
	public Color rayToWorld(Vector3 ray) {
		Vector3 curPos = cam.getPos();
		double dist = 0;
		int marches = 0;
		while (dist < MAX_DIST && marches < MAX_MARCHES) {
			double minDist = Double.POSITIVE_INFINITY;
			for (Shape s : shapes) {
				double sDist = s.SDF(curPos);
				if (minDist > sDist) minDist = sDist;
			}
			Vector3 marchingRay = ray.mult(minDist);
			curPos = curPos.add(marchingRay);
			if (minDist < EPSILON) {
				return new Color((int) (((marches+0d) / MAX_MARCHES*3)*255));
			}
			dist+=minDist;
			marches++;
		}
		return Color.BLACK;
	}

}
