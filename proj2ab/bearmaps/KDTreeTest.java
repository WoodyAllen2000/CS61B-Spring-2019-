package bearmaps;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class KDTreeTest {
    @Test
    public void test() {
        Point goal = new Point(Math.random()*100, Math.random()*100);
        List<Point> points = new LinkedList<>();
        for (int i = 0; i < 10000; i++) {
            double x = Math.random()*100;
            double y = Math.random()*100;
            points.add(new Point(x, y));
        }
        NaivePointSet NSet = new NaivePointSet(points);
        KDTree KDTree = new KDTree(points);
        assertEquals(NSet.nearest(goal.getX(), goal.getY()), KDTree.nearest(goal.getX(), goal.getY()));
    }
}
