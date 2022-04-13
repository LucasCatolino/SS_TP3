package tests;

import static org.junit.Assert.*;

import java.awt.geom.Point2D;

import org.junit.Test;

import core.Particle;
import core.Utils;
import core.Wall;

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
	
	@Test
	public void test04WallRDevuelvePuntoDeColision() {
		Wall wallU= new Wall("R", 6);
		Particle part= new Particle(1, 1, 0, 1, 1, 1, 0);
		
		Point2D point= wallU.getCollisionPoint(part);
		
		assertEquals(6, point.getX(), 0.1);
		assertEquals(1, point.getY(), 0.1);
	}
	
	@Test
	public void test05WallRDevuelvePuntoDeColision() {
		Wall wallU= new Wall("R", 6);
		Particle part= new Particle(1, 1, 0, 1, 1, 5, 1);
		
		Point2D point= wallU.getCollisionPoint(part);
		
		assertEquals(6, point.getX(), 0.1);
		assertEquals(2, point.getY(), 0.1);
	}
	
	@Test
	public void test06WallRDevuelvePuntoDeColision() {
		Wall wallU= new Wall("R", 6);
		Particle part= new Particle(1, 1, 0, 1, 1, 5, -1);
		
		Point2D point= wallU.getCollisionPoint(part);
		
		assertEquals(6, point.getX(), 0.1);
		assertEquals(0, point.getY(), 0.1);
	}
	
	@Test
	public void test06WallLDevuelvePuntoDeColision() {
		Wall wallU= new Wall("L", 6);
		Particle part= new Particle(1, 1, 0, 3, 2, -3, -1);
		
		Point2D point= wallU.getCollisionPoint(part);
		
		assertEquals(0, point.getX(), 0.1);
		assertEquals(1, point.getY(), 0.1);
	}
	
	@Test
	public void test07WallLDevuelvePuntoDeColision() {
		Wall wallU= new Wall("L", 6);
		Particle part= new Particle(1, 1, 0, 3, 2, -3, 1);
		
		Point2D point= wallU.getCollisionPoint(part);
		
		assertEquals(0, point.getX(), 0.1);
		assertEquals(3, point.getY(), 0.1);
	}
	
	@Test
	public void test08WallUDevuelvePuntoDeColision() {
		Wall wallU= new Wall("U", 6);
		Particle part= new Particle(1, 1, 0, 3, 2, 1, 4);
		
		Point2D point= wallU.getCollisionPoint(part);
		
		assertEquals(4, point.getX(), 0.1);
		assertEquals(6, point.getY(), 0.1);
	}
	
	@Test
	public void test09WallUDevuelvePuntoDeColision() {
		Wall wallU= new Wall("U", 6);
		Particle part= new Particle(1, 1, 0, 3, 2, -1, 4);
		
		Point2D point= wallU.getCollisionPoint(part);
		
		assertEquals(2, point.getX(), 0.1);
		assertEquals(6, point.getY(), 0.1);
	}
	
	@Test
	public void test10WallDDevuelvePuntoDeColision() {
		Wall wallU= new Wall("D", 6);
		Particle part= new Particle(1, 1, 0, 3, 2, -1, -2);
		
		Point2D point= wallU.getCollisionPoint(part);
		
		assertEquals(2, point.getX(), 0.1);
		assertEquals(0, point.getY(), 0.1);
	}

	@Test
	public void test11WallDDevuelvePuntoDeColision() {
		Wall wallU= new Wall("D", 6);
		Particle part= new Particle(1, 1, 0, 3, 2, 1, -2);
		
		Point2D point= wallU.getCollisionPoint(part);
		
		assertEquals(4, point.getX(), 0.1);
		assertEquals(0, point.getY(), 0.1);
	}
}
