package core;

import java.awt.geom.Point2D;

public class Utils {
	
	public static double timeToCollision(Particle p1, Particle p2) {
		
		return 0;
	}
	
	public static double sigma(double r1, double r2) {
		return r1 + r2;
	}
	
	public static double deltaR_deltaR(double x1, double x2, double y1, double y2) {
		return Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2);
	}
	
	public static double deltaV_deltaV(double vx1, double vx2, double vy1, double vy2) {
		return Math.pow(vx2 - vx1, 2) + Math.pow(vy2 - vy1, 2);
	}
	
	public static double deltaV_deltaR(double x1, double x2, double vx1, double vx2, double y1, double y2, double vy1, double vy2) {
		return (vx2 - vx1)*(x2 - x1) + (vy2 - vy1)*(y2 - y1);
	}
	

}
