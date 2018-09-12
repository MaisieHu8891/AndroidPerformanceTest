package utilclass;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CmdAdb {

    private String cmmand;
    public CmdAdb(String cmmand){
        this.cmmand = cmmand;
    }

    public void executeCMDconsole() {
        System.out.println("在cmd里面输入"+this.cmmand);
        Process p;
        try {
            p = Runtime.getRuntime().exec(this.cmmand);
            System.out.println(":::::::::::::::::::开始在控制台打印日志::::::::::::::::::::::>>>>>>");
            //p.waitFor();
            BufferedReader bReader=new BufferedReader(new InputStreamReader(p.getInputStream(),"gbk"));
            String line=null;
            while((line=bReader.readLine())!=null)
                System.out.println(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String executeCMDfile(String logToFilePath) throws IOException {
        try {
            ProcessBuilder builder = new ProcessBuilder(this.cmmand + " > "+logToFilePath);
//            if (dirTodoCMD != null)
//                builder.directory(new File(dirTodoCMD));
            builder.redirectErrorStream(true);
            builder.redirectOutput(new File(logToFilePath));
            Process process = builder.start();
            process.waitFor();
            // 得到命令执行后的结果
            InputStream is = process.getInputStream();
            BufferedReader buffer = new BufferedReader(new InputStreamReader(is, "gbk"));
            String line = null;
            StringBuffer sbBuffer = new StringBuffer();
            while ((line = buffer.readLine()) != null) {
                sbBuffer.append(line);
            }

            is.close();
            return sbBuffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public  String[] getAppCmdInfo(){
        BufferedReader br = null;
        try {
            Process p = Runtime.getRuntime().exec(this.cmmand);
            br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = null;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            String t = df.format(new Date());// new Date()为获取当前系统时间
            String cmdinfo = sb.toString();
            String[] perinfo = {t, cmdinfo};
            return perinfo;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

}

