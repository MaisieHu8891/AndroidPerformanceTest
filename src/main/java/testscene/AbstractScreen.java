package testscene;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstractScreen {

    protected final AppiumDriver<?> driver;
    public AbstractScreen(AppiumDriver<?> driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    /**
     * description：定位到元素坐标，然后进行滑动操作
     * elem: 要操作的元素     *
     * moveX：从起始x位置需要移动的距离（移动到beginX+moveX的位置）
     * moveY：从起始y位置需要移动的距离（移动到beginY+moveY的位置）
     */
    public void swipeToPosition(MobileElement elem,int moveX,int moveY){
        //MobileElement elem = driver.findElement(By.id(id));
        System.out.println("moveX ="+moveX);
        System.out.println("moveY ="+moveY);
        Point start = elem.getLocation();
        TouchAction ta = new TouchAction(this.driver);
        int startX = start.x;
        System.out.println("startX ="+startX);
        int startY = start.y;
        System.out.println("startY ="+startY);
        // 获取控件坐标轴差
        Dimension q = elem.getSize();
        int x = q.getWidth();
        System.out.println("x ="+x);
        int y = q.getHeight();
        System.out.println("y ="+y);
        // 计算出控件结束坐标
        int endX = x + startX;
        System.out.println("endX ="+endX);
        int endY = y + startY;
        System.out.println("endY ="+endY);
        // 计算中间点坐标
        int centreX = (endX + startX) / 2;
        System.out.println("centreX ="+centreX);
        int centreY = (endY + startY) / 2;
        System.out.println("centreY="+centreY);
        ta.press(centreX,centreY).moveTo(moveX,moveY).release().perform();
    }
//    public void outRoom
    public MobileElement findElementWithTimeout(By by, int timeOutInSeconds) {
        return (MobileElement)(new WebDriverWait(driver, timeOutInSeconds)).until(ExpectedConditions.presenceOfElementLocated(by));
    }

    protected void takeScreenShot(){
        driver.getScreenshotAs(OutputType.BASE64);
    }

}



