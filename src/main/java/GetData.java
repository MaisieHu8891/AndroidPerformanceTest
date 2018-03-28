import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class GetNetWorkFlow {
    String userId;
    List<String> lines = new ArrayList<>();

    public GetNetWorkFlow(String userId) {
        this.userId = userId;
    }
    public List<String> data() throws Exception {
        Process p = Runtime.getRuntime().exec("adb shell cat /proc/net/xt_qtaguid/stats|grep "+userId);
        InputStream is = p.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        p.waitFor();
        String line = reader.readLine();
        while (line != null) {
            lines.add(line);
            line = reader.readLine();
        }
        reader.close();
        return lines;
    }
}

class GetCPU {
    public GetCPU() {}

    public String[] data(int ver) throws Exception {
        if (ver >= 8) {
            //增加支持android8.0系统的cpu获取
            GetCmmandInfo gprocess= new GetCmmandInfo("adb shell ps|grep com.panda.videoliveplatform$");
            String pid = (gprocess.appInfo()[1]).split("\\s+")[1];
            System.out.println(pid);
            GetCmmandInfo gcpuinfo= new GetCmmandInfo("adb shell top -n 1|grep " + pid);
            String[] cpuinfo_ver = gcpuinfo.appInfo();
            cpuinfo_ver[1] = cpuinfo_ver[1].split("\\s+")[9];
            System.out.println(cpuinfo_ver[1]);
            return cpuinfo_ver;
        } else {
            GetCmmandInfo gcpu = new GetCmmandInfo("adb shell top -n 1|grep com.panda.videoliveplatform");
                String[] cpuinfo = gcpu.appInfo();
                String cpunumber = cpuinfo[1];
                String rscpu = gcpu.regStr(cpunumber, "\\d+%");
                cpuinfo[1] = rscpu.substring(0, rscpu.length() - 1);
                return cpuinfo;
             }
        }
    }

class GetMEM {
    String cmmand;
    public GetMEM(String cmmand) {
        this.cmmand =cmmand;
    }
    public String[] data() {
        GetCmmandInfo gmem = new GetCmmandInfo(this.cmmand);
        try {
            String[] meminfo = gmem.appInfo();
            meminfo[1] = meminfo[1].split("\\s+")[2];
            //System.out.println(meminfo[1]);
            return meminfo;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

public class  GetData{}