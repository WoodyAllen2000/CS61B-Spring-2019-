package bearmaps;

import java.util.*;

public class NaivePointSet implements PointSet {

    public List<Point> points;

    public NaivePointSet(List<Point> points) {
        this.points = points;
    }

    // find the nearest point
    @Override
    public Point nearest(double x, double y) {
        Point input = new Point(x, y);
        Point ans = points.get(0);
        double dist = Point.distance(ans, input);
        for (Point point : points) {
            if (Point.distance(point, input) < dist) {
                ans = point;
                dist = Point.distance(ans, input);
            }
        }
        return ans;
    }
}
