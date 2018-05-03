public class GetUniversalInfo {
    public GetUniversalInfo(){};

    public int  GetAndroidVersion() throws Exception {
        GetCmmandInfo GC = new GetCmmandInfo("adb shell getprop ro.build.version.release");
        String verinfo = GC.appInfo()[1];
        String ver = GC.regStr(verinfo, "\\d?");
        int androidVer = Integer.parseInt(ver);
        return androidVer;
    }

    public String GetAppUserID() throws Exception {
        GetCmmandInfo GC= new GetCmmandInfo("adb shell dumpsys package com.panda.videoliveplatform|grep userId");
        String userIdinfo = GC.appInfo()[1];
        return GC.regStr(userIdinfo, "\\d+");
    }

}
