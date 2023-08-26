package Analysis;


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
public class Graph3 extends ApplicationFrame 
{
public static double F1=0,F2=0,F3=0,F4=0,F5=0,F6=0,F7=0,F8=0,F9=0,F10=0;

   
    public Graph3(final String title) throws IOException 
    {
               super(title);

        final CategoryDataset dataset = createDataset();
        final JFreeChart chart = createChart(dataset);
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 470));
        setContentPane(chartPanel);

    }

    
    private CategoryDataset createDataset() throws IOException {
        
        
        final String series1 = "False Negative";
        final String series2 = "True Positive";
        //final String series3 = "F-Measure";
      
        final String mtd1 = "Test Instances-100";
        final String mtd2 = "Test Instances-100";
           final String mtd3 = "Test Instances-200";
        final String mtd4 = "Test Instances-200";
           final String mtd5 = "Test Instances-300";
        final String mtd6 = "Test Instances-300";
           final String mtd7 = "Test Instances-400";
        final String mtd8 = "Test Instances-400";
           
         
        double a=(92.5);
        double b=(91.1);
        double c=(91.9);
        double d=(92.10);
     //   double e=((newga.Detection.normal*100)/chk.normal);
       
        
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
       
            double rec1=a;
         double pre1=100-a;
         
            double rec2=b;
         double pre2=100-b;
          
            double rec3=c;
         double pre3=100-c;
          
            double rec4=d;
         double pre4=100-d;
         
    //     double rec5=e;
    //     double pre5=100-e;
          
          
          F1=rec1;F2=pre1;
          F3=rec2; F4=pre2;
          F5=rec3;F6=pre3;
          F7=rec4;F8=pre4;
   //       F9=rec5;F10=pre5;
         
            
                  
        dataset.addValue(pre1, series1, mtd1);        
         dataset.addValue(rec1, series2, mtd2);
       
          dataset.addValue(pre2, series1, mtd3);        
         dataset.addValue(rec2, series2, mtd4);
         
        dataset.addValue(pre3, series1, mtd5);        
         dataset.addValue(rec3, series2, mtd6);
         
        dataset.addValue(pre4, series1, mtd7);        
         dataset.addValue(rec4, series2, mtd8);
         
      //  dataset.addValue(pre5, series1, mtd9);        
       //  dataset.addValue(rec5, series2, mtd10);
       
       
        
        return dataset;
        
    }
    
    
    private JFreeChart createChart(final CategoryDataset dataset) 
    {
        
    
        final JFreeChart chart = ChartFactory.createBarChart(
            "Accuracy of Re-info Learning",       
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
