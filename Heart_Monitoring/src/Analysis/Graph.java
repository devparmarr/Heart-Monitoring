package Analysis;

import Algorithms.chk1;
import Master.Dbconn;
import static Master.Dbconn.pid;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;

/**
 *
 * @author Sunsoft
 */
public class Graph extends ApplicationFrame {

    public static double pre1 = 0;
    public static double pre2 = 0;
    public static double pre3 = 0;
    public static double pre4 = 0;

    public Graph(final String title) throws IOException, Exception {
        super(title);

        final CategoryDataset dataset = createDataset();
        final JFreeChart chart = createChart(dataset);
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 470));
        setContentPane(chartPanel);

    }

    private CategoryDataset createDataset() throws IOException, SQLException, Exception {

        // final String series1 = "False Rate";
        final String series2 = "QLearning";
        //final String series3 = "F-Measure";
        chk1 c = new chk1();
      //  c.br();
        final String mtd1 = "Accuracy";
        final String mtd2 = "QLearning Precision";
        final String mtd3 = "QLearning Recall";
        final String mtd4 = "F1 Score";

        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        dataset.addValue(pre1, series2, mtd1);
        dataset.addValue(pre2, series2, mtd2);
        dataset.addValue(pre3, series2, mtd3);
        dataset.addValue(pre4, series2, mtd4);

        return dataset;

    }

    private JFreeChart createChart(final CategoryDataset dataset) {

        final JFreeChart chart = ChartFactory.createBarChart(
                "Accuracy of Q-Learning",
                "Methods",
                "Rate in %",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        chart.setBackgroundPaint(Color.white);

        final CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);

        //final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        //rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        // disable bar outlines...
        final BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(false);

        final GradientPaint gp0 = new GradientPaint(
                0.0f, 0.0f, Color.red,
                0.0f, 0.0f, Color.red
        );
        final GradientPaint gp1 = new GradientPaint(
                0.0f, 0.0f, Color.green,
                0.0f, 0.0f, Color.green
        );
        final GradientPaint gp2 = new GradientPaint(
                0.0f, 0.0f, Color.red,
                0.0f, 0.0f, Color.red
        );

        renderer.setSeriesPaint(0, gp0);
        renderer.setSeriesPaint(1, gp1);
        renderer.setSeriesPaint(2, gp2);

        final CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(
                CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 6.0)
        );
        return chart;
    }

    public static void main(final String[] args) {

    }

}
