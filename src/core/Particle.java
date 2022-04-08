package core;

import java.awt.geom.Point2D;

public class Particle {
	
	private final int id;
	private final double mass;
	private final double radius;
	private Point2D position; 
	private double[] velocity= new double[2];
	
	public Particle(int id, double mass, double rad, Point2D pos) {
		this.id= id;
		this.mass= mass;
		this.radius = rad;
		this.position= pos;
	}

	public void move(double t) {
		//TODO: actualiza la posicion
	}
	
	public void collide() {
		
	}

}
