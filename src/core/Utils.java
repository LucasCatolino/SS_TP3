package core;

import java.awt.geom.Point2D;

public class Utils {
	
	private static final int INFINITE= Integer.MAX_VALUE;
	
	public static double timeToCollision(Point2D p1, double[] v1, double r1, Point2D p2, double[] v2, double r2) {
		
		double dv_dr= deltaV_deltaR(p1.getX(), p2.getX(), v1[0], v2[0], p1.getY(), p2.getY(), v1[1], v2[1]);
		if (dv_dr >= 0) {
			return INFINITE;
		}
		
		double d= d(p1, v1, r1, p2, v2, r2);
		if (d < 0) {
			return INFINITE;
		}
		
		double dv_dv= deltaV_deltaV(v1[0], v2[0], v1[1], v2[1]);
		return - ( dv_dr + Math.sqrt(d) ) / ( dv_dv );
	}
	
	private static double sigma(double r1, double r2) {
		return r1 + r2;
	}
	
	private static double deltaR_deltaR(double x1, double x2, double y1, double y2) {
		return Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2);
	}
	
	private static double deltaV_deltaV(double vx1, double vx2, double vy1, double vy2) {
		return Math.pow(vx2 - vx1, 2) + Math.pow(vy2 - vy1, 2);
	}
	
	private static double deltaV_deltaR(double x1, double x2, double vx1, double vx2, double y1, double y2, double vy1, double vy2) {
		return (vx2 - vx1)*(x2 - x1) + (vy2 - vy1)*(y2 - y1);
	}
	
	private static double d(Point2D p1, double[] v1, double r1, Point2D p2, double[] v2, double r2) {
		double dv_dr= deltaV_deltaR(p1.getX(), p2.getX(), v1[0], v2[0], p1.getY(), p2.getY(), v1[1], v2[1]);
		double dv_dv= deltaV_deltaV(v1[0], v2[0], v1[1], v2[1]);
		double dr_dr= deltaR_deltaR(p1.getX(), p2.getX(), p1.getY(), p2.getY());
		double sigma2= Math.pow(sigma(r1, r2), 2);
		
		return Math.pow(dv_dr, 2) - ( dv_dv * ( dr_dr - sigma2 ) );
	}
	
	public static double jacobian(Point2D p1, double[] v1, double m1, double r1, Point2D p2, double[] v2, double m2, double r2, String dim) {
		double deltaPos= (dim.charAt(0) == 'X') ? p2.getX() - p1.getX() : p2.getY() - p1.getY(); 
		
		double dv_dr= deltaV_deltaR(p1.getX(), p2.getX(), v1[0], v2[0], p1.getY(), p2.getY(), v1[1], v2[1]);
		double sigma= sigma(r1, r2);
		
		double j= (2 * m1 * m2 * dv_dr) / (sigma * (m1 + m2));
		
		return (j * deltaPos) / sigma(r1, r2);
	}

}
