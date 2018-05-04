import org.apache.commons.csv.CSVPrinter;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import java.io.IOException;

class CpuTimeChart extends ChartPanel {
    static TimeSeries timeSeries;
    static Double timeInterval = 3600000D;
    String[] cpuheader = {"DateTime", "CPU"};
    WriteLogFiles cpufile = new WriteLogFiles(".\\out\\log\\cpu"+System.currentTimeMillis()+".csv", cpuheader);
    CSVPrinter cpuprinter = cpufile.initPrinter();
    GetUniversalInfo GU = new GetUniversalInfo();
    int ver = GU.GetAndroidVersion(); //增加支持android8.0系统的cpu获取

    public CpuTimeChart(String chartContent, String title, String yaxisName) throws Exception {
        super(createChart(chartContent, title, yaxisName));
    }

    public static JFreeChart createChart(String chartContent, String title, String yaxisName) {
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

    public Double CpuNum() throws Exception {
        GetCPU getcpudata = new GetCPU();
        String[] cpuresult = getcpudata.data(ver); //换成获取CPU信息的函数
        cpufile.doWrite(cpuprinter, cpuresult);
        return Double.parseDouble(cpuresult[1]);
    }


}

class MemTimeChart extends ChartPanel {

    static TimeSeries timeSeries;
    static Double timeInterval = 3600000D;
    String[] memheader ={"DateTime","Memery"};
    WriteLogFiles memfile = new WriteLogFiles(".\\out\\log\\mem"+System.currentTimeMillis()+".csv",memheader);
    CSVPrinter memprinter = memfile.initPrinter();

    public MemTimeChart(String chartContent, String title, String yaxisName) throws IOException {
        super(createChart(chartContent, title, yaxisName));
    }

    public static JFreeChart createChart(String chartContent, String title, String yaxisName) {
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

    public Integer MemNum() throws IOException {
        GetMEM getmemdata = new GetMEM("adb shell dumpsys meminfo com.panda.videoliveplatform|grep TOTAL");
        String[] memresult = getmemdata.data(); //换成获取memery信息的函数
        memfile.doWrite(memprinter,memresult);
        return Integer.parseInt(memresult[1]);
    };

}

public class TimeChartAndWriteCsv{}