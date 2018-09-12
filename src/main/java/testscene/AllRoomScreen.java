package testscene;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

import java.util.List;


public class AllRoomScreen extends AbstractScreen {
    @AndroidFindBy(id = "com.panda.videoliveplatform:id/tv_fans_count")
    private List<MobileElement> roomElements;
    @AndroidFindBy(id ="com.panda.videoliveplatform:id/iv_chinajoy_close")
    private MobileElement chinajoyclose;
    public AllRoomScreen(AppiumDriver<?> driver) {
        super(driver);
    }

    public void ioRoom() throws InterruptedException {
        Thread.sleep(7000);
        int page = 17;
        while (page != 0){
            Thread.sleep(3000);
            int mesize = roomElements.size();
            for(MobileElement m :roomElements){
                m.click();
                Thread.sleep(4000);
//                if(chinajoyclose != null){
//                    driver.navigate().back();
//                }
                driver.navigate().back();
                System.out.println(mesize);
                if (mesize == 1){
                    this.swipeToPosition(m,167);
                    System.out.println("move to y300");
                }
                mesize --;
            }
            page  --;
        }
    }

}

