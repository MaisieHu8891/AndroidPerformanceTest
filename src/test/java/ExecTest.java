import org.jfree.chart.ChartUtils;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

public class ExecTest {
    public static void main(String[] args) throws Exception {
        File filePath = new File(".\\out\\log");
        filePath.mkdirs();
        ExecCpu ec = new ExecCpu();
        ExecMemery em = new ExecMemery();
        Thread cputhread = new Thread(ec);
        Thread memthread = new Thread(em);
        cputhread.start();
        memthread.start();
        ec.framecpu.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowevent) {
                try {
                    ChartUtils.saveChartAsJPEG(new File(".\\out\\log\\cpu"+System.currentTimeMillis()+".jpg"), ec.tccpu.getChart(), 700, 500);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ec.exit = true;
            }
        });
        em.framemem.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowevent) {
                try {
                    ChartUtils.saveChartAsJPEG(new File(".\\out\\log\\mem"+System.currentTimeMillis()+".jpg"), em.tcmem.getChart(), 700, 500);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                em.exit = true;
            }
        });
    }

}
