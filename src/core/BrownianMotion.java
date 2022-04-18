package core;

import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class BrownianMotion {
	
	private static final int WALLS= 4;
	private static final int INFINITE= Integer.MAX_VALUE;
	private static final int MAX= 100000;
	private Particle[] particles;
	private Wall[] walls;
	private final int N;
	private final double L;
	private double[][] timesToCollision;
	private double minTime= INFINITE;
	private int minX= 0; //position of first particle to collide
	private int minY= 0; //position of second particle or wall to collide (minY > N means a wall)
	private double time= 0; //time starts in 0
 
	public BrownianMotion(String particleStaticFile, String particleDynamicFile) {
		//open static file
        InputStream staticStream = BrownianMotion.class.getClassLoader().getResourceAsStream(particleStaticFile);
        assert staticStream != null;
        Scanner staticScanner = new Scanner(staticStream);
        
        this.N= Integer.parseInt(staticScanner.next()); //First line N
        this.L= Double.parseDouble(staticScanner.next()); //Second line L
        
        particles= new Particle[N];
        walls= new Wall[WALLS]; 
        
	 	//open dynamic file
        InputStream dynamicStream = BrownianMotion.class.getClassLoader().getResourceAsStream(particleDynamicFile);
        assert dynamicStream != null;
        Scanner dynamicScanner = new Scanner(dynamicStream);
        
        //discard first line
        dynamicScanner.next(); //first line is time
        
        int i= 0;
        //read rest of files and add particles to map
        while (dynamicScanner.hasNext() && i < N) {
        	double rad= Float.parseFloat(staticScanner.next());
        	double mass= Float.parseFloat(staticScanner.next());
        	double x= Float.parseFloat(dynamicScanner.next());
        	double y= Float.parseFloat(dynamicScanner.next());
        	double v_x= Float.parseFloat(dynamicScanner.next());
        	double v_y= Float.parseFloat(dynamicScanner.next());
        	
        	particles[i]= new Particle(i, mass, rad, x, y, v_x, v_y);
        	
        	i++;
        }
        
      	staticScanner.close();
		dynamicScanner.close();
		
		walls[0]= new Wall("D", L);
		walls[1]= new Wall("R", L);
		walls[2]= new Wall("U", L);
		walls[3]= new Wall("L", L);

		this.timesToCollision= new double[N][N+4]; //+4 for walls
		initTimes(); //times starts in max value to update with min
	}
	
	private void initTimes() {
		for (int i = 0; i < particles.length; i++) {
			for (int j = 0; j < particles.length + 4; j++) {
				timesToCollision[i][j]= INFINITE;
			}
		}
	}

	public void run() {
		for (int i = 1; i <= MAX; i++) {
			//sets collision times for every pair of particles and with walls for first time
			fillTimes();
			
			//time to first collision
			updateMin();
			
			//evolve system to first collision
			evolve();
			
			//update collision particles
			collision();

			//write output file after steps
			writeOutput();
		}
        System.out.println("Successfully wrote to the file ./resources/dynamic.txt");
	}


	private void fillTimes() {
		double[] zeroVelocity= {0, 0};
		for (int i = 0; i < particles.length; i++) {
			Particle auxP1= particles[i];
			for (int j = i+1; j < particles.length; j++) {	 
				Particle auxP2= particles[j];
				double auxTime= Utils.timeToCollision(auxP1.getPoint(), auxP1.getV(), auxP1.getR(), auxP2.getPoint(), auxP2.getV(), auxP2.getR());
				
				timesToCollision[i][j]= auxTime;
			}
			
			for (int k = 0; k < walls.length; k++) {
				Point2D wallCollisionPoint= walls[k].getCollisionPoint(auxP1);
				double auxTime= Utils.timeToCollision(auxP1.getPoint(), auxP1.getV(), auxP1.getR(), wallCollisionPoint, zeroVelocity, 0);
				
				timesToCollision[i][particles.length + k]= auxTime;
			}
		}
		minTime= INFINITE;
	}

	private void updateMin() {
		for (int i = 0; i < particles.length; i++) {
			for (int j = i+1; j < particles.length; j++) {	 
				compareTime(timesToCollision[i][j], i, j);
			}
			
			for (int k = 0; k < walls.length; k++) {
				compareTime(timesToCollision[i][particles.length + k], i, particles.length + k);
			}
		}
	}

	private void compareTime(double compareTime, int x, int y) {
		if (compareTime > 0 && compareTime < minTime) {
			minTime= compareTime;
			minX= x;
			minY= y;
		}
	}
	
	private void evolve() {
		//update particles position
		for (int i = 0; i < particles.length; i++) {
			particles[i].move(minTime);
		}
		//update collision times
		for (int i = 0; i < particles.length; i++) {
			for (int j = i+1; j < particles.length; j++) {	 
				timesToCollision[i][j]= timesToCollision[i][j] - minTime; 
			}
		}
		time= time + minTime;
	}
	
	private void collision() {
		if (minY >= N) {
			wallCollision();
		} else {
			particlesCollision(particles[minX], particles[minY]);
		}
	}

	private void wallCollision() {
		int wallPosition= minY - N;
		switch (wallPosition) {
		//down wall
		case 0:
			particles[minX].swapVy();
			break;
		
		//right wall
		case 1:
			particles[minX].swapVx();
			break;
		
		//up wall
		case 2:
			particles[minX].swapVy();
			break;
		
		//left wall
		case 3:
			particles[minX].swapVx();
			break;
		}
	}
		
	private void particlesCollision(Particle p1, Particle p2) {
		double jx= Utils.jacobian(p1.getPoint(), p1.getV(), p1.getMass(), p1.getR(), p2.getPoint(), p2.getV(), p2.getMass(), p2.getR(), "X");
		double jy= Utils.jacobian(p1.getPoint(), p1.getV(), p1.getMass(), p1.getR(), p2.getPoint(), p2.getV(), p2.getMass(), p2.getR(), "Y");
		p1.updateV(jx, jy, 1);
		p2.updateV(jx, jy, -1);
	}

	private void writeOutput() {
		try {
            File file = new File("./resources/dynamic.txt");
            FileWriter myWriter = new FileWriter("./resources/dynamic.txt", true); //true to append to file
            myWriter.write("" + time + "\n");
            for (int i = 0; i < particles.length; i++) {				
            	try {
            		Particle particle= particles[i];
            		myWriter.write("" + particle.getX() + "\t" + particle.getY() + "\t" + particle.getVx() + "\t" + particle.getVy() + "\n");
            	} catch (Exception e) {
            		System.err.println("IOException");
            	}
			}
            myWriter.close();
        } catch (IOException e) {
            System.out.println("IOException ocurred");
            e.printStackTrace();
        }
	}

	static public void main(String[] args) throws IOException {
		System.out.println("Static");
		BufferedReader readerStatic = new BufferedReader(new InputStreamReader(System.in));
		String staticInput = readerStatic.readLine();
		
		System.out.println("Dynamic");
		BufferedReader readerDynamic= new BufferedReader(new InputStreamReader(System.in));
		String dynamicInput = readerDynamic.readLine();
				
		String staticFile= (staticInput.length() == 0) ? "static.txt" : staticInput;
		String dynamicFile= (dynamicInput.length() == 0) ? "dynamic.txt" : dynamicInput;
		
		System.out.println("Starting with " + staticFile + ", " + dynamicFile);
		
		BrownianMotion brownian= new BrownianMotion(staticFile, dynamicFile);
		brownian.run();

	}

}
