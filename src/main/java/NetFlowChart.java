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

public class NetFlowChart implements Runnable{

    private String userId;
    private Boolean runStatus;
    private String testTitle;
    private String chartImgFilePath;
    private ChartViewPanel netFlowPanel;
    private static String[] netFlowCsvHeader = {"DateTime", "NetWorkFlow_rxbytes","NetWorkFlow_txbytes"};
    private static WriteLogFiles netFlowCsvFile = new WriteLogFiles(".\\out\\log\\NetFlow"+System.currentTimeMillis()+".csv",netFlowCsvHeader);
    private static CSVPrinter netFlowPrinter = netFlowCsvFile.initPrinter();

    public Boolean getRunStatus() {
        return runStatus;
    }

    public void setRunStatus(Boolean runStatus) {
        this.runStatus = runStatus;
    }

    public NetFlowChart(String userId,String testTitle,String chartImgFilePath) {
        this.userId = userId;
        this.testTitle = testTitle;
        this.chartImgFilePath = chartImgFilePath;
    }

    public void setNetFlowPanel(ChartViewPanel netFlowPanel) {
        this.netFlowPanel = netFlowPanel;
        JFrame jFrame = new JFrame(testTitle);
        jFrame.getContentPane().add(netFlowPanel, new BorderLayout().CENTER);
        jFrame.pack();
        jFrame.setVisible(true);
        jFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowevent) {
                try {
                    ChartUtils.saveChartAsJPEG(new File(chartImgFilePath), netFlowPanel.getChart(), 700, 500);
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
                String[] netFlowInfo = new GetPerforData().GetNetWorkFlow(userId);
                netFlowCsvFile.doWrite(netFlowPrinter,netFlowInfo);
                Integer netFlownumrx = Integer.parseInt(netFlowInfo[1]);
                Integer netFlownumtx = Integer.parseInt(netFlowInfo[2]);
                netFlowPanel.timeSeriesrx.add(new Millisecond(), netFlownumrx);
                netFlowPanel.timeSeriestx.add(new Millisecond(), netFlownumtx);
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("记录内存信息出错");
            }

        }
    }

}
