import utilclass.GetAppUtilInfo;
import java.io.File;


public class ExecPerfTest {
    public static void main(String[] args) throws Exception {
        File filePath = new File(".\\out\\log");
        filePath.mkdirs();
        GetAppUtilInfo GU = new GetAppUtilInfo();
        int ver = GU.GetAndroidVersion();
        String pid = GU.GetAppPid();
        ChartViewPanel cpuPanel = new ChartViewPanel("cpu", "com.panda.videoliveplatform", "cpu");
        CpuChart cpuChart = new CpuChart(ver, pid,true,"CPU_TEST",".\\out\\log\\cpu"+System.currentTimeMillis()+".jpg");
        cpuChart.setCpuPanel(cpuPanel);
        cpuChart.setRunStatus(true);
        Thread cpuThread = new Thread(cpuChart);
        cpuThread.start();
    }
}
