public class GetUniversalInfo {
    public GetUniversalInfo(){};

    public int  GetAndroidVersion() throws Exception {
        String verinfo = GetCmmandInfo.appInfo("adb shell getprop ro.build.version.release")[1];
        String ver = GetCmmandInfo.regStr(verinfo, "\\d?");
        int androidVer = Integer.parseInt(ver);
        return androidVer;
    }

    public String GetAppUserID() throws Exception {
        String userIdinfo = GetCmmandInfo.appInfo("adb shell dumpsys package com.panda.videoliveplatform|grep userId")[1];
        return GetCmmandInfo.regStr(userIdinfo, "\\d+");
    }

}
