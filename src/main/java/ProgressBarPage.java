import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProgressBarPage extends Pages {

    public ProgressBarPage()
    {
        super();
    }

    //TODO Set Slider Value
    private final String url = "https://demo.seleniumeasy.com/drag-drop-range-sliders-demo.html";

    private final By SLIDER_GREEN = By.xpath("//div[@id='slider3']//input");

    private final By RANGE_SUCCESS = By.xpath("//output[@id='rangeSuccess']");
    public int setSlider(int desiredValue) throws InterruptedException {

        navigate(url);

        WebElement slider = driver.findElement(SLIDER_GREEN);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        String script = "arguments[0].value = arguments[1]";
        js.executeScript(script, slider, String.valueOf(desiredValue));
        js.executeScript("arguments[0].dispatchEvent(new Event('change'))", slider);

        Thread.sleep(2000);

        return Integer.parseInt(driver.findElement(RANGE_SUCCESS).getText());
    }

    //TODO Download Progress

    private final By NAVBAR = By.xpath("//ul[@class=\"nav navbar-nav navbar-right\"]");
    private final By BUTTON_CIRCLE = By.id("cricle-btn");
    private final By PERCENT_TEXT = By.xpath("//div[@class='percenttext']");

    public String startDownload()
    {
        navigate(url);

        WebElement progressBar = driver.findElement(NAVBAR).findElement(By.xpath("./li[1]"));
        progressBar.click();
        WebElement downloadPage = progressBar.findElements(By.xpath("./ul/li")).get(1);
        downloadPage.click();
        driver.findElement(BUTTON_CIRCLE).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.textToBe(PERCENT_TEXT, "100%"));

        return driver.findElement(PERCENT_TEXT).getText();
    }

    //TODO Wait for text to appear

    private final By BUTTON_DOWNLOAD = By.xpath("//button[text()='Start Download']");
    private final By TEXT_COMPLETE = By.xpath("//div[@class=\"progress-label\"]");
    public String waitDownload()
    {
        navigate(url);
        WebElement progressBar = driver.findElement(NAVBAR).findElement(By.xpath("./li[1]"));
        progressBar.click();
        progressBar.findElements(By.xpath("./ul/li")).get(0).click();
        driver.findElement(BUTTON_DOWNLOAD).click();
        Boolean wait = new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.textToBe(TEXT_COMPLETE, "Complete!"));
        return driver.findElement(TEXT_COMPLETE).getAttribute("innerHTML");
    }

}
