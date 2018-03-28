import org.jfree.data.time.Millisecond;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

class ExecCpu implements Runnable{
    CpuTimeChart tccpu = new CpuTimeChart ("cpu", "com.panda.videoliveplatform", "cpu");
    JFrame framecpu = new JFrame("CPU Test");
    boolean exit = false;
    public ExecCpu() throws Exception {
        framecpu.getContentPane().add(tccpu, new BorderLayout().CENTER);
        framecpu.pack();
        framecpu.setVisible(true);
    }
    @Override
    public void run() {
        while (!exit) {
            try {
                Double cpunum = tccpu.CpuNum();
                tccpu.timeSeries.add(new Millisecond(), cpunum);
                Thread.sleep(1500);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

class ExecMemery implements Runnable {
    MemTimeChart tcmem = new MemTimeChart("memery", "com.panda.videoliveplatform", "memery");
    JFrame framemem = new JFrame("Memery Test");
    boolean exit = false;
    public ExecMemery() throws IOException{
        framemem.getContentPane().add(tcmem, new BorderLayout().CENTER);
        framemem.pack();
        framemem.setVisible(true);
    }
    @Override
    public void run() {
        while (!exit) {
            try {
                Integer memnum = tcmem.MemNum();
                tcmem.timeSeries.add(new Millisecond(), memnum);
                Thread.sleep(1500);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}



