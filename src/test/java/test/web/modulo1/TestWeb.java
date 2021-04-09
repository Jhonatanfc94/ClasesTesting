package test.web.modulo1;

import auxiliar.web.TestBase;
import org.testng.annotations.Test;

public class TestWeb extends TestBase {

    @Test(dataProvider = "web")
    public void loginTwitter(String browser) throws InterruptedException {
        String urlTwitter = "https://twitter.com/login";
        driver.get(urlTwitter);
        Thread.sleep(2000);
    }
}
