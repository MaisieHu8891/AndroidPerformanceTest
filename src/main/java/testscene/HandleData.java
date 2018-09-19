package testscene;

import org.apache.commons.csv.CSVPrinter;
import utilclass.CmdAdb;
import utilclass.WriteLogFiles;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class HandleData {
    private String[] cmds;
    private String filepath;
    private String filename;


    public HandleData(String[] cmds, String filepath, String filename) {
        this.cmds = cmds;
        this.filepath = filepath;
        this.filename = filename;

    }

    public void getdata() throws IOException, InterruptedException {
        new CmdAdb().executeCMDfile(cmds,filepath,filename);
    }

    //String[] key 传多个关键字列表  要求data是 key === data 格式
    public void filterData(String[] keys, String filterpath) throws IOException {
        String[] wcsvhead = {"key", "value"};
        WriteLogFiles wcsv = new WriteLogFiles(filterpath, wcsvhead);
        CSVPrinter printercsv = wcsv.initPrinter();
        RandomAccessFile raf = new RandomAccessFile(this.filepath, "r");
        String str = null;
        for (String i : keys) {
            while ((str = raf.readLine()) != null) {
                String keyreg = "(.*)" + i + "(.*)";
                if (str.matches(keyreg)) {
                    System.out.println(str);
                    String[] tmparray = str.split(" == ");
                    String wvalue = tmparray[1];
                    String wkey = tmparray[0];
                    //android格式：
                    Pattern p = Pattern.compile(":\\D+");
                    //IOS格式：
//                    Pattern p=Pattern.compile(",\\s\\D+");
                    Matcher m = p.matcher(wkey);
                    m.find(); //需要先执行find函数才能找到
                    wkey = m.group();
                    String[] resstr = {wkey, wvalue};
                    wcsv.doWrite(printercsv, resstr);
                }
            }
            raf.seek(0);

        }
        raf.close();
    }
}