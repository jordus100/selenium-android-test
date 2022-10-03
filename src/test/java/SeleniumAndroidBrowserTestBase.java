import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.io.TemporaryFilesystem;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public class SeleniumAndroidBrowserTestBase {
    protected ChromeDriver driver;

    @BeforeTest
    @Parameters({"deviceID", "browser"})
    public void setUp(String deviceID, String browser) {
        ChromeOptions chromeOptions = new ChromeOptions();
        switch(browser){
            case "chrome":
                WebDriverManager.chromedriver().setup();
                chromeOptions.setExperimentalOption("androidPackage", "com.android.chrome");
                break;
            case "samsung":
                System.setProperty("webdriver.chrome.driver","C:\\Users\\admin\\Downloads\\chromedriver_win32\\chromedriver.exe");
                //this needs to be set to the path of the downloaded correct version of the chromedriver
                chromeOptions.setExperimentalOption("androidPackage", "com.sec.android.app.sbrowser");
                chromeOptions.setExperimentalOption("androidActivity", "com.sec.android.app.sbrowser.SBrowserMainActivity");
                chromeOptions.setExperimentalOption("androidDeviceSocket", "Terrace_devtools_remote");
                chromeOptions.setExperimentalOption("androidExecName", "Terrace");
                break;
        }
        chromeOptions.setExperimentalOption("androidDeviceSerial", deviceID);
        driver = new ChromeDriver(chromeOptions);
    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }
}
