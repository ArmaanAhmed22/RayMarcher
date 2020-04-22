package com.armaanahmed.raymarcher.math;

public abstract class Vector<E> {
	
	public abstract E add(E v);
	public abstract E sub(E v);
	public abstract E mult(E v);
	public abstract E div(E v);
	
	public abstract double distance(E v);

}
