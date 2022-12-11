//Student Name: Omar Benzekri
//Student Number: 300262795

import java.util.List;
import java.util.ArrayList;
import java.io.*;

public class Exp2{

	public static void main(String[] args){	
		String type = args[0].toLowerCase();
		double eps= Double.parseDouble(args[1]);
		List<Point3D> points= Exp1.read(args[2]); 
		long startT, endT;
		Point3D query;
		List<Long> times = new ArrayList<Long>();
		Neighbours nearest;

		if (type.equals("kd")){
			nearest = new NearestNeighboursKD(points);
		}
		else if (type.equals("lin")){
			nearest = new NearestNeighbours(points);
		}

		for (int i = 10; i < points.size(); i+=10){
			query = points.get(i);	
			startT = System.nanoTime();
			nearest.rangeQuery(query,eps);
			endT = System.nanoTime();
			times.add(endT - startT);
		}

		long avrg = 0;
		for (long l : times)
			avrg+=l;

		avrg = avrg/times.size();

		System.out.println("The average computing time for rangeQuery using is " + avrg + " ns.");

	}
}