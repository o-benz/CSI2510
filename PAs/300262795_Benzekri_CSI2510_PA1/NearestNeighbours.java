//Name: Omar Benzekri 
//Student Number: 300262795

import java.util.*;
import java.util.List;

public class NearestNeighbours {
    List<Point3D> pts;

    //Constructor

    public NearestNeighbours(List<Point3D> points) 
    {
        this.pts = points;
    }

    //Finds the nearest neighbors (points that are in close proximity) of a 3D point
    
    public List<Point3D> rangeQuery(Point3D p, double eps) 
    {
        List<Point3D> neighbours = new LinkedList<>();
        Iterator<Point3D> n = pts.iterator();
        while (n.hasNext()) {           //O(n)
            Point3D pt = n.next();
            if (p.distance(pt)<=eps) {
                neighbours.add(pt);
            }
        }
        return neighbours;
    }
}