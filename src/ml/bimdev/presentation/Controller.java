package ml.bimdev.presentation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;


public class Controller {
    ObservableList<XYChart.Data> datas;
    ObservableList<XYChart.Data> datas2;
    StartBenchThread thread = null;

    @FXML
    Button startBenchBtn;
    @FXML
    LineChart<Number, Number> chart;
    @FXML
    NumberAxis timeAxis;
    @FXML
    NumberAxis nAxis;


    @FXML
    void initialize() {

       // LineChart<Number, Number> numberLineChart = new LineChart<>(x,y);
        chart.setTitle("График сложности алгоритма Чана");

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("t(n)");
        XYChart.Series series2 = new XYChart.Series();
        series2.setName("h(n)");
        datas = FXCollections.observableArrayList();
        datas2 = FXCollections.observableArrayList();

        series1.setData(datas);
        series2.setData(datas2);
        chart.getData().add(series1);
        chart.getData().add(series2);

    }

    @FXML
    void onStartBenchClick() {
        if(thread == null) {
            thread = new StartBenchThread(new Transfer());
            thread.start();
            startBenchBtn.setText("Stop Benchmarking");
        } else {
            thread.running = false;
            thread.stop();
            thread = null;
            startBenchBtn.setText("Start Benchmarking");

        }
    }

    private class Transfer implements DataTransfer {
        @Override
        public void add(int n, double time, int h) {
            datas.add(new XYChart.Data(n, time));
            datas2.add(new XYChart.Data(n, h));
        }
    }
}
