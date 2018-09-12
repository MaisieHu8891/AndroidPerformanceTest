package utilclass;


public class GetAppUtilInfo {
    public GetAppUtilInfo(){};

    public int  GetAndroidVersion(){
        try {
            String verinfo = new CmdAdb("adb shell getprop ro.build.version.release").getAppCmdInfo()[1];
            String ver = new PatternRule().regStr(verinfo, "\\d?");
            int androidVer = Integer.parseInt(ver);
            return androidVer;
        }catch (Exception e){
            return 0;
        }
    }

    public String GetAppUserID()  {
        try {
            String userIdinfo= new CmdAdb("adb shell dumpsys package com.panda.videoliveplatform|grep userId").getAppCmdInfo()[1];
            return new PatternRule().regStr(userIdinfo, "\\d+");
        } catch (Exception e){
            System.out.println("获取应用userid失败");
            return null;
        }
    }

    public String GetAppPid() {
        try {
            String pid = new CmdAdb("adb shell ps|grep com.panda.videoliveplatform$").getAppCmdInfo()[1].split("\\s+")[1];
            return pid;
        }catch (Exception e){
            System.out.println("获取应用PID失败");
            return null;
        }
    }
}
