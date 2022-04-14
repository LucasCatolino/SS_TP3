package core;

import java.awt.geom.Point2D;

public class Wall {
	
	private final double limit;
	private final char id; //D: down, R: right, U: up, L: left
	private static final int INFINITE= Integer.MAX_VALUE;

	public Wall(String c, double l) {
		this.id= c.toUpperCase().charAt(0);
		this.limit= l;
	}

	public Point2D getCollisionPoint(Particle part) {
		Point2D collisionPoint= new Point2D.Float();

		double alpha= 0;
		double x= part.getX();
		double y= part.getY();
		double r= part.getR();
		double auxX= 0;
		double auxY= 0;
		double vX= Math.abs(part.getVx());
		double vY= Math.abs(part.getVy());
		
		switch (id) {
		case 'D':
			//particle going up
			if (part.getVy() >= 0) {
				collisionPoint.setLocation(INFINITE, INFINITE);
				return collisionPoint;
			}
			//particle going down
			alpha= Math.atan2(vX, vY);
			auxY= 0;
			auxX= (part.getVx() > 0) ? (y - r) * Math.tan(alpha) + x : x - (y - r) * Math.tan(alpha);
			break;
			
		case 'R':
			//particle going left
			if (part.getVx() <= 0) {
				collisionPoint.setLocation(INFINITE, INFINITE);
				return collisionPoint;
			}
			//particle going right
			alpha= Math.atan2(vY, vX);
			auxX= limit;
			auxY= (part.getVy() > 0) ? (limit - x - r) * Math.tan(alpha) + y : y - (limit - x - r) * Math.tan(alpha);
			break;
			
		case 'U':
			//particle going down
			if (part.getVy() <= 0) {
				collisionPoint.setLocation(INFINITE, INFINITE);
				return collisionPoint;
			}
			//particle going down
			alpha= Math.atan2(vX, vY);
			auxY= limit;
			auxX= (part.getVx() > 0) ? (limit - y - r) * Math.tan(alpha) + x : x - (limit - y - r) * Math.tan(alpha);
			break;
			
		case 'L':
			//particle going right
			if (part.getVx() >= 0) {
				collisionPoint.setLocation(INFINITE, INFINITE);
				return collisionPoint;
			}
			//particle going left
			alpha= Math.atan2(vY, vX);
			auxX= 0;
			auxY= (part.getVy() > 0) ? (x - r) * Math.tan(alpha) + y : y - (x - r) * Math.tan(alpha);
			break;

		default:
			auxX= INFINITE;
			auxY= INFINITE;
			break;
		}
		//check if particle reaches other wall before
		auxX= (0 <= auxX && auxX <= limit) ? auxX : INFINITE;
		auxY=  (0 <= auxY && auxY <= limit) ? auxY : INFINITE;

		collisionPoint.setLocation(auxX, auxY);
		return collisionPoint;
	}

}
