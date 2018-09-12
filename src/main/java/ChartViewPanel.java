import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;


public class ChartViewPanel extends ChartPanel {
    static TimeSeries timeSeries;
    static TimeSeries timeSeriesrx;
    static TimeSeries timeSeriestx;
    static Double timeInterval = 3600000D;

    public ChartViewPanel(String chartContent, String title, String yaxisName){
        super(createOneDataChart(chartContent, title, yaxisName));
    }
    public ChartViewPanel(String chartContentrx, String chartContenttx, String title, String yaxisName){
        super(createTwoDataChart(chartContentrx,chartContenttx, title, yaxisName));
    }

    public static JFreeChart createOneDataChart(String chartContent, String title, String yaxisName) {
        timeSeries = new TimeSeries(chartContent);//, Millisecond.class
        TimeSeriesCollection timeseriescollection = new TimeSeriesCollection(timeSeries);
        JFreeChart jfreechart = ChartFactory.createTimeSeriesChart(title, "Time", yaxisName, timeseriescollection, true, true, false);
        XYPlot xyplot = jfreechart.getXYPlot();
//纵坐标设定
        ValueAxis valueaxis = xyplot.getDomainAxis();
//自动设置数据轴数据范围
        valueaxis.setAutoRange(true);
//数据轴固定数据范围 1hour
        valueaxis.setFixedAutoRange(timeInterval);
        //xyplot.getRangeAxis().setRange(0.0D,100D);
        return jfreechart;
    }

    public static JFreeChart createTwoDataChart(String chartContentrx,String chartContenttx, String title, String yaxisName) {
        timeSeriesrx = new TimeSeries(chartContentrx);//, Millisecond.class
        timeSeriestx = new TimeSeries(chartContenttx);//, Millisecond.class
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(timeSeriesrx);
        dataset.addSeries(timeSeriestx);
        JFreeChart jfreechart = ChartFactory.createTimeSeriesChart(title, "Time", yaxisName, dataset, true, true, false);
        XYPlot xyplot = jfreechart.getXYPlot();
        ValueAxis valueaxis = xyplot.getDomainAxis();
        valueaxis.setAutoRange(true);
        valueaxis.setFixedAutoRange(timeInterval);
        return jfreechart;
    }

}
