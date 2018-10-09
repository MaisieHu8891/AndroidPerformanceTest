package utilclass;


public class GetAppUtilInfo {
    public GetAppUtilInfo(){};

    public int  GetAndroidVersion(){
        try {
            String verinfo = new CmdAdb().getAppCmdInfo("adb shell getprop ro.build.version.release")[1];
            String ver = new PatternRule().regStr(verinfo, "\\d?", 0);
            int androidVer = Integer.parseInt(ver);
            return androidVer;
        }catch (Exception e){
            return 0;
        }
    }

    public String GetAppUserID()  {
        AppConfig appConfig = new AppConfig();
        appConfig.setMap();
        String app = appConfig.getAppName();
        try {
            String userIdinfo= new CmdAdb().getAppCmdInfo("adb shell dumpsys package "+app+"|grep userId")[1];
            String userId = new PatternRule().regStr(userIdinfo, "\\d+", 0);
            return userId;
        } catch (Exception e){
            System.out.println("获取应用userid失败");
            return null;
        }
    }

    public String GetAppPid() {
        AppConfig appConfig = new AppConfig();
        appConfig.setMap();
        String app = appConfig.getAppName();
        try {
            String pid = new CmdAdb().getAppCmdInfo("adb shell ps|grep "+app)[1].split("\\s+")[1];
            return pid;
        }catch (Exception e){
            System.out.println("获取应用PID失败");
            return null;
        }
    }
}
