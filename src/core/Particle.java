package core;

import java.awt.geom.Point2D;

public class Particle {
	
	private final int id;
	private final double mass;
	private final double radius;
	private Point2D position; 
	private double[] velocity= new double[2];
	
	public Particle(int id, double mass, double rad, double x, double y, double v_x, double v_y) {
		this.id= id;
		this.mass= mass;
		this.radius = rad;
		
		Point2D position = new Point2D.Float();
		position.setLocation(x, y);
		this.position= position;
		
		this.velocity[0]= v_x;
		this.velocity[1]= v_y;
	}

	public void move(double t) {
		//TODO: actualiza la posicion
	}
	
	public void collide() {
		
	}

	public int getId() {
		return id;
	}
	
	public double getMass() {
		return mass;
	}
	
	public double getR() {
		return radius;
	}
	
	public Point2D getPoint() {
		return position;
	}

	public double getX() {
		return position.getX();
	}

	public double getY() {
		return position.getY();
	}
	
	public double[] getV() {
		return velocity;
	}
	
	public double getVx() {
		return velocity[0];
	}
	
	public double getVy() {
		return velocity[1];
	}

}
