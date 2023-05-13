import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

//import org.testng.annotations.Test;

import java.util.Arrays;


public class TableTest extends BaseTest {

    TableSearchPage tableSearchPage = new TableSearchPage();

    @Test
    public void testCountOfRows()
    {
        int actual = tableSearchPage.countRows("in progress");

        Assertions.assertEquals(3, actual);
    }

    @Test
    public void testIdEqualsFile()
    {
        Integer[] actual = tableSearchPage.tableIds();

        String[] array = Pages.readFile("table_data.txt");
        String[] arraySplit = array[0].split(",");

        Integer[] expected = new Integer[arraySplit.length];

        for(int i = 0; i < arraySplit.length; i++){
            expected[i] = Integer.parseInt(arraySplit[i].trim());
        }

        Assertions.assertArrayEquals(expected, actual);
    }
}
