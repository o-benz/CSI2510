//Name: Omar Benzekri 
//Student Number: 300262795

import java.io.*;
import java.util.List;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

public class DBScan 
{

    //Class Variables

    private List<Point3D> points;
    private double eps;
    private int minPts;
    private int clusters;

    //Overloading Constructors

    public DBScan(List<Point3D> pointList) 
    {
        points = pointList;
    }

    public DBScan(List<Point3D> pointList, double eps, int minPts) 
    {
        this.points = pointList;
        this.eps = eps;
        this.minPts = minPts;
    }

    //Setters

    public double setEps(double eps) 
    {
        this.eps = eps;
        return eps;
    }

    public double setMinPts(int minPts) 
    {
        this.minPts = minPts;
        return minPts;
    }

    //Pushing all elements of our List<Point3D> to our Stack<Point3D>

    private static void pushListToStack(List<Point3D> x, Stack<Point3D> sta) 
    {
        Iterator<Point3D> i = x.iterator();
        while (i.hasNext()) {
            sta.push(i.next());
        }
    }  

    //Executing the DBScan Algorithm

    public void findClusters() 
    {
        Stack<Point3D> sta = new Stack<Point3D>();
        int currentClusterLabel = 0;
        Iterator<Point3D> pts = points.iterator();
        NearestNeighbours neigh = new NearestNeighbours(points);
        List<Point3D> neighbours;
        Point3D scndPoint;
        while (pts.hasNext()) {
            Point3D point = pts.next();                 //O(n)
            if (point.getLabel()!=-1) {
                continue;
            }
            neighbours = neigh.rangeQuery(point, eps);
            if (neighbours.size() <minPts) {
                point.setLabel(-2);
            }
            else {
                currentClusterLabel++;
                point.setLabel(currentClusterLabel);
                pushListToStack(neighbours, sta);     
                while (!sta.isEmpty()) {                //O(n^2)
                    scndPoint = sta.pop();
                    if (scndPoint.getLabel()==-2) {
                        scndPoint.setLabel(currentClusterLabel);
                        
                    }
                    else if (scndPoint.getLabel()!=-1) {
                        continue;
                    }
                    scndPoint.setLabel(currentClusterLabel);
                    neighbours = neigh.rangeQuery(scndPoint,eps);
                    if (neighbours.size()>=minPts) {
                        pushListToStack(neighbours,sta);
                    }
                }
            }
        }
    }

    //Getters

    public int getClusters() { return clusters; }

    public List<Point3D> getPoints() { return points; }
    
    private static List<int[]> numberOfPointsInClusters(List<Point3D> points)
    {
        List<int[]> result = new LinkedList<>();
        int[] temp;
        int clusterLabel=0;
        int numInCluster=0;
        for (Point3D elem: points) {
            if (elem.getLabel()==clusterLabel) {    //O(n)
                numInCluster++;
            }
            else if (elem.getLabel()!=clusterLabel && elem.getLabel()!=-2) {
                temp = new int[2];
                temp[0] = clusterLabel;
                temp[1] = numInCluster;
                result.add(temp);
                numInCluster = 0;
                clusterLabel = elem.getLabel();
            }
        }
        return result;
    }

    private static int numberOfNoisePoints(List<Point3D> points) 
    {
        int noisePts=0;
        for (Point3D elem: points) {
            if (elem.getLabel()==-2) {              //O(n)
                noisePts++;
            }
        }
        return noisePts;
    }

    //Accepts a filename and returns a List<Point3D>

    public static List<Point3D> read(final String filename) 
    {
        List<Point3D> pts = new LinkedList<>();
        BufferedReader reader;
        File toRead = new File(filename);
        
        try {
            reader = new BufferedReader(new FileReader(toRead));
            String[] coordsNSuch;
            reader.readLine();
            while (reader.readLine()!=null) {
            coordsNSuch=reader.readLine().split(", ");
            pts.add(new Point3D(Double.parseDouble(coordsNSuch[0]), Double.parseDouble(coordsNSuch[1]), Double.parseDouble(coordsNSuch[2]),-1));
            }

            reader.close();
        }

        //Catching Potential Exceptions

        catch (NumberFormatException e) {
            throw new NumberFormatException("The first three columns should be doubles.");
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException("File not found.");
        }
        catch (IOException e) {
            throw new RuntimeException("IOException.");
        }
        return pts;
    }
    
    //Saves all the points with their cluster label and associated RGB color

    public void save(String filename) {
        double[] rgb = new double[3];
        rgb[0] =1;
        int indexOfIncrement= 1;
        File outputFile = new File(filename+".csv");
        String columnNames = "x, y, z, C, R, G, B";
        try {
            FileWriter write = new FileWriter(outputFile);
            write.append(columnNames);
            write.append("\n");
            for (int i =0; i<=this.getClusters(); i++) {
                for (Point3D p:points) {            //O(n)
                    if (p.getLabel()!=i) {          //O(n^2)
                        continue;
                    }
                    else {
                        write.append(buildLine(rgb, p));
                        write.append("\n");
                    }
                }
                if ((rgb[indexOfIncrement]+0.25)%1==1||(rgb[indexOfIncrement]+0.25)%1==0) 
                {
                    rgb[indexOfIncrement] = (rgb[indexOfIncrement]+0.25)%1;
                    indexOfIncrement = (indexOfIncrement+1)%3;
                }
                rgb[indexOfIncrement]= rgb[indexOfIncrement]+0.25;
            }
            write.close();

        }

        //Catching Potential Exception

        catch (IOException e) {
            throw new RuntimeException("IOException.");
        }
    }

    private static String buildLine(double[] rgb, Point3D p) {
        StringBuilder b = new StringBuilder();
        b.append(p.getX());
        b.append(", ");
        b.append(p.getY());
        b.append(", ");
        b.append(p.getZ());
        b.append(", ");
        b.append(p.getLabel());
        b.append(", ");
        b.append(rgb[0]);
        b.append(", ");
        b.append(rgb[1]);
        b.append(", ");
        b.append(rgb[2]);
        return b.toString();
    }

    public static void main(String[] args) throws FileNotFoundException {
        /*
        List dbs = new ArrayList();
        dbs=(DBScan.read(filename));
        DBScan scan;
        scan = new DBScan(dbs);                              
        scan.setEps(1);                                     //Setting the eps and MinPts, it should be done via Terminal but had to do so manually
        scan.setMinPts(10);
        scan.findCluster();                                
        System.out.println(scan.getNumberOfClusters());
        List cluster = scan.clusterSize(dbs);
        int sum=0;
        for (int i = 0; i < cluster.size(); i++) {
            System.out.println(cluster.get(i));
            sum=sum+(int) cluster.get(i);
        }
        System.out.println("sum = " + sum );
        scan.save(filename+"_clusters_0.4_20_nclusters");   //Saving the new file            
        */

    }
}