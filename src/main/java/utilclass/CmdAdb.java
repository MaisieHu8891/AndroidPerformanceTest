package utilclass;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CmdAdb {


    public String executeCMDget(String cmmand) {
        Process p;
        try {
            p = Runtime.getRuntime().exec(cmmand);
            InputStream is = p.getInputStream();
            BufferedReader bReader = new BufferedReader(new InputStreamReader(is, "gbk"));
            String line = null;
            StringBuffer sbBuffer = new StringBuffer();
            while ((line = bReader.readLine()) != null)
                sbBuffer.append(line+ "\n");
            is.close();
            return sbBuffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void executeCMDtofile(String[] cmmands, String dirTodoCMD, String logToFilePath) throws IOException {
        //logToFilePath 写全包括dirTodoCMD的路径
        try {
            ProcessBuilder builder = new ProcessBuilder(cmmands);
            if (dirTodoCMD != null)
                builder.directory(new File(dirTodoCMD));
            builder.redirectErrorStream(true);
            builder.redirectOutput(new File(logToFilePath));
            Process process = builder.start();
            process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        return null;
    }

    public String[] getAppCmdInfo(String cmmand) {
        BufferedReader br = null;
        try {
            Process p = Runtime.getRuntime().exec(cmmand);
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

