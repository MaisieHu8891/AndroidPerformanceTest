import utilclass.GetAppUtilInfo;
import java.io.File;

public class ExecPerfTest {
    public static void main(String[] args) {
        File filePath = new File(".\\out\\log");
        filePath.mkdirs();
        GetAppUtilInfo GU = new GetAppUtilInfo();
        int ver = GU.GetAndroidVersion();
        String pid = GU.GetAppPid();
        String userId = GU.GetAppUserID();
        if (ver > 0 && pid != null && userId != null) {
            CpuChart cpuChart = new CpuChart(ver, pid, "CPU_TEST");
            Thread cpuThread = new Thread(cpuChart);
            cpuThread.start();
            MemoryChart memoryChart = new MemoryChart("Memory_TEST");
            Thread memoryThread = new Thread(memoryChart);
            memoryThread.start();
            NetFlowChart netFlowChart = new NetFlowChart(userId, "NetFlow_TEST");
            Thread netFlowThread = new Thread(netFlowChart);
            netFlowThread.start();
        } else {
            System.out.println("记录数据失败");
        }
    }
}
