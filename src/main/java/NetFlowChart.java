import org.apache.commons.csv.CSVPrinter;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import utilclass.WriteLogFiles;
import javax.swing.*;

public class NetFlowChart implements Runnable{
    private String userId;
    private String testTitle;
    private ChartViewJFrame netViewJFrame;
    private TimeSeries timeSeriesrx;
    private TimeSeries timeSeriestx;
    private static String[] netCsvHeader = {"DateTime", "NetFlow_rxbytes", "NetFlow_txbytes"};
    private static WriteLogFiles netCsvFile = new WriteLogFiles(".\\out\\log\\NetFlow"+System.currentTimeMillis()+".csv",netCsvHeader);
    private static CSVPrinter netPrinter = netCsvFile.initPrinter();

    public NetFlowChart( String userId,String testTitle) {
        this.userId = userId;
        this.testTitle = testTitle;
        ChartViewJFreeChart netViewJFreeChart = new ChartViewJFreeChart();
        netViewJFreeChart.setTimeInterval(3600000D);
        JFreeChart netJFreeChart = netViewJFreeChart.createTwoDataChart("NetFlow_rxbytes", "NetFlow_txbytes","com.panda.videoliveplatform","NetFlow");
        this.timeSeriesrx = netViewJFreeChart.getTimeSeriesrx();
        this.timeSeriestx = netViewJFreeChart.getTimeSeriestx();
        this.netViewJFrame = new ChartViewJFrame(new ChartPanel(netJFreeChart), ".\\out\\log\\NetFlow"+System.currentTimeMillis()+".jpg",true);
        this.netViewJFrame.setjFrame(new JFrame(this.testTitle));
    }

    public Boolean getRunStatus() {
        return this.netViewJFrame.getRunStatus();
    }

    @Override
    public void run() {
        while (this.getRunStatus()){
            try {
                String[] netInfo = new GetPerforData().GetNetWorkFlow(userId);
                netCsvFile.doWrite(netPrinter,netInfo);
                Integer netRxbytes = Integer.parseInt(netInfo[1]);
                Integer netTxbytes = Integer.parseInt(netInfo[2]);
                timeSeriesrx.add(new Millisecond(), netRxbytes);
                timeSeriestx.add(new Millisecond(), netTxbytes);
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("记录内存信息出错");
            }
        }
    }
}
