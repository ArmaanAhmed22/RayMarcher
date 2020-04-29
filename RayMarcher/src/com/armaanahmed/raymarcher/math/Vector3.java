package com.armaanahmed.raymarcher.math;

public class Vector3 extends Vector<Vector3> {
	
	public static final Vector3 ZERO = new Vector3(0,0,0);
	
	private double x, y, z;
	
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
	public Vector3 mult(double num) {
		return new Vector3(x * num, y * num, z * num);
	}

	@Override
	public Vector3 div(double num) {
		return new Vector3(x / num, y / num, z / num);
	}

	@Override
	public double distance(Vector3 v) {
		return Math.sqrt( Math.pow(x - v.x, 2) + Math.pow(y - v.y, 2) + Math.pow(z - v.z, 2) );
	}

	@Override
	public double dot(Vector3 v) {
		return x*v.x + y*v.y + z*v.z;
	}

	@Override
	public Vector3 cross(Vector3 v) {
		return new Vector3(y*v.z - z*v.y, z*v.x - x*v.z, x*v.y - y*v.x);
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public double getZ() {
		return z;
	}

	@Override
	public double distance() {
		return distance(ZERO);
	}
	
	

}
