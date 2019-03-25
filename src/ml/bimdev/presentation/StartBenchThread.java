package ml.bimdev.presentation;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import ml.bimdev.Config;
import ml.bimdev.chan.Chan;
import ml.bimdev.chan.Point;
import ml.bimdev.generator.PointGenerator;

import java.util.ArrayList;
import java.util.List;

public class StartBenchThread extends Thread {
    DataTransfer transfer;
    boolean running = true;
    int n = 100;
    List<Point> input = new ArrayList<>();

    public StartBenchThread(DataTransfer transfer) {
        super();
        this.transfer = transfer;
    }

    @Override
    public void run() {
        while (!this.isInterrupted() && running) {
            input.addAll(PointGenerator.generate(Config.INCREMENT));
            long startTime = System.nanoTime();
            int h = Chan.convexHull(input).size();
            long stopTime = System.nanoTime();
            System.out.println("h: " + h);
            Platform.runLater(() -> {
                transfer.add(n, (stopTime - startTime) / 1000000, h);
            });
            n += Config.INCREMENT;
            try {
                sleep(20);
            } catch (InterruptedException e) {}
        }
    }
}
