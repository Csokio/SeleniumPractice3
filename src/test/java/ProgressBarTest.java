import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
//import org.testng.annotations.Test;


public class ProgressBarTest extends BaseTest {
    ProgressBarPage progressBarPage = new ProgressBarPage();

    @Test
    public void testSetSlider() throws InterruptedException {
        int valueExpected = 76;
        int valueActual = progressBarPage.setSlider(valueExpected);

        Assertions.assertEquals(valueExpected, valueActual);
    }

    @Test
    public void testPercentText()
    {
        String actual = progressBarPage.startDownload();

        Assertions.assertEquals("100%", actual);
    }

    @Test
    public void testWaitDownload()
    {
        String actual = progressBarPage.waitDownload();

        Assertions.assertEquals("Complete!", actual);
    }

}
