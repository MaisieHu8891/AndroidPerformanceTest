import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class GetNetWorkFlow {
    private String userId;
    public GetNetWorkFlow(String userId) {
        this.userId = userId;
    }
    public String data() throws Exception {
      String[] netinfo = GetCmmandInfo.appInfo("adb shell cat /proc/net/xt_qtaguid/stats|grep "+userId);
        String line = netinfo[1];
        return line;
    }
}

class GetCPU {
    public GetCPU() {}

    public String[] data(int ver) throws Exception {
        if (ver >= 8) {
            //增加支持android8.0系统的cpu获取
            //GetCmmandInfo gprocess= new GetCmmandInfo("adb shell ps|grep com.panda.videoliveplatform$");
            String pid = (GetCmmandInfo.appInfo("adb shell ps|grep com.panda.videoliveplatform$")[1]).split("\\s+")[1];
            System.out.println(pid);
            //GetCmmandInfo gcpuinfo= new GetCmmandInfo("adb shell top -n 1|grep " + pid);
            String[] cpuinfo_ver = GetCmmandInfo.appInfo("adb shell top -n 1|grep " + pid);
            cpuinfo_ver[1] = cpuinfo_ver[1].split("\\s+")[9];
            System.out.println(cpuinfo_ver[1]);
            return cpuinfo_ver;
        } else {
                GetCmmandInfo gcpu = new GetCmmandInfo();
                String[] cpuinfo = GetCmmandInfo.appInfo("adb shell top -n 1|grep com.panda.videoliveplatform");
                String cpunumber = cpuinfo[1];
                String rscpu = gcpu.regStr(cpunumber, "\\d+%");
                cpuinfo[1] = rscpu.substring(0, rscpu.length() - 1);
                return cpuinfo;
             }
        }
    }

class GetMEM {
    public GetMEM() {}
    public String[] data() {
        try {
            String[] meminfo = GetCmmandInfo.appInfo("adb shell dumpsys meminfo com.panda.videoliveplatform|grep TOTAL");
            meminfo[1] = meminfo[1].split("\\s+")[2];
            System.out.println(meminfo[1]);
            return meminfo;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

public class  GetData{}