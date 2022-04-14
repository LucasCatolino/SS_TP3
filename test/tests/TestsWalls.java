package tests;

import static org.junit.Assert.*;

import java.awt.geom.Point2D;

import org.junit.Test;

import core.Particle;
import core.Wall;

public class TestsWalls {
	
	@Test
	public void test01WallRDevuelvePuntoDeColision() {
		Wall wallU= new Wall("R", 6);
		Particle part= new Particle(1, 1, 0, 1, 1, 1, 0);
		
		Point2D point= wallU.getCollisionPoint(part);
		
		assertEquals(6, point.getX(), 0.1);
		assertEquals(1, point.getY(), 0.1);
	}
	
	@Test
	public void test02WallRDevuelvePuntoDeColision() {
		Wall wallU= new Wall("R", 6);
		Particle part= new Particle(1, 1, 0, 1, 1, 5, 1);
		
		Point2D point= wallU.getCollisionPoint(part);
		
		assertEquals(6, point.getX(), 0.1);
		assertEquals(2, point.getY(), 0.1);
	}
	
	@Test
	public void test03WallRDevuelvePuntoDeColision() {
		Wall wallU= new Wall("R", 6);
		Particle part= new Particle(1, 1, 0, 1, 1, 5, -1);
		
		Point2D point= wallU.getCollisionPoint(part);
		
		assertEquals(6, point.getX(), 0.1);
		assertEquals(0, point.getY(), 0.1);
	}
	
	@Test
	public void test04WallLDevuelvePuntoDeColision() {
		Wall wallU= new Wall("L", 6);
		Particle part= new Particle(1, 1, 0, 3, 2, -3, -1);
		
		Point2D point= wallU.getCollisionPoint(part);
		
		assertEquals(0, point.getX(), 0.1);
		assertEquals(1, point.getY(), 0.1);
	}
	
	@Test
	public void test05WallLDevuelvePuntoDeColision() {
		Wall wallU= new Wall("L", 6);
		Particle part= new Particle(1, 1, 0, 3, 2, -3, 1);
		
		Point2D point= wallU.getCollisionPoint(part);
		
		assertEquals(0, point.getX(), 0.1);
		assertEquals(3, point.getY(), 0.1);
	}
	
	@Test
	public void test06WallUDevuelvePuntoDeColision() {
		Wall wallU= new Wall("U", 6);
		Particle part= new Particle(1, 1, 0, 3, 2, 1, 4);
		
		Point2D point= wallU.getCollisionPoint(part);
		
		assertEquals(4, point.getX(), 0.1);
		assertEquals(6, point.getY(), 0.1);
	}
	
	@Test
	public void test07WallUDevuelvePuntoDeColision() {
		Wall wallU= new Wall("U", 6);
		Particle part= new Particle(1, 1, 0, 3, 2, -1, 4);
		
		Point2D point= wallU.getCollisionPoint(part);
		
		assertEquals(2, point.getX(), 0.1);
		assertEquals(6, point.getY(), 0.1);
	}
	
	@Test
	public void test08WallDDevuelvePuntoDeColision() {
		Wall wallU= new Wall("D", 6);
		Particle part= new Particle(1, 1, 0, 3, 2, -1, -2);
		
		Point2D point= wallU.getCollisionPoint(part);
		
		assertEquals(2, point.getX(), 0.1);
		assertEquals(0, point.getY(), 0.1);
	}

	@Test
	public void test09WallDDevuelvePuntoDeColision() {
		Wall wallU= new Wall("D", 6);
		Particle part= new Particle(1, 1, 0, 3, 2, 1, -2);
		
		Point2D point= wallU.getCollisionPoint(part);
		
		assertEquals(4, point.getX(), 0.1);
		assertEquals(0, point.getY(), 0.1);
	}
	
	@Test
	public void test10WallRDevuelvePuntoDeColisionConRadio() {
		Wall wallU= new Wall("R", 6);
		Particle part= new Particle(1, 1, 1, 2, 2, 4, -1);
		
		Point2D point= wallU.getCollisionPoint(part);
		
		assertEquals(6, point.getX(), 0.1);
		assertEquals(1.2, point.getY(), 0.1);
	}
	
	@Test
	public void test11WallRDevuelvePuntoDeColisionConRadio() {
		Wall wallU= new Wall("R", 6);
		Particle part= new Particle(1, 1, 1, 2, 2, 2, 1);
		
		Point2D point= wallU.getCollisionPoint(part);
		
		assertEquals(6, point.getX(), 0.1);
		assertEquals(3.5, point.getY(), 0.1);
	}
	
	@Test
	public void test12WallUDevuelvePuntoDeColisionConRadio() {
		Wall wallU= new Wall("U", 6);
		Particle part= new Particle(1, 1, 1, 2, 2, 1, 4);
		
		Point2D point= wallU.getCollisionPoint(part);
		
		assertEquals(2.75, point.getX(), 0.1);
		assertEquals(6, point.getY(), 0.1);
	}
	
	@Test
	public void test13WallUDevuelvePuntoDeColisionConRadio() {
		Wall wallU= new Wall("U", 6);
		Particle part= new Particle(1, 1, 1, 2, 2, -1, 4);
		
		Point2D point= wallU.getCollisionPoint(part);
		
		assertEquals(1.25, point.getX(), 0.1);
		assertEquals(6, point.getY(), 0.1);
	}
	
	@Test
	public void test14WallUDevuelvePuntoDeColisionConRadio() {
		Wall wallU= new Wall("D", 6);
		Particle part= new Particle(1, 1, 1, 3, 4, 1, -4);
		
		Point2D point= wallU.getCollisionPoint(part);
		
		assertEquals(3.75, point.getX(), 0.1);
		assertEquals(0, point.getY(), 0.1);
	}
	
	@Test
	public void test15WallUDevuelvePuntoDeColisionConRadio() {
		Wall wallU= new Wall("D", 6);
		Particle part= new Particle(1, 1, 1, 3, 4, -2, -4);
		
		Point2D point= wallU.getCollisionPoint(part);
		
		assertEquals(1.5, point.getX(), 0.1);
		assertEquals(0, point.getY(), 0.1);
	}
	
	@Test
	public void test16WallULevuelvePuntoDeColisionConRadio() {
		Wall wallU= new Wall("L", 6);
		Particle part= new Particle(1, 1, 1, 4, 3, -4, 2);
		
		Point2D point= wallU.getCollisionPoint(part);
		
		assertEquals(0, point.getX(), 0.1);
		assertEquals(4.5, point.getY(), 0.1);
	}
	
	@Test
	public void test17WallULevuelvePuntoDeColisionConRadio() {
		Wall wallU= new Wall("L", 6);
		Particle part= new Particle(1, 1, 1, 4, 3, -4, -2);
		
		Point2D point= wallU.getCollisionPoint(part);
		
		assertEquals(0, point.getX(), 0.1);
		assertEquals(1.5, point.getY(), 0.1);
	}
}
