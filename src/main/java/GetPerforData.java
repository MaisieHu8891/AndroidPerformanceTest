import utilclass.CmdAdb;
import utilclass.PatternRule;


public class  GetPerforData{

    public String[] GetCPU(int ver, String pid) {
        String[] cpuinfo = new String[2];
        if (ver >= 8) {
            //增加支持android8.0系统的cpu获取
            try {
                cpuinfo = new CmdAdb("adb shell top -n 1|grep " + pid).getAppCmdInfo();
                cpuinfo[1] = cpuinfo[1].split("\\s+")[9];
                LoggerUse.logobject.info("CPU百分比："+cpuinfo[1]+"%");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("该手机系统>=8.0, 获取CPU失败");
            }
        } else if(ver > 0) {
            try {
                cpuinfo = new CmdAdb("adb shell top -n 1|grep com.panda.videoliveplatform").getAppCmdInfo();
                String cpunumber = cpuinfo[1];
                String rscpu =new PatternRule().regStr(cpunumber, "\\d+%");
                cpuinfo[1] = rscpu.substring(0, rscpu.length() - 1);
                LoggerUse.logobject.info("CPU百分比："+cpuinfo[1]+"%"+'\n');
            }catch (Exception e){
                e.printStackTrace();
                System.out.println("该手机系统<8.0, 获取CPU失败");

            }
        }else {
            System.out.println("获取手机系统版本失败");
        }
        return cpuinfo;
    }

    public String[] GetMEM() {
        String[] meminfo = new String[2];
        try {
            meminfo = new CmdAdb("adb shell dumpsys meminfo com.panda.videoliveplatform|grep TOTAL").getAppCmdInfo();
            meminfo[1] = meminfo[1].split("\\s+")[2];
            LoggerUse.logobject.info("内存占用:"+meminfo[1]+"KB"+"\n");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("获取应用内存占用大小失败");
        }
        return meminfo;
        }

    public String[] GetNetWorkFlow(String userId) {
        String[] NetData = new String[3];
        try {
            String[] netinfo = new CmdAdb("adb shell cat /proc/net/xt_qtaguid/stats|grep " + userId).getAppCmdInfo();
            NetData[0] = netinfo[0];
            String line = netinfo[1];//从1开始，第6个数是rx_bytes接收数据, 第8个数是tx_bytes传输数据
            LoggerUse.logobject.info("网络传输数据："+"\n"+line+'\n');
            int[] rtdata = new int[2];
            String[] tmpline = line.split("\n");
            for (String i :tmpline){
                String[] itmp = i.split(" ");
                if (itmp.length>1){
                    rtdata[0]+= Integer.parseInt(itmp[5]);
                    rtdata[1]+= Integer.parseInt(itmp[7]);//从0开始，第5个数是rx_bytes接收数据, 第7个数是tx_bytes传输数据
                    LoggerUse.logobject.info("rxbytes:"+itmp[5]+" and tx_bytes:"+itmp[7]);
                }
                else
                    continue;
            }
            NetData[1] = Integer.toString(rtdata[0]);
            NetData[2] = Integer.toString(rtdata[1]);
            LoggerUse.logobject.info("systime:"+NetData[0]+ "  rxbytes:"+ NetData[1] + "  tx_bytes:"+NetData[2]);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("网络传输数据获取失败");
        }
        return NetData ;
    }

}