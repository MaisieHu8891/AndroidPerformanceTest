import utilclass.GetAppUtilInfo;
import java.io.File;


public class ExecPerfTest {
    public static void main(String[] args) throws Exception {
        File filePath = new File(".\\out\\log");
        filePath.mkdirs();
        GetAppUtilInfo GU = new GetAppUtilInfo();
        int ver = GU.GetAndroidVersion();
        String pid = GU.GetAppPid();
        String userId = GU.GetAppUserID();

        ChartViewPanel cpuPanel = new ChartViewPanel("cpu", "com.panda.videoliveplatform", "cpu");
        CpuChart cpuChart = new CpuChart(ver,pid,"CPU_TEST",".\\out\\log\\CPU"+System.currentTimeMillis()+".jpg");
        cpuChart.setRunStatus(true);
        cpuChart.setCpuPanel(cpuPanel);
        Thread cpuThread = new Thread(cpuChart);
        cpuThread.start();

        ChartViewPanel memPanel = new ChartViewPanel("memory", "com.panda.videoliveplatform", "memory");
        MemoryChart memoryChart = new MemoryChart("Memory_TEST",".\\out\\log\\Memory"+System.currentTimeMillis()+".jpg");
        memoryChart.setRunStatus(true);
        memoryChart.setMemoryPanel(memPanel);
        Thread memoryThread = new Thread(memoryChart);
        memoryThread.start();

        ChartViewPanel netFlowPanel = new ChartViewPanel("rxbytes", "txbytes","com.panda.videoliveplatform", "NetFlow");
        NetFlowChart netFlowChart = new NetFlowChart(userId,"NetFlow_TEST",".\\out\\log\\NetFlow"+System.currentTimeMillis()+".jpg");
        netFlowChart.setRunStatus(true);
        netFlowChart.setNetFlowPanel(netFlowPanel);
        Thread netFlowThread = new Thread(netFlowChart);
        netFlowThread.start();
    }
}
