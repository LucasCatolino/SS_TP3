package files;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;

@SuppressWarnings("unused") //Warnings because Writer is not used, it only creates files
public class FilesCreator {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, IOException {

    	boolean correctL= false;
    	int L= 0;
    	while (!correctL) {    		
    		System.out.println("Insert L (> 0)");
    		BufferedReader readerL = new BufferedReader(new InputStreamReader(System.in));
    		String auxL = readerL.readLine();
    		L= Integer.valueOf(auxL);
    		correctL= (L > 0) ? true : false;
    	}
  
    	boolean correctN= false;
    	int N= 0;
    	while (!correctN) {    		
    		System.out.println("Insert N (> 0)");
    		BufferedReader readerN = new BufferedReader(new InputStreamReader(System.in));
    		String auxN = readerN.readLine();
    		N= Integer.valueOf(auxN);
    		correctN= (N > 0) ? true : false;
    	}

    	System.out.println("L: " + L + " N: " + N);
    	
    	Writer writerStatic = new Writer(L, N, "static");
		Writer writerDynamic = new Writer(L, N, "dynamic");

    }

}
