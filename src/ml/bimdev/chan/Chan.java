package ml.bimdev.chan;

import java.util.ArrayList;
import java.util.List;

public class Chan {
    private static int power(int base, int exp) {
        int result = 1;
        for (int i = 0; i < exp; i++) {
            result = result * base;
        }
        return result;
    }

    private static List<Integer> findExtreme(List<List<Point>> points) {
        List<Integer> extreme_index = new ArrayList<>();
        Point p = new Point(points.get(0).get(0));
        int index = 0;
        for (int i = 1; i < points.size(); i++) {
            int x = points.get(i).get(0).x;
            int y = points.get(i).get(0).y;
            if (x < p.x || (x == p.x && y < p.y)) {
                p.x = x;
                p.y = y;
                index = i;
            }
        }
        extreme_index.add(index);
        extreme_index.add(0);
        return extreme_index;
    }

    private static int findTangent(List<Point> points, Point p) {
        int left = 0;
        int mid;
        int right = points.size();
        int prev_turn = 0;
        int next_turn;
        int mid_prev_turn;
        int mid_next_turn;
        int mid_side_turn;
        int sz = points.size();
        if (sz - 1 >= 0) {
            prev_turn = Util.orientation(p, points.get(0), points.get(sz - 1));
        }
        next_turn = Util.orientation(p, points.get(0), points.get((left + 1) % right));
        while (left < right) {
            mid = (left + right) / 2;
            if (((mid - 1) % sz) >= 0)
                mid_prev_turn = Util.orientation(p, points.get(mid), points.get((mid - 1) % sz));
            else
                mid_prev_turn = Util.orientation(p, points.get(mid), points.get(sz - 1));
            mid_next_turn = Util.orientation(p, points.get(mid), points.get((mid + 1) % sz));
            mid_side_turn = Util.orientation(p, points.get(left), points.get(mid));
            if (mid_prev_turn != -1 && mid_next_turn != -1) {
                return mid;
            } else if (mid_side_turn == 1 && (next_turn == -1 || prev_turn == next_turn) || mid_side_turn == -1 && mid_prev_turn == -1) {
                right = mid;
            } else {
                left = mid + 1;
                prev_turn = -mid_next_turn;
                if (left < sz)
                    next_turn = Util.orientation(p, points.get(left), points.get((left + 1) % sz));
                else
                    return -1;
            }
        }
        return left;
    }

    private static List<Integer> nextPoint(List<List<Point>> points, List<Integer> list) {
        Point p = new Point(points.get(list.get(0)).get(list.get(1)));
        List<Integer> next = new ArrayList<>();
        next.add(list.get(0));
        int temp = ((list.get(1) + 1) % points.get(list.get(0)).size());
        int j;
        Point q;
        Point r;
        next.add(temp);
        for (int i = 0; i < points.size(); i++) {
            if (i != list.get(0)) {
                j = findTangent(points.get(i), p);
                if (j == -1)
                    continue;
                q = new Point(points.get(next.get(0)).get(next.get(1)));
                r = new Point(points.get(i).get(j));
                int turn = Util.orientation(p, q, r);
                double dist = Double.compare(Util.dist(p, r), Util.dist(p, q));
                if (turn == -1 || turn == 0 && dist == 1) {
                    next.set(0, i);
                    next.set(1, j);
                }
            }
        }
        return next;
    }

    public static List<Point> convexHull(List<Point> points) {
        List<Point> sub;
        int m = 1, t = 0;
        List<Point> result = new ArrayList<>();
        List<Point> res;
        List<List<Point>> input = new ArrayList<>();
        while ((points.size() / m) > m) {
            t++;
            m = power(2, power(2, t));
        }
        int noOfGroups = (points.size()) / m;
        if (((points.size()) % m) != 0)
            noOfGroups += 1;
        for (; m < points.size(); ) {
            for (int i = 0, k = 0; i < noOfGroups; i++, k = k + m) {
                if (k <= points.size() - m) {
                    sub = points.subList(k, k + m);
                } else
                    sub = points.subList(k, points.size());
                res = JarvisMarch.convexHull(sub);
                input.add(res);
            }
            List<Integer> list;
            list = findExtreme(input);
            result.add(input.get(list.get(0)).get(list.get(1)));
            for (int x = 0; x < m; x++) {
                list = nextPoint(input, list);
                if (result.get(0) == input.get(list.get(0)).get(list.get(1))) {
                    return result;
                }
                result.add(input.get(list.get(0)).get(list.get(1)));
            }
            t++;
            m = power(2, power(2, t));
        }
        return result;
    }
}