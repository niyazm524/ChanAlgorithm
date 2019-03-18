package ml.bimdev.generator;

import ml.bimdev.Config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;

class Main {

    private static final int NUM_OF_POINTS = 1000;

    public static void main(String[] args) {
        Random rnd = new Random();
        try(PrintWriter writer = new PrintWriter(new File(Config.FILENAME))) {
            for (int i = 0; i < NUM_OF_POINTS; i++) {
                writer.printf("%s %s\n", rnd.nextInt(10000)-2000, rnd.nextInt(10000)-2000);
            }
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
