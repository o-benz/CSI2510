//Student Name: Omar Benzekri
//Student Number: 300262795

import java.util.List;
import java.util.ArrayList;

public class NearestNeighboursKD extends Neighbours{
	private KDtree kd;			//The point cloud database
	public NearestNeighboursKD(List<Point3D> kd) {
		this.kd = new KDtree();

		for (Point3D p : kd) {
			this.kd.insert(p, this.kd.getRoot(), 0);
		}
	}

	//Getter

	public KDtree getTree(){
		return this.kd;
	}

	public List<Point3D> rangeQuery(Point3D p, double eps) {
		List neighbours = new ArrayList<Point3D>();
		KDtree.KDnode node = this.getTree().getRoot();
		if (node == null)
			return neighbours;
		if (p.distance(node.point) < eps) 
			neighbours.add(node.point); 
		if (p.get(node.axis) - eps <= node.value)
			rangeQuery(p, eps, neighbours, node.left);
		if (p.get(node.axis) + eps > node.value) 
			rangeQuery(p, eps, neighbours, node.right);
		return neighbours;
	}
}