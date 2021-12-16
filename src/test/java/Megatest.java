import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.$x;

abstract public class Megatest {
    private final SelenideElement linkPersonalRoom = $x("//input[@class='form']");
    private final SelenideElement pressBtnSearch = $x("//input[@class='go']");
    private final static String BASE_URL = "http://xn----8sbalhqhcqniie4b.xn--p1ai/";


    public void setUp() {
        WebDriverManager.chromedriver().setup();
        Configuration.browser = "chrome";
        Configuration.driverManagerEnabled = true;
        Configuration.browserSize = "1920x1080";
        Configuration.headless = false;
    }


    @Before
    public void init(){
        setUp();
    }

    @After
    public void tearDown(){
        Selenide.closeWebDriver();
    }
    @Test
    public void SignInIsError() {

        //SignInPage page = new GlavStr(BASE_URL).signIn();


    }




}