package ml.bimdev.chan;

import java.util.List;

class Util {
    static int compare(double a, double b) {
        if (a > b)
            return 1;
        else if (a < b)
            return -1;
        return 0;
    }

    static double dist(Point p, Point q) {
        double dx = q.x - p.x;
        double dy = q.y - p.y;
        return ((dx * dx) + (dy * dy));
    }

    static int orientation(Point p, Point q, Point r) {
        return compare(((q.x - p.x) * (r.y - p.y)) - ((q.y - p.y) * (r.x - p.x)), 0);
    }

    static Point findExtreme(List<Point> points) {
        Point p = new Point(points.get(0));
        for (int i = 1; i < points.size(); i++) {
            int x = points.get(i).x;
            int y = points.get(i).y;
            if (x < p.x || (x == p.x && y < p.y)) {
                p.x = x;
                p.y = y;
            }
        }
        return p;
    }

}
