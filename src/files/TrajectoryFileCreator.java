package files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.util.Scanner;


public class TrajectoryFileCreator {
	
	private static final double RADIUS= 0.01;
	private static final int RED= 1;
	private static final int GREEN= 1;
	private static final int BLUE= 1;
	private int movements;
	

	public TrajectoryFileCreator(String d1, String d2, String d3, String d4, String d5, double time) throws IOException {
		
		InputStream staticStream = TrajectoryFileCreator.class.getClassLoader().getResourceAsStream("static.txt");
		assert staticStream != null;
		Scanner staticScanner = new Scanner(staticStream);
		
		int N= Integer.parseInt(staticScanner.next()); //First line N
        double L= Double.parseDouble(staticScanner.next()); //Second line L
        
        staticScanner.close();
        
        this.movements= 0;
        
        File file = new File("./resources/trajectory.xyz");
        FileWriter myWriter = new FileWriter("./resources/trajectory.xyz");
        
        myWriter.write("          \n");
        
        writeFile(myWriter, d1, time, RED, 0, 0);
        writeFile(myWriter, d2, time, 0, GREEN, 0);
        writeFile(myWriter, d3, time, 0, 0, BLUE);
        writeFile(myWriter, d4, time, RED, 0, BLUE);
        writeFile(myWriter, d5, time, RED, GREEN, BLUE);
        
        myWriter.write("0\t0\t0\t0\t0\t0\n");
        myWriter.write("0\t" + L + "\t0\t0\t0\t0\n");
        myWriter.write("" + L + "\t0\t0\t0\t0\t0\n");
        myWriter.write("" + L + "\t" + L + "\t0\t0\t0\t0\n");
        
        myWriter.close();
        
        appendMovements();
	}
	
	private void writeFile(FileWriter myWriter, String dynamic, double time, int r, int g, int b) throws IOException {
		System.out.println("Reading " + dynamic);
		//Open dynamic file
		InputStream dynamicStream = TrajectoryFileCreator.class.getClassLoader().getResourceAsStream(dynamic);
		assert dynamicStream != null;
		Scanner dynamicScanner = new Scanner(dynamicStream);
		
		boolean endTime= false;
        while (dynamicScanner.hasNext() && !endTime) {
        	double n=  Double.parseDouble(dynamicScanner.next()); //N particles
        	double timeToken= Double.parseDouble(dynamicScanner.next().replace("t=", "")); //Time
        	
        	endTime= (timeToken > time) ? true : false;
        	
        	double x=  Double.parseDouble(dynamicScanner.next());
        	double y=  Double.parseDouble(dynamicScanner.next());
        	myWriter.write("" + x + "\t" + y + "\t" + RADIUS + "\t" + r + "\t" + g + "\t" + b + "\n"); //X Y Radius Red Green Blue
        	
        	movements ++; //One movement was written
        	
        	dynamicScanner.next(); //Radius
        	dynamicScanner.next(); //Red
        	dynamicScanner.next(); //Blue
        	
        	for (int i = 0; i < n - 1; i++) { //n - 1 because first particle was readed, the others are discarded
        		try {
        			dynamicScanner.next(); //X
        			dynamicScanner.next(); //Y
        			dynamicScanner.next(); //Radius
        			dynamicScanner.next(); //Red
        			dynamicScanner.next(); //Blue
				} catch (Exception e) {
					System.out.println("IO exception: " + dynamic + " in time " + timeToken);
				}
			}
		}
        dynamicScanner.close();
	}
	
	private void appendMovements() throws IOException {
		RandomAccessFile file = new RandomAccessFile("./resources/trajectory.xyz", "rw");
        file.seek(0); //Append to first position
        String movementsString= "" + (movements + 4) + "\n";
        file.write(movementsString.getBytes());
        file.close();
	}

	static public void main(String[] args) throws IOException {
		//file 1
		System.out.println("File 1 (default d1.xyz)");
		BufferedReader readerDynamic1= new BufferedReader(new InputStreamReader(System.in));
		String dynamic1Input = readerDynamic1.readLine();		
		String dynamic1File= (dynamic1Input.length() == 0) ? "d1.xyz" : dynamic1Input;
		
		//file 2
		System.out.println("File 2 (default d2.xyz)");
		BufferedReader readerDynamic2= new BufferedReader(new InputStreamReader(System.in));
		String dynamic2Input = readerDynamic2.readLine();		
		String dynamic2File= (dynamic2Input.length() == 0) ? "d2.xyz" : dynamic2Input;
		
		//file 3
		System.out.println("File 3 (default d3.xyz)");
		BufferedReader readerDynamic3= new BufferedReader(new InputStreamReader(System.in));
		String dynamic3Input = readerDynamic3.readLine();		
		String dynamic3File= (dynamic3Input.length() == 0) ? "d3.xyz" : dynamic3Input;
		
		//file 4
		System.out.println("File 4 (default d4.xyz)");
		BufferedReader readerDynamic4= new BufferedReader(new InputStreamReader(System.in));
		String dynamic4Input = readerDynamic4.readLine();		
		String dynamic4File= (dynamic4Input.length() == 0) ? "d4.xyz" : dynamic4Input;
		
		//file 5
		System.out.println("File 5 (default d5.xyz)");
		BufferedReader readerDynamic5= new BufferedReader(new InputStreamReader(System.in));
		String dynamic5Input = readerDynamic5.readLine();		
		String dynamic5File= (dynamic5Input.length() == 0) ? "d5.xyz" : dynamic5Input;
		
		System.out.println("Time (default 5)");
		BufferedReader readerTime= new BufferedReader(new InputStreamReader(System.in));
		double timeInput = Double.parseDouble(readerTime.readLine());
		double time= (timeInput > 0) ? timeInput : 5;
		
		System.out.println("Creating trajectory");
		TrajectoryFileCreator trajectory= new TrajectoryFileCreator(dynamic1File, dynamic2File, dynamic3File, dynamic4File, dynamic5File, time);
		System.out.println("Trajectory created");
	}

}
