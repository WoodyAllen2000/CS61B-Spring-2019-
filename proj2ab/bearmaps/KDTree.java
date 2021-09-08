package bearmaps;

import java.util.List;

public class KDTree {

    List<Point> points;
    Node root;

    public KDTree(List<Point> points) {
        this.points = points;
        this.root = new Node(points.get(0));
        if (points.size() > 1) {
            for (int i = 1; i < points.size(); i++) {
                root.add(new Node(points.get(i)));
            }
        }
    }

    public Point nearest(double x, double y) {
        return nearestHelper(root, new Point(x, y), root).p;
    }

    private Node nearestHelper(Node n, Point goal, Node best) {
        if (n == null) {
            return best;
        }
        Node goodSide;
        Node badSide;
        if (Double.compare(Point.distance(n.p, goal), Point.distance(best.p, goal)) < 0) {
            best = n;
        }
        if (n.length % 2 == 0) {
            if (Double.compare(goal.getX(), n.p.getX()) < 0) {
                goodSide = n.leftChild;
                badSide = n.rightChild;
            }else {
                goodSide = n.rightChild;
                badSide = n.leftChild;
            }
        }else {
            if (Double.compare(goal.getY(), n.p.getY()) < 0) {
                goodSide = n.leftChild;
                badSide = n.rightChild;
            }else {
                goodSide = n.rightChild;
                badSide = n.leftChild;
            }
        }
        best = nearestHelper(goodSide, goal, best);
        if (!isPruning(n, goal, best)) {
            best = nearestHelper(badSide, goal, best);
        }
        return best;
    }

    private boolean isPruning(Node n, Point goal, Node best) {
        double shortestDistance = Point.distance(best.p, goal);
        double shortestDistanceToBadSide;
        if (n.length % 2 == 0) {
            shortestDistanceToBadSide = Point.distance(new Point(n.p.getX(), goal.getY()), goal);
        }else {
            shortestDistanceToBadSide = Point.distance(new Point(goal.getX(), n.p.getY()), goal);
        }
        return Double.compare(shortestDistanceToBadSide, shortestDistance) >= 0;
    }

    private static class Node {

        Point p;
        int length;
        Node leftChild;
        Node rightChild;

        Node(Point p) {
            this.p = p;
            this.length = 0;
        }

        private void add(Node x) {
            x.length = length + 1;
            if (length % 2 == 0) {
                if (Double.compare(p.getX(), x.p.getX()) < 0) {
                    if (rightChild == null) {
                        rightChild = x;
                    }else {
                        rightChild.add(x);
                    }
                }else {
                    if (leftChild == null) {
                        leftChild = x;
                    }else {
                        leftChild.add(x);
                    }
                }
            }else {
                if (Double.compare(p.getY(), x.p.getY()) < 0) {
                    if (rightChild == null) {
                        rightChild = x;
                    }else {
                        rightChild.add(x);
                    }
                }else {
                    if (leftChild == null) {
                        leftChild = x;
                    }else {
                        leftChild.add(x);
                    }
                }
            }
        }


        /**
         Lambda
        Comparator<Node> xComparator = (Node m, Node n) -> Double.compare(m.p.getX(), n.p.getX());
        Comparator<Node> yComparator = (Node m, Node n) -> Double.compare(m.p.getY(), n.p.getY());
        */
    }
    public static void main(String[] args) {
        Point p1 = new Point(1.1, 2.2); // constructs a Point with x = 1.1, y = 2.2
        Point p2 = new Point(3.3, 4.4);
        Point p3 = new Point(-2.9, 4.2);

        KDTree nn = new KDTree(List.of(p1, p2, p3));
        Point ret = nn.nearest(3.0, 4.0); // returns p2
        double x = ret.getX(); // evaluates to 3.3
        double y = ret.getY();// evaluates to 4.4
        System.out.println(x);
        System.out.println(y);
    }
}
