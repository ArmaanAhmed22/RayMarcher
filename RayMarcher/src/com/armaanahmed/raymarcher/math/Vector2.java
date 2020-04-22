package com.armaanahmed.raymarcher.math;

public class Vector2 extends Vector<Vector2> {
	
	public double x, y;
	
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
	public Vector2 mult(Vector2 v) {
		return new Vector2(x * v.x, y * v.y);
	}

	@Override
	public Vector2 div(Vector2 v) {
		return new Vector2(x / v.x, y / v.y);
	}

	@Override
	public double distance(Vector2 v) {
		return Math.sqrt( Math.pow(x - v.x, 2) + Math.pow(y - v.y, 2));
	}

}
