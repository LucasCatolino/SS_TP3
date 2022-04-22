package files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;


public class CollisionsFileCreator {
	
	public CollisionsFileCreator(int dt) throws IOException {
		int time= dt;
		
		InputStream staticStream = CollisionsFileCreator.class.getClassLoader().getResourceAsStream("static.txt");
		assert staticStream != null;
		Scanner staticScanner = new Scanner(staticStream);
		
		int N= Integer.parseInt(staticScanner.next()); //First line N
        
        staticScanner.close();
                
        File file = new File("./resources/collisions.txt");
        FileWriter myWriter = new FileWriter("./resources/collisions.txt");
        
        myWriter.write("Time" + "\tCollisions\n");
        myWriter.write("" + time + "\t");

		InputStream dynamicStream = CollisionsFileCreator.class.getClassLoader().getResourceAsStream("dynamic.xyz");
		assert dynamicStream != null;
		Scanner dynamicScanner = new Scanner(dynamicStream);

		int collisions= 0;
        while (dynamicScanner.hasNext()) {
        	dynamicScanner.next(); //N
        	double timeToken= Double.parseDouble(dynamicScanner.next().replace("t=", "")); //time
        	
        	if (timeToken > time) {
        		myWriter.write("" + collisions + "\n");
				time= time + dt;
        		myWriter.write("" + time + "\t");

				collisions= 0;
			}
        	
        	for (int i = 0; i < N + 4; i++) {
        		try {
        			dynamicScanner.next(); //X
        			dynamicScanner.next(); //Y
        			dynamicScanner.next(); //Radius
        			dynamicScanner.next(); //Red
        			dynamicScanner.next(); //Blue				
				} catch (Exception e) {
		        	//end of file
				}
			}
        	collisions ++;
		}
        
		myWriter.write("" + collisions + "\n");

        myWriter.close();		
	}

	static public void main(String[] args) throws IOException {
		System.out.println("Insert dt (default 5)");
		BufferedReader readerDt= new BufferedReader(new InputStreamReader(System.in));
		int dtInput = Integer.parseInt(readerDt.readLine());
		int dt= (dtInput > 0) ? dtInput : 2;
		
		System.out.println("Creating collisions file");
		CollisionsFileCreator trajectory= new CollisionsFileCreator(dt);
		System.out.println("Collisions file created");
	}

}
