import org.junit.Test;

import static org.junit.Assert.assertTrue;
/*
 *
 * 逻辑操作类
 *
 */
public class IORoomTest extends AbstractTest {
    public IORoomTest() {}
    /* 一个简单的加法运算,期望结果为正确的值 */
//    @Test
//    public void twoPlusTwoOperation() {
//        app.calculatorScreen().addTwoAndTwo();
//        assertTrue(app.calculatorScreen().isResultCorrect("4"));
//    }
    @Test
    public void IORoomOperation() throws InterruptedException {
        app.allroomScreen().ioRoom();
    }

}

