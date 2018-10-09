package utilclass;

import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;

public class AppConfig {
    private String appName;
    private String uiActivity;
    private Map<String, String> map;

    public void setMap(){
        try {
            InputStream input = new FileInputStream(new File("D:\\coding\\IdeaProjects\\PandaTest\\AndroidPerformanceTest\\src\\main\\resources\\AppConfig.yml"));
            Yaml yaml = new Yaml();
            map = (Map<String, String>) yaml.load(input);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.print("获取配置文件异常");
        }
    }

    public String getAppName() {
        appName = map.get("app");
        return appName;
    }


    public String getUiActivity() {
        uiActivity = map.get("activity");
        return uiActivity;
    }

}
