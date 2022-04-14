package tests;

import static org.junit.Assert.*;

import java.awt.geom.Point2D;

import org.junit.Test;

import core.Utils;

public class TestsJacobian {

	@Test
	public void test01Jacobian() {
		Point2D p1= new Point2D.Float();
		p1.setLocation(3, 3);
		double[] v1= {2, 2};
		double r1= 0.7;
		double m1= 1;
		
		Point2D p2= new Point2D.Float();
		p2.setLocation(2.9, 3.9);
		double[] v2= {-1, 0};
		double r2= 0.2;
		double m2= 0.5;
		
		double jx= Utils.jacobian(p1, v1, m1, r1, p2, v2, m2, r2, "X");
		assertEquals(0.12, jx, 0.01);
		
		double jy= Utils.jacobian(p1, v1, m1, r1, p2, v2, m2, r2, "Y");
		assertEquals(-1.11, jy, 0.01);
	}
	
}
