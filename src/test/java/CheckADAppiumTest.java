import org.junit.Test;


public class CheckADAppiumTest extends AbstractAppiumTest {
    @Test
    public void checkADOperation() throws InterruptedException {
        app.checkAD().opAd();
    }

}