package testscene;
import io.appium.java_client.AppiumDriver;
/*
 * 应用类，返回各个操作页面类
 */
public class VideoLivePlatform {
    private final AppiumDriver<?> driver;
    public VideoLivePlatform(AppiumDriver<?> driver) {
        this.driver = driver;
    }
    public AllRoomScreen allroomScreen() {
        return new AllRoomScreen(driver);
    }

    public CheckAD checkAD() {
        return new CheckAD(driver);
    }
}
