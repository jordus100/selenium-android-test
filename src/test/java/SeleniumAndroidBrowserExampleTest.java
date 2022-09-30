import org.testng.annotations.Test;

public class SeleniumAndroidBrowserExampleTest extends SeleniumAndroidBrowserTestBase{

    @Test
    public void testWebsiteOpen(){
        driver.get("https://google.com");
    }

}
