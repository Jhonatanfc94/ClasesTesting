package test.web.modulo1;

import auxiliar.web.TestBase;
import org.testng.annotations.Test;

public class TestExtension extends TestBase {

    @Test(dataProvider = "web")
    public void loginTwitter(String browser) throws InterruptedException {
        driver.get("chrome-extension://pgjjikdiikihdfpoppgaidccahalehjh/index.html");
        Thread.sleep(2000);
    }
}

