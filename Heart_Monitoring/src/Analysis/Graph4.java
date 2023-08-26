package Analysis;


import Algorithms.chk1;
import static Master.Dbconn.pid;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.io.IOException;
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
public class Graph4 extends ApplicationFrame {

    public static double F1 = 0, F2 = 0, F3 = 0, F4 = 0, F5 = 0, F6 = 0, F7 = 0, F8 = 0, F9 = 0, F10 = 0;
public static double Rpre1 = 0;
    public static double Rpre2 = 0;
    public static double Rpre3 = 0;
    public static double Rpre4 = 0;
    public Graph4(final String title) throws IOException {
        super(title);

        final CategoryDataset dataset = createDataset();
        final JFreeChart chart = createChart(dataset);
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 470));
        setContentPane(chartPanel);

    }

    private CategoryDataset createDataset() throws IOException {

          chk1 c = new chk1();
       // c.br();
        final String series1 = "Accuracy";
        final String series2 = "Error Rate";
       

        final String mtd1 = series1;
        final String mtd2 = series2;
     
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        double f=Graph2.fpre1;
        double r=Graph1.rfpre1;
        double q=Graph.pre1;
        double acc=(f+r+q)/3;
        double pre2=1.0-acc;
        dataset.addValue(acc, series1, mtd1);
        dataset.addValue(pre2, series2, mtd2);
       

        return dataset;

    }

    private JFreeChart createChart(final CategoryDataset dataset) {

        final JFreeChart chart = ChartFactory.createBarChart(
                "Accuracy ",
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
