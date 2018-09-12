import org.apache.commons.csv.CSVPrinter;
import org.jfree.chart.ChartUtils;
import org.jfree.data.time.Millisecond;
import utilclass.WriteLogFiles;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;


class CpuChart implements Runnable  {
    private int ver;
    private String pid;
    private Boolean runStatus;
    private String testTitle;
    private String chartImgFilePath;
    private ChartViewPanel cpuPanel;

    private static String[] cpuCsvHeader = {"DateTime", "CPU"};
    private static WriteLogFiles cpuCsvFile = new WriteLogFiles(".\\out\\log\\CPU"+System.currentTimeMillis()+".csv",cpuCsvHeader);
    private static CSVPrinter cpuPrinter = cpuCsvFile.initPrinter();

    public Boolean getRunStatus() {
        return runStatus;
    }

    public void setRunStatus(Boolean runStatus) {
        this.runStatus = runStatus;
    }

    public CpuChart(int ver, String pid, Boolean runStatus, String testTitle,String chartImgFilePath) {
        this.ver = ver;
        this.pid = pid;
        this.runStatus = runStatus;
        this.testTitle = testTitle;
        this.chartImgFilePath = chartImgFilePath;
    }

    public void setCpuPanel(ChartViewPanel cpuPanel) {
        this.cpuPanel = cpuPanel;
        JFrame jFrame = new JFrame(testTitle);
        jFrame.getContentPane().add(cpuPanel, new BorderLayout().CENTER);
        jFrame.pack();
        jFrame.setVisible(true);
        jFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowevent) {
                try {
                    ChartUtils.saveChartAsJPEG(new File(chartImgFilePath), cpuPanel.getChart(), 700, 500);
                    runStatus = !runStatus;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void run() {
        while (this.getRunStatus()){
            try {
                String[] cpuInfo = new GetPerforData().GetCPU(ver,pid);
                cpuCsvFile.doWrite(cpuPrinter,cpuInfo);
                Double cpunum = Double.parseDouble(cpuInfo[1]);
                cpuPanel.timeSeries.add(new Millisecond(), cpunum);
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("记录CPU信息出错");
            }

        }
    }
}