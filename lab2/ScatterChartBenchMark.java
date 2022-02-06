/*
 *  Course: CS1011-051
 *  Fall 2020-2021
 *  File header contains class ScatterChart
 *  Name: paganinik
 *  Created 3/14/2021
 */
package paganiniK;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

/**
 * Draws the Scatter chart - Benchmark
 */
public class ScatterChartBenchMark extends Application {

    @Override
    public void start(Stage stage) {
        stage.setTitle("Benchmark: ArrayList vs. LinkedList");
        final NumberAxis xAxis = new NumberAxis(0, 450000, 80000);
        final NumberAxis yAxis = new NumberAxis(0, 40000, 100);
        final ScatterChart<Number, Number> sc = new
                ScatterChart<Number, Number>(xAxis, yAxis);

        xAxis.setLabel("Number of Elements");
        yAxis.setLabel("Time in Milliseconds");
        sc.setTitle("Benchmark: Get From Middle");

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("ArrayList");
        series1.getData().add(new XYChart.Data(20000, 5));

        series1.getData().add(new XYChart.Data(40000, 3));

        series1.getData().add(new XYChart.Data(200000, 1));

        series1.getData().add(new XYChart.Data(400000, 0.97));


        XYChart.Series series2 = new XYChart.Series();
        series2.setName("LinkedList");
        series2.getData().add(new XYChart.Data(20000, 1500));
        series2.getData().add(new XYChart.Data(40000, 3000));
        series2.getData().add(new XYChart.Data(200000, 14974));
        series2.getData().add(new XYChart.Data(400000, 36925));


        sc.getData().addAll(series1, series2);

        final NumberAxis xAxis2 = new NumberAxis(0, 450000, 80000);
        final NumberAxis yAxis2 = new NumberAxis(0, 10000, 100);
        final ScatterChart<Number, Number> sc2 = new
                ScatterChart<Number, Number>(xAxis2, yAxis2);

        xAxis2.setLabel("Number of Elements");
        yAxis2.setLabel("Time in Milliseconds");
        sc2.setTitle("Benchmark: Remove From Front");

        XYChart.Series series3 = new XYChart.Series();
        series3.setName("ArrayList");
        series3.getData().add(new XYChart.Data(20000, 20));

        series3.getData().add(new XYChart.Data(40000, 70));

        series3.getData().add(new XYChart.Data(200000, 2030));

        series3.getData().add(new XYChart.Data(400000, 8156));


        XYChart.Series series4 = new XYChart.Series();
        series4.setName("LinkedList");
        series4.getData().add(new XYChart.Data(20000, 2));
        series4.getData().add(new XYChart.Data(40000, 2));
        series4.getData().add(new XYChart.Data(200000, 3.5));
        series4.getData().add(new XYChart.Data(400000, 6));


        sc2.getData().addAll(series3, series4);





        FlowPane pane = new FlowPane(sc, sc2);

        Scene scene = new Scene(pane, 700, 600);


        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}