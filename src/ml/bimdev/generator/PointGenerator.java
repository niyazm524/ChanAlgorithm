package ml.bimdev.generator;

import ml.bimdev.chan.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PointGenerator {
    private static Random rnd = new Random();
    public static List<Point> generate(int count) {
        ArrayList<Point> array = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            array.add(new Point(rnd.nextInt(10000)-2000, rnd.nextInt(10000)-2000));
        }
        return array;
    }

    public static void addGeneratedToList(List<Point> list, int count) {
        for (int i = 0; i < count; i++) {
            list.add(new Point(rnd.nextInt(1000000)-500000, rnd.nextInt(1000000)-500000));
        }
    }
}
