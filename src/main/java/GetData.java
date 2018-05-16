
class GetNetWorkFlow {
    private String userId;
    private String[] NetData = new String[3];
    public GetNetWorkFlow(String userId) {
        this.userId = userId;
    }
    public String dataline() throws Exception {
      String[] netinfo = GetCmmandInfo.appInfo("adb shell cat /proc/net/xt_qtaguid/stats|grep "+userId);
      NetData[0] = netinfo[0];
      String line = netinfo[1]; //从1开始，第6个数是rx_bytes接收数据, 第8个数是tx_bytes传输数据
//    LoggerUse.logobject.info("网络传输数据："+"\n"+line+'\n');
      return line;
    }
    public String[] data(String line){
        int[] rtdata = new int[2];
        String[] tmpline = line.split("\n");
        for (String i :tmpline){
            String[] itmp = i.split(" ");
            if (itmp.length>1){
                //添加空行判断
                rtdata[0]+= Integer.parseInt(itmp[5]);
                rtdata[1]+= Integer.parseInt(itmp[7]);//从0开始，第5个数是rx_bytes接收数据, 第7个数是tx_bytes传输数据
//              LoggerUse.logobject.info("rxbytes:"+itmp[5]+" and tx_bytes:"+itmp[7]);
            }
            else
                continue;
        }
        NetData[1] = Integer.toString(rtdata[0]);
        NetData[2] = Integer.toString(rtdata[1]);
        LoggerUse.logobject.info("systime:"+NetData[0]+ "  rxbytes:"+ NetData[1] + "  tx_bytes:"+NetData[2]);
        return NetData ;
    }

}

class GetCPU {
    public GetCPU() {}
    public String[] data(int ver) throws Exception {
        if (ver >= 8) {
            //增加支持android8.0系统的cpu获取
            String pid = (GetCmmandInfo.appInfo("adb shell ps|grep com.panda.videoliveplatform$")[1]).split("\\s+")[1];
            System.out.println(pid);
            String[] cpuinfo_ver = GetCmmandInfo.appInfo("adb shell top -n 1|grep " + pid);
            cpuinfo_ver[1] = cpuinfo_ver[1].split("\\s+")[9];
            LoggerUse.logobject.info("CPU百分比："+cpuinfo_ver[1]+"%");
            return cpuinfo_ver;
        } else {
                String[] cpuinfo = GetCmmandInfo.appInfo("adb shell top -n 1|grep com.panda.videoliveplatform");
                String cpunumber = cpuinfo[1];
                String rscpu = GetCmmandInfo.regStr(cpunumber, "\\d+%");
                cpuinfo[1] = rscpu.substring(0, rscpu.length() - 1);
                LoggerUse.logobject.info("CPU百分比："+cpuinfo[1]+"%"+'\n');
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
            LoggerUse.logobject.info("内存占用:"+meminfo[1]+"KB"+"\n");
            return meminfo;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

public class  GetData{}