import utilclass.LoggerUse;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class FramestatsLogRead {
    private String filePath;
    private static String ActivityName  = "LiveRoomActivity";
    private static String DataKey = "---PROFILEDATA---";

    public FramestatsLogRead(String filePath) {
        this.filePath = filePath;
    }

    public void  getFrameData() throws IOException {
        FileInputStream inputStream = new FileInputStream(filePath);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String str = null;
        while ((str = bufferedReader.readLine()) != null) {

        }
        //close
        inputStream.close();
        bufferedReader.close();


    }


}
