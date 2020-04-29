package com.armaanahmed.raymarcher.math;

public abstract class Vector<E extends Vector<E>> {
	
	public abstract E add(E v);
	public abstract E sub(E v);
	public abstract E mult(double num);
	public abstract E div(double num);
	
	public abstract double distance(E v);
	public abstract double dot(E v);
	public abstract E cross(E v);
	
	public abstract double distance();
	public final E normalize() {
		return mult(1/distance());
	}

}
