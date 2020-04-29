package com.armaanahmed.raymarcher.objects.shapes;

import com.armaanahmed.raymarcher.math.Vector3;

public class Sphere extends Shape {
	
	private Vector3 center;
	private double radius;
	
	public Sphere(Vector3 _center, double _radius) {
		center = _center;
		radius = _radius;
	}

	@Override
	public double SDF(Vector3 pos) {
		return pos.distance(center) - radius;
	}

}
