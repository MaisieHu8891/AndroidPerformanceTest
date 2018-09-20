import testscene.HandleData;

import java.io.IOException;

public class MiaoKaiTest{
    public static void main(String[] args) throws IOException, InterruptedException {
        String[] cmds = {"adb", "shell", "logcat" ,"|grep" ,"miaokai"};
        HandleData d = new HandleData(cmds,"D:\\TEST\\androidtestbuildapk\\logcat\\","D:\\TEST\\androidtestbuildapk\\logcat\\logcattest.txt");
        d.getdata();
//        String[] keys = {"miaokai test time except Network Connection","miaokai test total time"};
//        d.filterData(keys,"D:\\TEST\\androidtestbuildapk\\logcat\\logcat.csv");
    }
}