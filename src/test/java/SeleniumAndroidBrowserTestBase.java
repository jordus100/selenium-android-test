import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public class SeleniumAndroidBrowserTestBase {

    protected ChromeDriver driver;

    @BeforeSuite
    public void setUpDriver(){
        WebDriverManager.chromedriver().setup();
    }
    @BeforeTest
    @Parameters("deviceID")
    public void setUp(String deviceID) {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("androidPackage", "com.android.chrome");
        chromeOptions.setExperimentalOption("androidDeviceSerial", deviceID);
        driver = new ChromeDriver(chromeOptions);
    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }
}
