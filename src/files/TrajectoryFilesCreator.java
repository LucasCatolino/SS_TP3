package files;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;


public class TrajectoryFilesCreator {
	
	private static final double RADIUS= 0.01;
	private static final int RED= 1;

	public TrajectoryFilesCreator() throws IOException {
		
		InputStream staticStream = TrajectoryFilesCreator.class.getClassLoader().getResourceAsStream("static.txt");
		assert staticStream != null;
		Scanner staticScanner = new Scanner(staticStream);
		
		int N= Integer.parseInt(staticScanner.next()); //First line N
        double L= Double.parseDouble(staticScanner.next()); //Second line L
        
        staticScanner.close();
        
        int movements= getMovements();
        
        File file = new File("./resources/trajectory.xyz");
        FileWriter myWriter = new FileWriter("./resources/trajectory.xyz");
        
        myWriter.write("" + (movements + 4 + "\n"));
        myWriter.write("\n");
		
		InputStream dynamicStream = TrajectoryFilesCreator.class.getClassLoader().getResourceAsStream("dynamic.xyz");
		assert dynamicStream != null;
		Scanner dynamicScanner = new Scanner(dynamicStream);
		int count= 0;
        while (dynamicScanner.hasNext() && count < movements) {
        	dynamicScanner.next(); //N
        	dynamicScanner.next(); //time
        	
        	double x=  Double.parseDouble(dynamicScanner.next());
        	double y=  Double.parseDouble(dynamicScanner.next());
        	myWriter.write("" + x + "\t" + y + "\t" + RADIUS + "\t" + RED + "\n"); //X Y Radius Red
        	
        	dynamicScanner.next(); //Radius
        	dynamicScanner.next(); //Red
        	dynamicScanner.next(); //Blue
        	
        	count ++;
        	for (int i = 0; i < N + 3; i++) { //N+3 because first particle was readed, the others are discarded
        		try {
        			dynamicScanner.next(); //X
        			dynamicScanner.next(); //Y
        			dynamicScanner.next(); //Radius
        			dynamicScanner.next(); //Red
        			dynamicScanner.next(); //Blue					
				} catch (Exception e) {
					for (int j = 0; j < movements - count; j++) {
			        	myWriter.write("" + x + "\t" + y + "\t" + RADIUS + "\t" + RED + "\n"); //In case of error, it prints last position to complete
					}
				}
			}

		}
        
        myWriter.write("0\t0\t0\t0\t0\n");
        myWriter.write("0\t" + L + "\t0\t0\t0\n");
        myWriter.write("" + L + "\t0\t0\t0\t0\n");
        myWriter.write("" + L + "\t" + L + "\t0\t0\t0\n");
        dynamicScanner.close();
        myWriter.close();
		
	}
	
	private int getMovements() throws IOException {
		int movements= 0;
		InputStream dynamicStream = TrajectoryFilesCreator.class.getClassLoader().getResourceAsStream("dynamic.xyz");
		assert dynamicStream != null;
		Scanner dynamicScanner = new Scanner(dynamicStream);
        while (dynamicScanner.hasNext()) {
        	String token= dynamicScanner.next();
        	if (token.contains("t")) {
				movements ++;
			}
        }
		dynamicScanner.close();
		return movements;
	}

	static public void main(String[] args) throws IOException {
		System.out.println("Creating trajectory");
		TrajectoryFilesCreator trajectory= new TrajectoryFilesCreator();
		System.out.println("Trajectory created");
	}

}
