package utilclass;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CmdAdb {

    public void executeCMDconsole(String cmd) {
        //此方法为打印日志到控制台！！！！！！！！！！！！
        System.out.println("在cmd里面输入"+cmd);
        Process p;
        try {
            p = Runtime.getRuntime().exec(cmd);
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

    public String executeCMDfile(String[] cmmands, String logToFile) throws IOException {
        //此方法为輸出日志到指定文件夹！！！！！！！！！！！！
        //如果 String cmmand 那麼  String cmmand = "adb shell logcat |grep miaokai > D:\TEST\androidtestbuildapk\logcat\logcattest.txt";
        //String[] cmmands 所以   String commands[] = {"adb","shell","logcat","|grep miaokai"};
        //String logToFile  將日誌保存到logToFile
        //可加参数String dirTodoCMD 在dirTodoCMD執行cmd命令
        //由于將日志輸出到文件裡面了，就不能再将日誌輸出到console了
        try {
            ProcessBuilder builder = new ProcessBuilder(cmmands);
//            if (dirTodoCMD != null)
//                builder.directory(new File(dirTodoCMD));
            builder.redirectErrorStream(true);
            builder.redirectOutput(new File(logToFile));
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

}

