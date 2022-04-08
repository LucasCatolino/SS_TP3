package core;

import java.util.ArrayList;

public class BrownianMotion {
	
	 ArrayList<Particle> particles;
	 private final int N;
	 private final int L;
	 double[][] timesToCollition;
	 
	 public BrownianMotion(String partcileStaticFile, String partcileDynamicFile) {
		 //open static file
		 	//Primera linea: N
		 	//inicializa N
		 	//Segunda linea: L
		 	//inicializa L
		 	//open dynamic file
		 		//N lineas static: radio y masa
		 		//N lineas dynamic: x y v_x v_y
		 		//new Particle (x, y, v_x, v_y, radio, masa)
		 	//close dynamic
		 //close static file
		 this.L= 0; //TODO: inicializar
		 this.N= 0;  //TODO: inicializar
		 this.timesToCollition= new double[N+4][N]; //+4 for walls
	 }
	 
	 public void run() {
		 /*
		  * 2) Se calcula el tiempo hasta el primer choque (evento!) (tc). --> armar la matriz y guardar el minimo
			3) Se evolucionan todas las part�culas seg�n sus ecuaciones de
			movimiento hasta tc .
			4) Se guarda el estado del sistema (posiciones y velocidades) en t = tc
			5) Con el �operador de colisi�n� se determinan las nuevas velocidades
			despu�s del choque, solo para las part�culas que chocaron.
			6) volver a 2).
		  * */
	 }
	

}
