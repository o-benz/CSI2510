//Name: Omar Benzekri 
//Student Number: 300262795

public class Point3D {
    private double x, y, z; 
    private double r, g, b;
    private int label;

    //Overloading Constructors

    Point3D(double xCoord, double yCoord, double zCoord) {
        this.x = xCoord;
        this.y = yCoord;
        this.z = zCoord;
    }

    Point3D(double xCoord, double yCoord, double zCoord, int label) {
        this.x = xCoord;
        this.y = yCoord;
        this.z = zCoord;
        this.label = label;
    }

    Point3D(double xCoord, double yCoord, double zCoord, int label, double r, double g, double b) {
        this.x = xCoord;
        this.y = yCoord;
        this.z = zCoord; 
        this.label = label;
        this.r = r;
        this.g = g;
        this.b = b;
    }

    //Getters

    public double getX() { return x; }
    public double getY() { return y; }
    public double getZ() { return z; }

    public int getLabel() { return label; }

    //Setters

    public void setX(double x) {this.x = x;}
    public void setY(double y) {this.y = y;}
    public void setZ(double z) {this.z = z;}

    public void setLabel(int label) {this.label = label;}
    
    //Finds the inner product

    private static double innerProduct(double a, double b, double c) {
        double result = a*a+b*b+c*c;
        return result;
    }

    //Computes the Euclidean distance between two points

    public double distance(Point3D p) 
    {
        double result = Math.sqrt(innerProduct(this.x-p.x, this.y-p.y, this.z-p.z));
        return result;
    }

}