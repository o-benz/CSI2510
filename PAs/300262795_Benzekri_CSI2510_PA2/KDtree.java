//Student Name: Omar Benzekri
//Student Number: 300262795

public class KDtree {

	public class KDnode {

		public Point3D point;
		public int axis;
		public double v; 
		public KDnode left;
		public KDnode right;

		public KDnode(Point3D pts, int axis) {
			this.point= pts;
			this.axis= axis;
			this.v= pts.get(axis);
			left= right= null;
		}

/**
		public boolean hasLeft(){
			return this.left != null;
		}

		public boolean hasRight(){
			return this.right != null;
		}
**/

	}
	private KDnode root;
	private int ca;		//curent axis
	
	public KDtree() {
		root = null;
	}
	
	public KDnode getRoot(){
		return this.root;
	}
	
	public KDnode insert(Point3D p, KDnode node, int ca) {
		if (root == null){
			root = new KDnode(p, ca);
		}

		if (node == null)
			node = new KDnode(p, ca);
		else if (p.get(ca) <= node.v)
			node.left = insert(p, node.left, (ca + 1) % 3);
		else
			node.right = insert(p, node.right, (ca + 1) % 3);

		return node;
	}
}