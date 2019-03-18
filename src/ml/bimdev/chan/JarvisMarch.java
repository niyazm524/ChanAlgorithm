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
            Point r = new Point();
            for (Point point : points) {
                if ((point.x == p.x) && (point.y == p.y)) {
                    continue;
                }
                r.x = point.x;
                r.y = point.y;
                int turn = Util.orientation(p, q, r);
                double dist = Util.compare(Util.dist(p, r), Util.dist(p, q));
                if (turn == -1 || turn == 0 && dist == 1) {
                    q.x = r.x;
                    q.y = r.y;
                }
            }
            if ((q.x == result.get(0).x) && (q.y == result.get(0).y)) {
                break;
            }
            result.add(new Point(q));
            p.x = q.x;
            p.y = q.y;
        }
        return result;
    }
}