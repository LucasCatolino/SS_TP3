package core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class BrownianMotion {
	
	 private ArrayList<Particle> particles;
	 private final int N;
	 private final int L;
	 double[][] timesToCollition;
	 
	 public BrownianMotion(String particleStaticFile, String particleDynamicFile) {
		particles= new ArrayList<>();
		//open static file
        InputStream staticStream = BrownianMotion.class.getClassLoader().getResourceAsStream(particleStaticFile);
        assert staticStream != null;
        Scanner staticScanner = new Scanner(staticStream);
        
        this.N= Integer.parseInt(staticScanner.next()); //First line N
        this.L= Integer.parseInt(staticScanner.next()); //Second line L

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
        	
        	particles.add(new Particle(i, mass, rad, x, y, v_x, v_y));
        	
        	i++;
        }
        
		staticScanner.close();
		dynamicScanner.close();
				
		this.timesToCollition= new double[N+4][N]; //+4 for walls
	 }
	 
	 public void run() {
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
