import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

public class ChartViewJFreeChart {
    private TimeSeries timeSeries;
    private TimeSeries timeSeriesrx;
    private TimeSeries timeSeriestx;
    private Double timeInterval ;

    public TimeSeries getTimeSeries() {
        return timeSeries;
    }

    public TimeSeries getTimeSeriesrx() {
        return timeSeriesrx;
    }

    public TimeSeries getTimeSeriestx() {
        return timeSeriestx;
    }

    public void setTimeInterval(Double timeInterval) {
        this.timeInterval = timeInterval;
    }

    public JFreeChart createOneDataChart(String chartContent, String title, String yaxisName) {
        this.timeSeries = new TimeSeries(chartContent);//, Millisecond.class
        TimeSeriesCollection timeseriescollection = new TimeSeriesCollection(this.timeSeries);
        JFreeChart jfreechart = ChartFactory.createTimeSeriesChart(title, "Time", yaxisName, timeseriescollection, true, true, false);
        XYPlot xyplot = jfreechart.getXYPlot();
//纵坐标设定
        ValueAxis valueaxis = xyplot.getDomainAxis();
//自动设置数据轴数据范围
        valueaxis.setAutoRange(true);
//数据轴固定数据范围 1hour
        valueaxis.setFixedAutoRange(this.timeInterval);
        //xyplot.getRangeAxis().setRange(0.0D,100D);
        return jfreechart;
    }

    public  JFreeChart createTwoDataChart( String chartContentrx,String chartContenttx, String title, String yaxisName) {
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
