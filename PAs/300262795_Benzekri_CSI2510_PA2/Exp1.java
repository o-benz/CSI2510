//Student Name: Omar Benzekri
//Student Number: 300262795

import java.util.List;
import java.util.ArrayList;
import java.util.Scanearester;  
import java.io.*;

public class Exp1 {

    public static List<Point3D> read(String filename){
        String in;
        double x, y, z;
        List<Point3D> db = new ArrayList<Point3D>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            in = reader.readLine(); 
            while ((in = reader.readLine()) != null) { 
                String[] array = in.split(","); 
                x = Double.valueOf(array[0]);
                y = Double.valueOf(array[1]);
                z = Double.valueOf(array[2]);
                db.add( new Point3D(x, y, z) );
            }
            reader.close();
        } catch (IOException e) {}
        return db;
    }

	public static void main(String[] args) throws Exception {
		String type = args[0];
		double eps= Double.parseDouble(args[1]);
		List<Point3D> points= Exp1.read(args[2]); 
		List<Point3D> neighbours = new ArrayList<Point3D>();
		Point3D query= new Point3D(Double.parseDouble(args[3]), Double.parseDouble(args[4]), Double.parseDouble(args[5]));
		Neighbours nearest;

		if (type.equals("kd")){
			nearest = new NearestNeighboursKD(points);
		}
		else if (type.equals("lin")){
			nearest = new NearestNeighbours(points);
		}

		neighbours= nearest.rangeQuery(query,eps);
		
		try{
			BufferedWriter writer = new BufferedWriter(new FileWriter("pt_" + type + ".txt"));
			for (Point3D p : neighbours)
				writer.write(p + "\n");
			writer.close();
		} catch (Exception e){}


	}
}