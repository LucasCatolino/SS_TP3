package tests;

import static org.junit.Assert.*;

import java.awt.geom.Point2D;

import org.junit.Test;

import core.Utils;

public class Tests {
	
	private static final int INFINITE= Integer.MAX_VALUE;

	@Test
	public void test01ParticlesCollide() {
		Point2D p1= new Point2D.Float();
		p1.setLocation(0, 0);
		double[] v1= {0, 0};
		double r1= 0;
		
		Point2D p2= new Point2D.Float();
		p2.setLocation(100, 0);
		double[] v2= {-10, 0};
		double r2= 0;
		
		double ttc= Utils.timeToCollision(p1, v1, r1, p2, v2, r2);

		assertEquals(10, ttc, 0.1);
	}
	
	@Test
	public void test02ParticlesDoNotCollide() {
		Point2D p1= new Point2D.Float();
		p1.setLocation(0, 0);
		double[] v1= {0, 0};
		double r1= 0;
		
		Point2D p2= new Point2D.Float();
		p2.setLocation(100, 0);
		double[] v2= {10, 0};
		double r2= 0;
		
		double ttc= Utils.timeToCollision(p1, v1, r1, p2, v2, r2);

		assertEquals(INFINITE, ttc, 0.1);
	}
	
	@Test
	public void test03ParticlesDoNotCollide() {
		Point2D p1= new Point2D.Float();
		p1.setLocation(0, 0);
		double[] v1= {-10, 0};
		double r1= 0;
		
		Point2D p2= new Point2D.Float();
		p2.setLocation(100, 0);
		double[] v2= {10, 0};
		double r2= 0;
		
		double ttc= Utils.timeToCollision(p1, v1, r1, p2, v2, r2);

		assertEquals(INFINITE, ttc, 0.1);
	}

}
