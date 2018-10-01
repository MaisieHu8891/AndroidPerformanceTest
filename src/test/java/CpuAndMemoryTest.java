import utilclass.GetAppUtilInfo;
import java.io.File;

public class CpuAndMemoryTest {
    public static void main(String[] args) {
        File filePath = new File(".\\out\\log");
        filePath.mkdirs();
        GetAppUtilInfo GU = new GetAppUtilInfo();
        int ver = GU.GetAndroidVersion();
        String pid = GU.GetAppPid();
        if (ver > 0 && pid != null) {
            CpuChart cpuChart = new CpuChart(ver, pid, "CPU_TEST");
            Thread cpuThread = new Thread(cpuChart);
            cpuThread.start();
            MemoryChart memoryChart = new MemoryChart("Memory_TEST");
            Thread memoryThread = new Thread(memoryChart);
            memoryThread.start();
        } else {
            System.out.println("记录CPU和内存数据失败");
        }
    }
}
