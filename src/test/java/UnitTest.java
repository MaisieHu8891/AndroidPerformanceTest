import testscene.HandleData;

import java.io.IOException;

public class UnitTest {
    public static void main(String[] args) throws InterruptedException, IOException {
        HandleData d = new HandleData("C:\\Users\\hjx19\\Documents\\logcattest.txt");
            //d.getdata();
        String[] keys = {"miaokai test time except Network Connection","miaokai test total time"};
            d.filterData(keys,"C:\\Users\\hjx19\\Documents\\logcat.csv");
    }
}