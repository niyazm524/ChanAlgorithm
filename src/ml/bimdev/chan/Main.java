package ml.bimdev.chan;

import ml.bimdev.Config;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Point> points = Util.deserializePoints(Config.FILENAME);
        if(points == null) {
            System.err.println("Error reading, abort operation");
            return;
        }

        if (points.size() < 3) {
            System.out.println("Number of points must be at least 3");
            return;
        }
        long startTime = System.nanoTime();
        List<Point> result = Chan.convexHull(points);
        long endTime = System.nanoTime();
        Util.serializePoints(result, Config.OUTPUT_FILENAME);
        System.out.println("Time taken : " + (endTime - startTime) / 1000 + " micro seconds");

    }
}
