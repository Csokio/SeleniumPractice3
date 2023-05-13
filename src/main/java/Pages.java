import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;

public abstract class Pages {

    protected static WebDriver driver;

    public Pages() {
        if (driver == null) {
            WebDriverManager.chromedriver().setup();
            //System.setProperty("webdriver.chrome.driver", "chromedriver");

            ChromeOptions options = new ChromeOptions();
            options.setAcceptInsecureCerts(true);
            options.addArguments("ignore-certificate-errors");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--disable-notifications");
            options.addArguments("--disable-extensions");
            options.addArguments("--headless");
            options.addArguments("--window-size=1920,1080");
            options.addArguments("start-maximized");
            options.addArguments("--remote-allow-origins=*");
            driver = new ChromeDriver(options);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        }
    }

    public static void closeDriver()
    {
        if(driver != null){
            driver.close();
            driver = null;
        }
    }

    public void executeJavaScript(String functionName)
    {
        ((JavascriptExecutor) driver).executeScript(functionName);
    }

    protected void scrollIntoView(By xpath)
    {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView", driver.findElement(xpath));
    }
            //"window.scrollTo[0]"

    protected void waitMethod(By xpath)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.presenceOfElementLocated(xpath));
    }

    protected void navigate(String url)
    {
        driver.navigate().to(url);
    }

    public static String[] readFile(String fileName)
    {
        List<String> resultList = new ArrayList<>();

        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()){
                resultList.add(scanner.nextLine());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return resultList.toArray(new String[0]);
    }

    public static void writeFile(HashMap<String, String> map, String fileName)
    {
        try {
            File file = new File(fileName);
            FileWriter fileWriter = new FileWriter(file);

            List<Map.Entry<String, String>> entryList = new ArrayList<>(map.entrySet());
            Collections.reverse(entryList);

            int count = entryList.size();
            for (Map.Entry<String, String> entry : entryList) {
                fileWriter.write(entry.getKey() + ":" + entry.getValue());

            /*for (int i = 0; i < count; i++) {
                Map.Entry<String, String> entry = entryList.get(i);
                fileWriter.write(entry.getKey() + ":" + entry.getValue());

                // Az utolsó elem után ne szúrjon be új sort
                if (i != count - 1) {
                    fileWriter.write('\n');
                }*/
            }

            fileWriter.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


}
