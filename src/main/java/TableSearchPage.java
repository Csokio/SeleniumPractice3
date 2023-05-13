import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class TableSearchPage extends Pages {


    public TableSearchPage()
    {
        super();
    }

    private final String url = "https://demo.seleniumeasy.com/table-search-filter-demo.html";

    //TODO Count status with i progress in table

    private final By FIELD_FILTER = By.id("task-table-filter");

    private final By TABLE_TASK = By.id("task-table");

    public int countRows(String status)
    {
        navigate(url);
        driver.findElement(FIELD_FILTER).sendKeys(status);
        List<WebElement> tableRows = driver.findElement(TABLE_TASK).findElements(By.xpath("./tbody/tr"));

        List<WebElement> resultList = new ArrayList<>();

        for(WebElement row: tableRows)
        {
            String style = row.getAttribute("style");
            if(!style.equals("display: none;")){
                resultList.add(row);
            }
        }

        return resultList.size();
    }

    //TODO Verify Numbers In Table

    private final By TABLE_ROWS = By.xpath("//table[@class=\"table\"]/tbody/tr");

    public Integer[] tableIds()
    {
        navigate(url);
        scrollIntoView(TABLE_ROWS);
        waitMethod(TABLE_ROWS);

        List<WebElement> tableRows = driver.findElements(TABLE_ROWS);
        Integer[] resultArray = new Integer[tableRows.size()];

        int count = 0;
        for(WebElement row : tableRows){
            resultArray[count++] = Integer.parseInt(row.findElement(By.xpath("./td[1]")).getText());
        }

        return resultArray;
    }
}
