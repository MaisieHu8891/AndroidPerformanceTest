import org.apache.commons.csv.CSVPrinter;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import utilclass.WriteLogFiles;
import javax.swing.*;

public class CpuChart implements Runnable  {
    private int ver;
    private String pid;
    private String testTitle;
    private ChartViewJFrame cpuViewJFrame;
    private TimeSeries timeSeries;
    private static String[] cpuCsvHeader = {"DateTime", "CPU"};
    private static WriteLogFiles cpuCsvFile = new WriteLogFiles(".\\out\\log\\CPU"+System.currentTimeMillis()+".csv",cpuCsvHeader);
    private static CSVPrinter cpuPrinter = cpuCsvFile.initPrinter();

    public CpuChart(int ver, String pid, String testTitle) {
        this.ver = ver;
        this.pid = pid;
        this.testTitle = testTitle;
        ChartViewJFreeChart cpuViewJFreeChart = new ChartViewJFreeChart();
        cpuViewJFreeChart.setTimeInterval(3600000D);
        JFreeChart cpuJFreeChart = cpuViewJFreeChart.createOneDataChart("CPU", "com.panda.videoliveplatform","CPU");
        this.timeSeries = cpuViewJFreeChart.getTimeSeries();
        this.cpuViewJFrame = new ChartViewJFrame(new ChartPanel(cpuJFreeChart), ".\\out\\log\\CPU"+System.currentTimeMillis()+".jpg",true);
        this.cpuViewJFrame.setjFrame(new JFrame(this.testTitle));
    }

    public Boolean getRunStatus() {
        return this.cpuViewJFrame.getRunStatus();
    }

    @Override
    public void run() {
        while (this.getRunStatus()){
            try {
                String[] cpuInfo = new GetPerforData().GetCPU(ver,pid);
                cpuCsvFile.doWrite(cpuPrinter,cpuInfo);
                Double cpunum = Double.parseDouble(cpuInfo[1]);
                timeSeries.add(new Millisecond(), cpunum);
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("记录CPU信息出错");
            }
        }
    }

}