import org.knowm.xchart.XYChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChartBuilder;

import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class Experiments {
    public static void experiment1() {
        Random r = new Random();
        BST<Integer> bst = new BST<>();
        List<Integer> xValues = new ArrayList<>();
        List<Double> y1Values = new ArrayList<>();
        List<Double> y2Values = new ArrayList<>();
        while (bst.size() < 5000) {
            int x = r.nextInt();
            if (bst.contains(x)) {
                continue;
            }
            bst.add(x);
            xValues.add(bst.size());
            y1Values.add(bst.averageDepth());
            y2Values.add(ExperimentHelper.optimalAverageDepth(bst.size()));
        }
        XYChart chart = new XYChartBuilder().width(800).height(600).xAxisTitle("x label").yAxisTitle("y label").build();
        chart.addSeries("myBST", xValues, y1Values);
        chart.addSeries("optBST", xValues, y2Values);
        new SwingWrapper(chart).displayChart();
    }

    public static void experiment2() {
        BST<Integer> bst = new BST<>();
        Random r = new Random();
        List<Double> avgDepthMy = new ArrayList<>();
        List<Integer> maxDepthMy = new ArrayList<>();
        List<Integer> xValues = new ArrayList<>();

        //a bst tree of 2000 items
        for (int i = 0; i <= 2000; i++) {
            int x = r.nextInt(5000);
            if (bst.contains(x)) {
                continue;
            }
            bst.add(x);
        }

        for (int i = 0; i <=1000000; i++) {
            double avgDepths = bst.averageDepth();
            int maxDepth = bst.Depth();
            ExperimentHelper.insertAndDeleteSuccessor(bst);
            xValues.add(i);
            avgDepthMy.add(avgDepths);
            maxDepthMy.add(maxDepth);
        }
        XYChart chart1 = new XYChartBuilder().width(800).height(600).xAxisTitle("x label").yAxisTitle("y label").build();
        XYChart chart2 = new XYChartBuilder().width(800).height(600).xAxisTitle("x label").yAxisTitle("y label").build();
        chart1.addSeries("avgDepth", xValues, avgDepthMy);
        chart2.addSeries("maxDepth", xValues, maxDepthMy);
        new SwingWrapper(chart1).displayChart();
        new SwingWrapper(chart2).displayChart();
    }

    public static void experiment3() {
        BST<Integer> bst = new BST<>();
        Random r = new Random();
        List<Double> avgDepthMy = new ArrayList<>();
        List<Integer> maxDepthMy = new ArrayList<>();
        List<Integer> xValues = new ArrayList<>();

        //a bst tree of 2000 items
        for (int i = 0; i <= 2000; i++) {
            int x = r.nextInt(5000);
            if (bst.contains(x)) {
                continue;
            }
            bst.add(x);
        }

        for (int i = 0; i <=1000000; i++) {
            double avgDepths = bst.averageDepth();
            int maxDepth = bst.Depth();
            ExperimentHelper.insertAndDeleteRandom(bst);
            xValues.add(i);
            avgDepthMy.add(avgDepths);
            maxDepthMy.add(maxDepth);
        }
        XYChart chart1 = new XYChartBuilder().width(800).height(600).xAxisTitle("x label").yAxisTitle("y label").build();
        XYChart chart2 = new XYChartBuilder().width(800).height(600).xAxisTitle("x label").yAxisTitle("y label").build();
        chart1.addSeries("avgDepth", xValues, avgDepthMy);
        chart2.addSeries("maxDepth", xValues, maxDepthMy);
        new SwingWrapper(chart1).displayChart();
        new SwingWrapper(chart2).displayChart();
    }

    public static void main(String[] args) {

        experiment2();

    }
}
