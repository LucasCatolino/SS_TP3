package files;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;


public class AnimationFilesCreator {
	
	private static final int MAX= 100000;
	private double[] radius;

	public AnimationFilesCreator() throws IOException {
		
		InputStream staticStream = AnimationFilesCreator.class.getClassLoader().getResourceAsStream("static.txt");
		assert staticStream != null;
		Scanner staticScanner = new Scanner(staticStream);
		
		int N= Integer.parseInt(staticScanner.next()); //First line N
        double L= Double.parseDouble(staticScanner.next()); //Second line L
        
        radius= new double[N];
        
        int count= 0;
        while (staticScanner.hasNext() && count < N) {
        	radius[count]= Double.parseDouble(staticScanner.next()); //First element radius
        	staticScanner.next(); //Second element mass
        	count ++;
        }
        
        staticScanner.close();
        
        File file = new File("./resources/dynamic.xyz");
        FileWriter myWriter = new FileWriter("./resources/dynamic.xyz");
		
		InputStream dynamicStream = AnimationFilesCreator.class.getClassLoader().getResourceAsStream("dynamic.txt");
		assert dynamicStream != null;
		Scanner dynamicScanner = new Scanner(dynamicStream);
        int i= 0;
        boolean reachBorder= false;
        while (dynamicScanner.hasNext() && i < MAX && !reachBorder) {
			try {

	            myWriter.write("" + (N+4 + "\n"));
	            myWriter.write("t=" + Double.parseDouble(dynamicScanner.next()) + "\n");
	            
	            int red= 1;
	            int blue= 0;
	            
	            double redX= Double.parseDouble(dynamicScanner.next());
	            double redY= Double.parseDouble(dynamicScanner.next());
        		dynamicScanner.next(); //skip vx
        		dynamicScanner.next(); //skip vy
        		myWriter.write("" + redX + "\t" + redY + "\t" + radius[0] + "\t" + red + "\t" + blue + "\n");
        		reachBorder= evaluateBorder(redX, redY, radius[0], L);
        		
        		red= 0;
        		blue= 1;	            
	            for (int j = 1; j < N; j++) {
            		myWriter.write("" + Double.parseDouble(dynamicScanner.next()) + "\t" + Double.parseDouble(dynamicScanner.next()) + "\t" + radius[j] + "\t" + red + "\t" + blue + "\n");
            		dynamicScanner.next(); //skip vx
            		dynamicScanner.next(); //skip vy
				}
	            
	            myWriter.write("0\t0\t0\t0\t0\n");
	            myWriter.write("0\t" + L + "\t0\t0\t0\n");
	            myWriter.write("" + L + "\t0\t0\t0\t0\n");
	            myWriter.write("" + L + "\t" + L + "\t0\t0\t0\n");
	        } catch (IOException e) {
	            System.out.println("IOException ocurred");
	            e.printStackTrace();
	        }
			
			i++;
		}
        dynamicScanner.close();
		
	}
	
	private boolean evaluateBorder(double x, double y, double r, double l) {
		if ((x-r < 0.01) || (x+r > l-0.01) || (y-r < 00.1) || (y+r > l-0.01)) {
			return true;
		}
		return false;
	}

	static public void main(String[] args) throws IOException {
		System.out.println("Creating animation");
		AnimationFilesCreator animator= new AnimationFilesCreator();
		System.out.println("Animation created");
	}

}
