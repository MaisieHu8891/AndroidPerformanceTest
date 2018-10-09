import utilclass.AppConfig;
import utilclass.CmdAdb;
import utilclass.GetAppUtilInfo;

import java.io.File;
import java.io.IOException;

public class FrameTest {
    public static void main(String[] args) throws IOException, InterruptedException {
        AppConfig appConfig = new AppConfig();
        appConfig.setMap();
        String app = appConfig.getAppName();
        System.out.println("为了数据更准备，请在执行前，先将app打开到待测Activity界面");
        System.out.print("请输入你要测试多久UI性能情况，输入数字，比如输入10，代表10分钟: ");
        int testTime = Integer.parseInt(String.valueOf((char) System.in.read()));
        File filePath = new File(".\\out\\log");
        filePath.mkdirs();
        String resetgfxinfoCmd = "adb shell dumpsys gfxinfo "+app+" reset";
        String framestatsCmds = "adb shell dumpsys gfxinfo "+app+" framestats";
        CmdAdb cmdAdb = new CmdAdb();
        System.out.println("首次获取之前,先重置帧数据...请等待...");
        cmdAdb.executeCMDget(resetgfxinfoCmd);//重置帧信息
        Thread.sleep(10000);
        System.out.println("开始测试 " + testTime + "分钟...请等待 ");
        int ver = new GetAppUtilInfo().GetAndroidVersion();
        FramestatsDataCsv framestatsDataCsv = new FramestatsDataCsv();
        if (ver >= 6) {
            for (int n = 1; n < (testTime * 60 * 1000 / 2000); n++) {
                String frameStats = cmdAdb.executeCMDget(framestatsCmds);
                String[] JankyStats = new FrameStatsRead(frameStats).JankyStatsData();
                String[] FrameStats = new FrameStatsRead(frameStats).FrameData();
                framestatsDataCsv.JankyStats(JankyStats);
                framestatsDataCsv.FrameStats(FrameStats);
                Thread.sleep(2000);
            }
        } else {
            System.out.println("获取app帧率只能在6.0以上的安卓手机，请更换手机");
        }

    }
}

