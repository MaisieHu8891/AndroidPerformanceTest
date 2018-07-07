package testscene;
import utilclass.CmdAdb;
import utilclass.WriteLogFiles;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class HandleData {
    private String filepath;

    public HandleData(String filepath) {
        this.filepath = filepath;

    }

    public void getdata() throws IOException, InterruptedException {
        String[] cmdstr = {"adb", "shell", "logcat", "|grep miaokai"};
        CmdAdb ca = new CmdAdb();
        ca.executeCMDfile(cmdstr, this.filepath);

    }

    //String[] key 传多个关键字列表  要求data是 key === data 格式
    public void filterData(String[] keys,String filterpath) throws IOException {
        RandomAccessFile raf=new RandomAccessFile (this.filepath,"r");
        String str = null;
        WriteLogFiles wcsv = new WriteLogFiles(filterpath,keys);
        for (String i : keys){
            while ((str = raf.readLine()) != null) {
                String keyreg ="(.*)"+i+"(.*)";
                if(str.matches(keyreg)){
                    String[] tmparray =str.split(" == ");
                    String wkey = tmparray[0];
                    String wvalue = tmparray[1];;
                    Pattern p=Pattern.compile(":\\D+");
                    Matcher m=p.matcher(wkey);
                    m.find(); //需要先执行find函数才能找到
                    wkey = m.group();
                    System.out.println(wkey);
                    System.out.println(wvalue);



                }
            }
            raf.seek(0);

            //System.out.println(str);
        }
        raf.close();
    }
}