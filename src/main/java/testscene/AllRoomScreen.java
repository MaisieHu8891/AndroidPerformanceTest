package testscene;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

import java.util.List;

public class AllRoomScreen extends AbstractScreen {
    @AndroidFindBy(id = "com.panda.videoliveplatform:id/tv_fans_count")
    private List<MobileElement> roomElements;
    public AllRoomScreen(AppiumDriver<?> driver) {
        super(driver);
    }

    public void ioRoom() throws InterruptedException {
        Thread.sleep(20000);
        int mesize = roomElements.size();
        for(MobileElement m :roomElements){
            m.click();
            mesize --;
            if (mesize == 0){
                this.swipeToPosition(m,0,-600);
            }
        }

    }
}

