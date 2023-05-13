import org.openqa.selenium.By;

import java.util.HashMap;

public class RadioButtonPage extends Pages {

    public RadioButtonPage()
    {
        super();
    }

    //TODO Write Map to File

    private final String url = "https://demo.seleniumeasy.com/basic-radiobutton-demo.html";

    private final By TEXT_VALUES = By.xpath("//p[@class=\"groupradiobutton\"]");
    private final By BUTTON_MALE = By.xpath("(//input[@value=\"Male\"])[2]");
    private final By BUTTON_AGES = By.xpath("//input[@value=\"5 - 15\"]");

    public HashMap<String, String> getRadioValues()
    {
        navigate(url);
        scrollIntoView(TEXT_VALUES);

        driver.findElement(BUTTON_MALE).click();
        driver.findElement(BUTTON_AGES).click();
        executeJavaScript("getValues();");
        String message = driver.findElement(TEXT_VALUES).getText();

        System.out.println(message);
        StringBuilder strBuilder = new StringBuilder(message);
        strBuilder.insert(11, ",");

        HashMap<String, String> result = new HashMap<>();
        String[] array = strBuilder.toString().split(",");

        for(String item: array){
            String[] subArray = item.split(":");
            result.put(subArray[0], subArray[1]);
        }

        return result;
    }
}
