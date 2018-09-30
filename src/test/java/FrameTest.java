import utilclass.CmdAdb;
import utilclass.GetAppUtilInfo;
import utilclass.LoggerUse;
import utilclass.PatternRule;

import java.io.IOException;

public class FrameTest {
    public static void main(String[] args) throws IOException, InterruptedException {
        String resetgfxinfoCmd = "adb shell dumpsys gfxinfo com.panda.videoliveplatform reset";
        String framestatsCmds = "adb shell dumpsys gfxinfo com.panda.videoliveplatform framestats";
        CmdAdb cmdAdb = new CmdAdb();
        cmdAdb.executeCMDget(resetgfxinfoCmd);//重置帧信息
        Thread.sleep(8000);
        int ver = new GetAppUtilInfo().GetAndroidVersion();
        FramestatsDataCsv framestatsDataCsv = new FramestatsDataCsv();
        if (ver >= 6){
//            do
            String framestats = cmdAdb.executeCMDget(framestatsCmds);
//            String[] JankyStats = new FrameStatsRead(framestats).JankyStatsData();
//            framestatsDataCsv.JankyStats(JankyStats);



        } else{
            System.out.println("获取app帧率只能在6.0以上的安卓手机，请更换手机");
        }

    }
}

