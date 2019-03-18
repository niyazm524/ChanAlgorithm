package ml.bimdev.chan;

import java.util.ArrayList;
import java.util.List;


public class JarvisMarch {
    public static List<Point> convexHull(List<Point> points) {
        List<Point> result = new ArrayList<>();
        Point extreme = Util.findExtreme(points);
        result.add(new Point(extreme));
        Point p = new Point(extreme);
        Point q = new Point(extreme);

        while (true) {
            for (Point point : points) {
                if (point.equals(p)) {
                    continue;
                }
                int turn = Util.orientation(p, q, point);
                double dist = Double.compare(Util.dist(p, point), Util.dist(p, q));
                if (turn == -1 || turn == 0 && dist == 1) {
                    q = point;
                }
            }
            if (result.get(0).equals(q)) {
                break;
            }
            result.add(new Point(q));
            p.x = q.x;
            p.y = q.y;
        }
        return result;
    }
}