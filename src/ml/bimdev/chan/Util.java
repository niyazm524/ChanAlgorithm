package ml.bimdev.chan;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Util {

    static double dist(Point p, Point q) {
        double dx = q.x - p.x;
        double dy = q.y - p.y;
        return ((dx * dx) + (dy * dy));
    }

    static int orientation(Point p, Point q, Point r) {
        return Integer.compare(((q.x - p.x) * (r.y - p.y)) - ((q.y - p.y) * (r.x - p.x)), 0);
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

    public static List<Point> deserializePoints(String filename) {
        try(Stream<String> stream = Files.lines(Paths.get(filename))) {
            return stream
                    .map(line -> line.split(" "))
                    .map(items -> new Point(Integer.parseInt(items[0]), Integer.parseInt(items[1])))
                    .collect(Collectors.toList());
        } catch (FileNotFoundException ex) {
            System.err.println(
                    "Please check the location of File '" + filename + "'. It is not there where you are expecting it to be!");
        } catch (IOException ex) {
            System.err.println("Error reading file '" + filename + "'");
        }
        return null;
    }

    public static void serializePoints(List<Point> list, String filename) {
        try(PrintWriter writer = new PrintWriter(new File(filename))) {
            list.forEach(point -> writer.println(point.toString()));
        } catch (FileNotFoundException e) {
            System.err.println(
                    "Please check the location of File '" + filename + "'. It is not there where you are expecting it to be!");
        }
    }

}
