package testscene;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.AppiumDriver;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class IORoom {
    private AndroidDriver driver;


    public void setUp() throws Exception {
        //启动appium, 之前先手动启动appiumserver
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName","cb476a38");
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("platformVersion","7.1.1");

        //配置测试apk
        capabilities.setCapability("appPackage", "com.panda.videoliveplatform");
        capabilities.setCapability("appActivity", ".activity.WelcomeActivity");//被测app的入口Activity名称
        capabilities.setCapability("sessionOverride", true);    //每次启动时覆盖session，否则第二次后运行会报错不能新建session
        capabilities.setCapability("noReset",true);
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        //driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    public void doAction() throws Exception {
        Runtime.getRuntime().exec("adb shell logcat -v time 'TAG:*'|grep key > D:\\TEST\\androidtestbuildapk\\logcat\\logcat.txt");
        Thread.sleep(10000);
        try{
            if(driver.findElementById("com.panda.videoliveplatform:id/btnUpdate").isDisplayed()){
                System.out.print("11");
                driver.findElementById("com.panda.videoliveplatform:id/close").click();
            }
        } catch (Exception e){
            System.out.println("can't find update");
            }
        try{
            driver.findElementById("com.panda.videoliveplatform:id/iv_home_search").click();
            driver.findElementById("com.panda.videoliveplatform:id/searchedit").sendKeys("1680684");
            driver.findElementById("com.panda.videoliveplatform:id/image_button_search").click();
            Thread.sleep(3000);
        }catch (Exception e){
            System.out.println("can't find element");
        }

//        for(int i=0;i<1000;i++){
//            try {
//                driver.findElementById("com.panda.videoliveplatform:id/iv_pic").click();
//            }catch (Exception e){
//                System.out.println("can't find element");
//            }
//            finally {
//                driver.pressKeyCode(AndroidKeyCode.BACK);
//            }
//
//        }

        for(int i=0;i<1000;i++) {
            try {
                driver.findElementById("com.panda.videoliveplatform:id/xingyan_room_number").click();
                Thread.sleep(3000);
                driver.findElementById("com.panda.videoliveplatform:id/iv_player_enlarge").click();
                Thread.sleep(3000);
                //driver.swipe();
                driver.pressKeyCode(AndroidKeyCode.BACK);
                driver.pressKeyCode(AndroidKeyCode.BACK);
            } catch (Exception e) {
                System.out.println("can't find element");
            }
        }
    }


    public void teardown() throws Exception{
        driver.quit();
    }


}
