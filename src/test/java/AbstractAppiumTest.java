import testscene.AppiumDriverBuilder;
import testscene.VideoLivePlatform;
import io.appium.java_client.AppiumDriver;
import org.junit.Before;
import java.net.MalformedURLException;
import java.net.URL;

public abstract class AbstractAppiumTest {
    private AppiumDriver<?> driver;
    protected VideoLivePlatform app;
    /* Establish a connection to TestObject, or to a local device test is local. */
    @Before
    public void connect() throws MalformedURLException {
        this.driver = AppiumDriverBuilder.forAndroid()
                .withEndpoint(new URL("http://127.0.0.1:4723/wd/hub"))
                .build("com.panda.videoliveplatform", ".activity.WelcomeActivity");
        //实例化应用类
        app = new VideoLivePlatform(driver);
    }

}
