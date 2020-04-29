package com.armaanahmed.raymarcher.objects.shapes;

import com.armaanahmed.raymarcher.math.Vector3;

public abstract class Shape {
	
	public Shape() {
		
	}
	
	public abstract double SDF(Vector3 pos);

}
