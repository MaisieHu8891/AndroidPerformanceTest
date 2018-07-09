package testscene;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static io.appium.java_client.touch.offset.PointOption.point;

public abstract class AbstractScreen {

    protected final AppiumDriver<?> driver;
    public AbstractScreen(AppiumDriver<?> driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void swipeToPosition(MobileElement elem,int moveY){
        TouchAction ta = new TouchAction(this.driver);
        Point ecenter = elem.getCenter();
        int cx = ecenter.getX(); //971
        int cy = ecenter.getY(); //1764
        ta.press(point(cx,cy)).moveTo(point(cx,moveY)).release().perform();
    }

    public MobileElement findElementWithTimeout(By by, int timeOutInSeconds) {
        return (MobileElement)(new WebDriverWait(driver, timeOutInSeconds)).until(ExpectedConditions.presenceOfElementLocated(by));
    }

    protected void takeScreenShot(){
        driver.getScreenshotAs(OutputType.BASE64);
    }

}



