import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetCmmandInfo {
    private String cmmand;

    public GetCmmandInfo(String cmmand) {
        this.cmmand = cmmand;
    }
    public GetCmmandInfo(){};

    public String[] appInfo() throws Exception {
        Process p = Runtime.getRuntime().exec(this.cmmand);
        InputStream is = p.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        p.waitFor();
        String s = reader.readLine();
        //Long t = System.currentTimeMillis();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String t = df.format(new Date());// new Date()为获取当前系统时间
         //System.out.println(t);
         //System.out.println(s);
        String[] perinfo = {t, s};
        return perinfo;
    }

    public String regStr(String s, String reg) {
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(s);
        if (matcher.find()) {
            String rs = matcher.group();
            return rs;
        } else {
            return null;
        }
    }

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
