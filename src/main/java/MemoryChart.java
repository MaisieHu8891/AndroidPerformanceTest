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

public class MemoryChart implements Runnable  {
    private Boolean runStatus;
    private String testTitle;
    private String chartImgFilePath;
    private ChartViewPanel memoryPanel;
    private static String[] memCsvHeader = {"DateTime", "Memory"};
    private static WriteLogFiles memCsvFile = new WriteLogFiles(".\\out\\log\\Memory"+System.currentTimeMillis()+".csv",memCsvHeader);
    private static CSVPrinter memPrinter = memCsvFile.initPrinter();

    public Boolean getRunStatus() {
        return runStatus;
    }

    public void setRunStatus(Boolean runStatus) {
        this.runStatus = runStatus;
    }

    public MemoryChart(String testTitle,String chartImgFilePath) {
        this.testTitle = testTitle;
        this.chartImgFilePath = chartImgFilePath;
    }

    public void setMemoryPanel(ChartViewPanel memoryPanel) {
        this.memoryPanel = memoryPanel;
        JFrame jFrame = new JFrame(testTitle);
        jFrame.getContentPane().add(memoryPanel, new BorderLayout().CENTER);
        jFrame.pack();
        jFrame.setVisible(true);
        jFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowevent) {
                try {
                    ChartUtils.saveChartAsJPEG(new File(chartImgFilePath), memoryPanel.getChart(), 700, 500);
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
                String[] memInfo = new GetPerforData().GetMEM();
                memCsvFile.doWrite(memPrinter,memInfo);
                Integer memnum = Integer.parseInt(memInfo[1]);
                memoryPanel.timeSeries.add(new Millisecond(), memnum);
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("记录内存信息出错");
            }

        }
    }


}