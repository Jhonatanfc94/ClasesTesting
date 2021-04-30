package test.web.modulo1;

import auxiliar.web.TestBase;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
//https://clasestesting.atlassian.net/browse/CLAS-1?atlOrigin=eyJpIjoiZGFjZGU1OTVlYTA0NDNkMmEyNjI3NzY4YjgyNGJlZDkiLCJwIjoiaiJ9
public class TestWeb extends TestBase {

    @Test(dataProvider = "web")
    public void loginTwitter(String browser) throws InterruptedException {
        String urlTwitter = "https://twitter.com/login";
        driver.get(urlTwitter);
        driver.findElement(By.xpath("hola"));
        Thread.sleep(2000);
    }
}
