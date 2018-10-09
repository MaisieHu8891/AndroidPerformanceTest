import org.apache.commons.csv.CSVPrinter;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import utilclass.AppConfig;
import utilclass.WriteLogFiles;
import javax.swing.*;

public class MemoryChart implements Runnable {

    private String testTitle;
    private ChartViewJFrame memViewJFrame;
    private TimeSeries timeSeries;
    private GetPerforData getPerforData;
    private static String[] memCsvHeader = {"DateTime", "Memory"};
    private static WriteLogFiles memCsvFile = new WriteLogFiles(".\\out\\log\\Memory"+System.currentTimeMillis()+".csv",memCsvHeader);
    private static CSVPrinter memPrinter = memCsvFile.initPrinter();

    public MemoryChart( String testTitle) {
        AppConfig appConfig = new AppConfig();
        appConfig.setMap();
        String app = appConfig.getAppName();
        this.testTitle = testTitle;
        ChartViewJFreeChart memViewJFreeChart = new ChartViewJFreeChart();
        memViewJFreeChart.setTimeInterval(3600000D);
        JFreeChart memJFreeChart = memViewJFreeChart.createOneDataChart("Memory", app,"Memory");
        this.timeSeries = memViewJFreeChart.getTimeSeries();
        this.memViewJFrame = new ChartViewJFrame(new ChartPanel(memJFreeChart), ".\\out\\log\\Memory"+System.currentTimeMillis()+".jpg",true);
        this.memViewJFrame.setjFrame(new JFrame(this.testTitle));
        this.getPerforData = new GetPerforData();
    }

    public Boolean getRunStatus() {
        return this.memViewJFrame.getRunStatus();
    }

    @Override
    public void run() {
        while (this.getRunStatus()){
            try {
                String[] memInfo =getPerforData.GetMEM();
                memCsvFile.doWrite(memPrinter,memInfo);
                Integer memNum = Integer.parseInt(memInfo[1]);
                timeSeries.add(new Millisecond(), memNum);
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("记录内存信息出错");
            }
        }
    }
}