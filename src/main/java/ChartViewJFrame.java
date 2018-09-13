import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtils;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

public class ChartViewJFrame {

    private ChartPanel chartPanel;
    private String chartImgFilePath;
    private Boolean runStatus;

    public ChartViewJFrame(ChartPanel chartPanel,String chartImgFilePath, Boolean runStatus) {
        this.chartPanel = chartPanel;
        this.chartImgFilePath = chartImgFilePath;
        this.runStatus = runStatus;
    }

    public Boolean getRunStatus() {
        return runStatus;
    }

    public void setjFrame(JFrame jFrame) {
        jFrame.getContentPane().add(chartPanel, new BorderLayout().CENTER);
        jFrame.pack();
        jFrame.setVisible(true);
        jFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowevent) {
                try {
                    ChartUtils.saveChartAsJPEG(new File(chartImgFilePath), chartPanel.getChart(), 700, 500);
                    runStatus = !runStatus;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
