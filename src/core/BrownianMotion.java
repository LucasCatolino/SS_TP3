package core;

import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class BrownianMotion {
	
	//private ArrayList<Particle> particles;
	//private ArrayList<Wall> walls;
	private static final int WALLS= 4;
	private static final int INFINITE= Integer.MAX_VALUE;
	private Particle[] particles;
	private Wall[] walls;
	private final int N;
	private final double L;
	private double[][] timesToCollition;
	private double minTime= INFINITE;
	private double minX= 0; //position of first particle to collide
	private double minY= 0; //position of second particle or wall to collide (minY > N means a wall)
 
	public BrownianMotion(String particleStaticFile, String particleDynamicFile) {
		//particles= new ArrayList<>();
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
        
        int i= 0;
        //read rest of files and add particles to map
        while (dynamicScanner.hasNext() && i < N) {
        	double rad= Float.parseFloat(staticScanner.next());
        	double mass= Float.parseFloat(staticScanner.next());
        	double x= Float.parseFloat(dynamicScanner.next());
        	double y= Float.parseFloat(dynamicScanner.next());
        	double v_x= Float.parseFloat(dynamicScanner.next());
        	double v_y= Float.parseFloat(dynamicScanner.next());
        	
        	//particles.add(new Particle(i, mass, rad, x, y, v_x, v_y));
        	particles[i]= new Particle(i, mass, rad, x, y, v_x, v_y);
        	
        	i++;
        }
        
      	staticScanner.close();
		dynamicScanner.close();
		
		//walls.add(new Wall("D", L));
		//walls.add(new Wall("R", L));
		//walls.add(new Wall("U", L));
		//walls.add(new Wall("L", L));
		walls[0]= new Wall("D", L);
		walls[1]= new Wall("R", L);
		walls[2]= new Wall("U", L);
		walls[3]= new Wall("L", L);

		this.timesToCollition= new double[N][N+4]; //+4 for walls
		initTimes(); //times starts in max value to update with min
	}
	
	private void initTimes() {
		for (int i = 0; i < particles.length; i++) {
			for (int j = 0; j < particles.length + 4; j++) {
				timesToCollition[i][j]= INFINITE;
			}
		}
	}

	public void run() {
		
		//Sets collision times for every pair of particles and with walls and saves min time and pair
		fillTimes();
		
		 /*
		  * 2) Se calcula el tiempo hasta el primer choque (evento!) (tc). --> armar la matriz y guardar el minimo
			3) Se evolucionan todas las partículas según sus ecuaciones de
			movimiento hasta tc .
			4) Se guarda el estado del sistema (posiciones y velocidades) en t = tc
			5) Con el “operador de colisión” se determinan las nuevas velocidades
			después del choque, solo para las partículas que chocaron.
			6) volver a 2).
		  * */
	}
	
	private void fillTimes() {
		double[] zeroVelocity= {0, 0};
		for (int i = 0; i < particles.length; i++) {
			Particle auxP1= particles[i];
			for (int j = i+1; j < particles.length; j++) {	 
				Particle auxP2= particles[j];
				double auxTime= Utils.timeToCollision(auxP1.getPoint(), auxP1.getV(), auxP1.getR(), auxP2.getPoint(), auxP2.getV(), auxP2.getR());
				
				timesToCollition[i][j]= auxTime;
				compareTime(auxTime, i, j);
			}
			
			for (int k = 0; k < walls.length; k++) {
				Point2D wallCollisionPoint= walls[k].getCollisionPoint(auxP1);
				double auxTime= Utils.timeToCollision(auxP1.getPoint(), auxP1.getV(), auxP1.getR(), wallCollisionPoint, zeroVelocity, 0);
				
				timesToCollition[i][particles.length + k - 1]= auxTime;
				compareTime(auxTime, i, particles.length + k - 1);
			}
		}
	}

	private void compareTime(double time, int x, int y) {
		if (time < minTime) {
			minTime= time;
			minX= x;
			minY= y;
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
		
		System.out.println("Starting with " + staticFile + " and " + dynamicFile);
		
		BrownianMotion brownian= new BrownianMotion(staticFile, dynamicFile);
		brownian.run();

	}

}
