package ml.bimdev.chan;

import ml.bimdev.Config;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {


    public static void main(String[] args) {
        List<Point> result;

        try(Stream<String> stream = Files.lines(Paths.get(Config.FILENAME))) {

            List<Point> points = stream
                    .map(line -> line.split(" "))
                    .map(items -> new Point(Integer.parseInt(items[0]), Integer.parseInt(items[1])))
                    .collect(Collectors.toList());

            if (points.size() < 3) {
                System.out.println("Number of points must be at least 3");
                return;
            }
            long startTime = System.nanoTime();
            result = Chan.convexHull(points);
            long endTime = System.nanoTime();
            printToFile(result, Config.OUTPUT_FILENAME);
            System.out.println("Time taken : " + (endTime - startTime) / 1000 + " micro seconds");
        } catch (FileNotFoundException ex) {
            System.out.println(
                    "Please check the location of File '" + Config.FILENAME + "'. It is not there where you are expecting it to be!");
        } catch (IOException ex) {
            System.out.println("Error reading file '" + Config.FILENAME + "'");
        }
    }

    private static void printToFile(List<Point> list, String filename) {
        try(PrintWriter writer = new PrintWriter(new File(filename))) {
            list.forEach(point -> writer.println(point.toString()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
