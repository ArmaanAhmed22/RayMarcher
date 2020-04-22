package com.armaanahmed.raymarcher.math;

public class Vector3 extends Vector<Vector3> {
	
	public double x, y, z;
	
	public Vector3(double _x, double _y, double _z) {
		x = _x;
		y = _y;
		z = _z;
	}

	@Override
	public Vector3 add(Vector3 v) {
		return new Vector3(x + v.x, y + v.y, z + v.z);
	}

	@Override
	public Vector3 sub(Vector3 v) {
		return new Vector3(x - v.x, y - v.y, z - v.z);
	}

	@Override
	public Vector3 mult(Vector3 v) {
		return new Vector3(x * v.x, y * v.y, z * v.z);
	}

	@Override
	public Vector3 div(Vector3 v) {
		return new Vector3(x / v.x, y / v.y, z / v.z);
	}

	@Override
	public double distance(Vector3 v) {
		return Math.sqrt( Math.pow(x - v.x, 2) + Math.pow(y - v.y, 2) + Math.pow(z - v.z, 2) );
	}
	
	

}
