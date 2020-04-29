package com.armaanahmed.raymarcher.math;

public class Vector2 extends Vector<Vector2> {
	
	public static final Vector2 ZERO = new Vector2(0,0);
	
	private double x, y;
	
	public Vector2(double _x, double _y) {
		x = _x;
		y = _y;
	}

	@Override
	public Vector2 add(Vector2 v) {
		return new Vector2(x + v.x, y + v.y);
	}

	@Override
	public Vector2 sub(Vector2 v) {
		return new Vector2(x - v.x, y - v.y);
	}

	@Override
	public Vector2 mult(double num) {
		return new Vector2(x * num, y * num);
	}

	@Override
	public Vector2 div(double num) {
		return new Vector2(x / num, y / num);
	}

	@Override
	public double distance(Vector2 v) {
		return Math.sqrt( Math.pow(x - v.x, 2) + Math.pow(y - v.y, 2));
	}

	@Override
	public double dot(Vector2 v) {
		return x*v.x + y*v.y;
	}

	@Override
	public Vector2 cross(Vector2 v) {
		return null;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}

	@Override
	public double distance() {
		return distance(ZERO);
	}

}
