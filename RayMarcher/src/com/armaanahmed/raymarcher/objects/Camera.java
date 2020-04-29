package com.armaanahmed.raymarcher.objects;

import com.armaanahmed.raymarcher.math.Vector3;

public class Camera {
	public Vector3 UP = new Vector3(0, 1, 0);
	public Vector3 FORWARD = new Vector3(0,0,1);
	public Vector3 RIGHT = new Vector3(1,0,0);
	private Vector3 pos, dir;
	private double FOV;
	
	public Camera(Vector3 _pos, Vector3 _dir, double _FOV, boolean normalized) {
		pos = _pos;
		dir = normalized ? _dir : _dir.normalize();
		FOV = _FOV;
	}
	
	public double getFOV() {
		return FOV;
	}
	
	public Vector3 getPos() {
		return pos;
	}
	
	public Vector3 getDir() {
		return dir;
	}

}
