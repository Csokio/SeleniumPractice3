import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
//import org.testng.annotations.Test;

import java.util.HashMap;

public class RadioTest extends BaseTest {


    RadioButtonPage radioButtonPage = new RadioButtonPage();

    @Test
    public void testWriteToFile()
    {
        HashMap<String, String> actualMap = radioButtonPage.getRadioValues();
        Pages.writeFile(actualMap, "actualMap.txt");

        Assertions.assertArrayEquals(Pages.readFile("expectedMap.txt"), Pages.readFile("actualMap.txt"));
    }


}
