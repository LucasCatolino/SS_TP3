package files;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;


public class Writer {
	
	private static final double R_1= 0.2;
	private static final double M_1= 0.9;
	private static final double R_2= 0.7;
	private static final double M_2= 2;
	private static final int V= 2;
	private static final int DEGREES= 360;
		
    public Writer(int L, int N, String type) {
    	
		try {
            File file = new File("./resources/" + type + ".txt");
            FileWriter myWriter = new FileWriter("./resources/" + type + ".txt");
            try {
            	if (type.compareTo("static") == 0) {
					this.staticFile(L, N, myWriter);
				} else {
					this.dynamicFile(L, N, myWriter);
				}
			} catch (Exception e) {
				System.err.println("IOException");
			}
            myWriter.close();
            System.out.println("Successfully wrote to the file ./resources/" + type + ".txt");
        } catch (IOException e) {
            System.out.println("IOException ocurred");
            e.printStackTrace();
        }
    }
    
	private void staticFile(int l, int n, FileWriter myWriter) throws IOException {
		myWriter.write("" + n + "\n"); //N
		myWriter.write("" + l + "\n"); //L
		myWriter.write("" + R_2 + "\t" + M_2 + "\n");
		for (int i = 0; i < n-1; i++) { //n-1 because the first one was already written
			myWriter.write("" + R_1 + "\t" + M_1 + "\n");		
		}
	}

	private void dynamicFile(int l, int n, FileWriter myWriter) throws IOException {
		Point2D center = new Point2D.Float(l/2, l/2);
		ArrayList<Point2D> particles= new ArrayList<>();
		particles.add(center);
		double limitInf= R_1;
		double limitSup= l - R_1;
		
		//first particle centered and still
		myWriter.write("" + l/2 + "\t" + l/2 + "\t0\t0\n");
		
		//write n particles not overlapped
		while (particles.size() < n) {
			double x= (Math.random() * (limitSup - limitInf) + limitInf);
			double y= (Math.random() * (limitSup - limitInf) + limitInf);
			
			Point2D auxPoint = new Point2D.Float();
			auxPoint.setLocation(x, y);
			
			boolean particleNear= false;
			
			if (auxPoint.distance(center) < R_2) {
				particleNear= true;
			} else {
				for (Iterator particle = particles.iterator(); particle.hasNext();) {
					Point2D point2d = (Point2D) particle.next();
					if (point2d.distance(auxPoint) < R_1) {
						particleNear= true;
						break;
					}
				}
			}
			
			if (!particleNear) {
				particles.add(auxPoint);
				
				double v= Math.random() * V; //module of v is 2
				double d= Math.random() * DEGREES;
				myWriter.write("" + x + "\t" + y + "\t" + v * Math.sin(Math.toRadians(d)) + "\t"
						+  v * Math.cos(Math.toRadians(d)) + "\n"); //x y v_x v_y not overlapped and with |v|<2
			}
		}		
	}

}