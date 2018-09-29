import utilclass.CmdAdb;
import utilclass.GetAppUtilInfo;
import utilclass.LoggerUse;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class FramestatsRead {
    private String filePath;
    private static String ActivityName = "LiveRoomActivity";
    private static String DataKey = "---PROFILEDATA---";

    public FramestatsRead(String filePath) {
        this.filePath = filePath;
    }

    public void getFrameData() throws IOException, InterruptedException {
        String resetgfxinfoCmd = "adb shell dumpsys gfxinfo com.panda.videoliveplatform reset";
        String framestatsCmds = "adb shell dumpsys gfxinfo com.panda.videoliveplatform framestats";
        CmdAdb cmdAdb = new CmdAdb();
        cmdAdb.executeCMDget(resetgfxinfoCmd);//重置帧信息
        Thread.sleep(4000);
        int ver = new GetAppUtilInfo().GetAndroidVersion();
        if (ver >= 6){
            String framestats = cmdAdb.executeCMDget(framestatsCmds);
            LoggerUse.logobject.info("frame stats ：" +framestats);
        } else{
            System.out.println("获取app帧率只能在6.0以上的安卓手机，请更换手机");
        }


    }


}
