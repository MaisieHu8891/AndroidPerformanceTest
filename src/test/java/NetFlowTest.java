import utilclass.GetAppUtilInfo;
import java.io.File;

public class NetFlowTest {
    public static void main(String[] args) {
        File filePath = new File(".\\out\\log");
        filePath.mkdirs();
        String userId = new GetAppUtilInfo().GetAppUserID();
        if (userId != null) {
            NetFlowChart netFlowChart = new NetFlowChart(userId, "NetFlow_TEST");
            Thread netFlowThread = new Thread(netFlowChart);
            netFlowThread.start();
        } else {
            System.out.println("记录网络流量数据失败");
        }
    }
}
