package core;

import java.awt.geom.Point2D;

public class Wall {
	
	private final double limit;
	private final char id; //D: down, R: right, U: up, L: left
	private static final char D= "D".charAt(0);
	private static final char R= "R".charAt(0);
	private static final char U= "U".charAt(0);
	private static final char L= "L".charAt(0);
	private static final int INFINITE= Integer.MAX_VALUE;

	public Wall(String c, double l) {
		this.limit= l;
		this.id= c.toUpperCase().charAt(0);
	}

	public Point2D getCollisionPoint(Particle part) {
		Point2D collisionPoint= new Point2D.Float();
		//Down wall and particle going up
		if (Character.compare(id, D) == 0) {
			if (part.getVy() >= 0) {
				collisionPoint.setLocation(INFINITE, INFINITE);
				return collisionPoint;
			}
		}
		//Right wall and particle going left
		if (Character.compare(id, R) == 0) {
			if (part.getVx() <= 0) {
				collisionPoint.setLocation(INFINITE, INFINITE);
				return collisionPoint;
			}
		}
		//Up wall and particle going down
		if (Character.compare(id, U) == 0) {
			if (part.getVy() <= 0) {
				collisionPoint.setLocation(INFINITE, INFINITE);
				return collisionPoint;
			}
		}
		//Left wall and particle going right
		if (Character.compare(id, L) == 0) {
			if (part.getVx() >= 0) {
				collisionPoint.setLocation(INFINITE, INFINITE);
				return collisionPoint;
			}
		}

		


		// TODO Auto-generated method stub
		return null;
	}

}
