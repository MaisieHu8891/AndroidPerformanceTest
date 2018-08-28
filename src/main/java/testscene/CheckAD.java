package testscene;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

import java.util.List;

public class CheckAD extends AbstractScreen{

    @AndroidFindBy(id ="com.panda.videoliveplatform:id/custom_text")
    private List<MobileElement> classElements;

    @AndroidFindBy(id ="com.panda.videoliveplatform:id/tv_card_header")
    private MobileElement cardheader;

    public CheckAD(AppiumDriver<?> driver) {
        super(driver);
    }

    public void opAd() throws InterruptedException {
        Thread.sleep(7000);
        for(int i =0; i<100; i++){
            try {
                Thread.sleep(500);
                classElements.get(1).click();
                Thread.sleep(10000);
                this.swipeToPosition(cardheader,990);
                Thread.sleep(3000);
                classElements.get(3).click();
            }catch (Exception e){
                System.out.println("error");
            }
        }
    }

}
