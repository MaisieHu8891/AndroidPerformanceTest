import testscene.HandleData;

import java.io.IOException;

public class UnitTest {
    public static void main(String[] args) throws InterruptedException, IOException {
        HandleData d = new HandleData("D:\\TEST\\androidtestbuildapk\\logcat\\logcattest.txt");
            //d.getdata();
        String[] keys = {"miaokai test time except Network Connection","miaokai test total time"};
            d.filterData(keys,"D:\\TEST\\androidtestbuildapk\\logcat\\logcat.csv");
    }
}