import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
//import org.testng.annotations.Test;

//import Pages;


public class BaseTest {


    @AfterAll
    public static void quitDriver()
    {
        Pages.closeDriver();
    }



}
