import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.RemoteWebDriverBuilder;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.io.File;
import java.util.Map;

public class SeleniumAndroidBrowserTestBase {
    protected WebDriver driver;

    @BeforeTest
    @Parameters({"deviceID", "browser"})
    public void setUp(String deviceID, String browser) {
        ChromeOptions chromeOptions = new ChromeOptions();
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        System.setProperty("webdriver.chrome.logfile", "C:\\Users\\admin\\Documents\\chromedriver.log");
        System.setProperty("webdriver.chrome.verboseLogging", "true");
        switch(browser){
            case "chrome":
                WebDriverManager.chromedriver().setup();
                chromeOptions.setExperimentalOption("androidPackage", "com.android.chrome");
                chromeOptions.setExperimentalOption("androidDeviceSerial", deviceID);
                driver = new ChromeDriver(chromeOptions);
                break;
            case "samsung":
                System.setProperty("webdriver.chrome.driver","C:\\Users\\admin\\Downloads\\chromedriver_win32\\chromedriver.exe");
                //this needs to be set to the path of the downloaded correct version of the chromedriver
                chromeOptions.setExperimentalOption("androidPackage", "com.sec.android.app.sbrowser");
                chromeOptions.setExperimentalOption("androidActivity", "com.sec.android.app.sbrowser.SBrowserMainActivity");
                chromeOptions.setExperimentalOption("androidDeviceSocket", "Terrace_devtools_remote");
                chromeOptions.setExperimentalOption("androidExecName", "Terrace");
                chromeOptions.setExperimentalOption("androidDeviceSerial", deviceID);
                driver = new ChromeDriver(chromeOptions);
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                firefoxOptions.setAndroidPackage("org.mozilla.firefox");
                firefoxOptions.setAndroidDeviceSerialNumber(deviceID);
                driver = new FirefoxDriver(firefoxOptions);
                break;
            case "duckduckgo": //not fully working yet :/
                WebDriverManager.chromedriver().setup();
                chromeOptions.setExperimentalOption("androidPackage", "com.duckduckgo.mobile.android");
                chromeOptions.setExperimentalOption("androidActivity", "com.duckduckgo.app.browser.BrowserActivity");
                chromeOptions.setExperimentalOption("androidDeviceSocket", "Terrace_devtools_remote");
                chromeOptions.setExperimentalOption("androidExecName", "Terrace");
                chromeOptions.setExperimentalOption("androidDeviceSerial", deviceID);
                driver = new ChromeDriver(chromeOptions);
                break;
            case "huawei": //not working
                WebDriverManager.chromedriver().setup();
                chromeOptions.setExperimentalOption("androidPackage", "com.huawei.browser");
                chromeOptions.setExperimentalOption("androidActivity", "com.huawei.browser.BrowserMainActivity");
                chromeOptions.setExperimentalOption("androidDeviceSocket", "chrome_devtools_remote");
                chromeOptions.setExperimentalOption("androidExecName", "chrome");
                chromeOptions.setExperimentalOption("androidDeviceSerial", deviceID);
                driver = new ChromeDriver(chromeOptions);
                break;
            case "mi":
                WebDriverManager.chromedriver().setup();
                chromeOptions.setExperimentalOption("androidPackage", "com.mi.globalbrowser");
                chromeOptions.setExperimentalOption("androidActivity", "com.mi.globalbrowser.Main");
//                chromeOptions.addArguments("--remote-debugging-port=9113");
                chromeOptions.setExperimentalOption("androidDeviceSocket", "miui_webview_devtools_remote_");
                chromeOptions.setExperimentalOption("androidExecName", "miui");
                chromeOptions.setExperimentalOption("androidDeviceSerial", deviceID);
                driver = new ChromeDriver(chromeOptions);
                break;
            case "uc":
                WebDriverManager.chromedriver().setup();
                chromeOptions.setExperimentalOption("androidPackage", "com.UCMobile.intl");
                chromeOptions.setExperimentalOption("androidActivity", "com.uc.browser.InnerUCMobile");
                chromeOptions.setExperimentalOption("androidDeviceSocket", "gs_1241_com.UCMobile.intl_633e8ff" +
                        "b_X9dNjiEoxNyttiZpQuUMg2m89F/8Jp1RYgXIiQSRwio=");
                chromeOptions.setExperimentalOption("androidExecName", "gs");
                chromeOptions.setExperimentalOption("androidDeviceSerial", deviceID);
                driver = new ChromeDriver(chromeOptions);
                break;
            case "opera":
                //WebDriverManager.chromedriver().setup();
                System.setProperty("webdriver.chrome.driver",
                        "C:\\Users\\admin\\Downloads\\chromedriver_win32 (2)\\chromedriver.exe");
                chromeOptions.setExperimentalOption("androidPackage", "com.opera.browser");
                chromeOptions.setExperimentalOption("androidActivity", "com.opera.android.BrowserActivity");
                chromeOptions.setExperimentalOption("androidDeviceSocket", "com.opera.browser.devtools");
                chromeOptions.setExperimentalOption("androidExecName", "opera");
                chromeOptions.setExperimentalOption("androidDeviceSerial", deviceID);
                chromeOptions.addArguments("--no-sandbox");
                driver = new ChromeDriver(chromeOptions);
        }
    }

    @AfterTest
    public void tearDown(){
        if(driver instanceof FirefoxDriver)
            driver.close();
        else
            driver.quit();
    }
}
