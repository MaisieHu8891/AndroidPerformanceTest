package testscene;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.URL;

public abstract class AppiumDriverBuilder<SELF, DRIVER extends AppiumDriver<?>> {
    protected URL endpoint;
    @SuppressWarnings("unchecked")
    public SELF withEndpoint(URL endpoint) {
        this.endpoint = endpoint;
        return (SELF) this;
    }
    public abstract DRIVER build(String appPackage,String appActivity);
    public static class AndroidDriverBuilder extends AppiumDriverBuilder<AndroidDriverBuilder, AndroidDriver<?>> {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        @Override
        public AndroidDriver<?> build(String appPackage,String appActivity) {
            capabilities.setCapability("platformName", "Android");
//            capabilities.setCapability("deviceName", "cb476a38");
            capabilities.setCapability("deviceName", "be26dd89");
            capabilities.setCapability("platformVersion", "6.0.1");
            //通过newcommandtimeout将超时时间改长
            capabilities.setCapability("newCommandTimeout","600000");
            //不重新安装应用
            capabilities.setCapability("noReset",true);
            //待测包名及首次启动的页面
            capabilities.setCapability("appPackage", appPackage);
            capabilities.setCapability("appActivity", appActivity);
            //使用appium Unicode键盘输入法，输入完毕后重置输入法
            capabilities.setCapability("unicodeKeyboard", true);
            capabilities.setCapability("resetKeyboard", true);
            capabilities.setCapability("deviceReadyTimeout",30);
            return new AndroidDriver<AndroidElement>(endpoint, capabilities);
        }
    }
    public static AndroidDriverBuilder forAndroid() {
        return new AndroidDriverBuilder();
    }
}

