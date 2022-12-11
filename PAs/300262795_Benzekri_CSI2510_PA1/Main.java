//Name: Omar Benzekri 
//Student Number: 300262795

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Main {                                     //Main method to run the DBScan algorithm
    public static void main(String[] args) throws FileNotFoundException {
        List dbs = new ArrayList();
        dbs=(DBScan.read(filename));
        DBScan scan;
        scan = new DBScan(dbs);                              
        scan.setEps(1);                                 //Setting the eps and MinPts, it should be done via Terminal but had to do so manually
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
        scan.save(filename+"_clusters_0.4_20_nclusters");             


    }
}