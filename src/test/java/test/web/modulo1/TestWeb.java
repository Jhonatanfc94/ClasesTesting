package test.web.modulo1;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestWeb {
    ChromeDriver driver;

    @BeforeMethod
    public void configurarDriver(){
        System.setProperty("webdriver.chrome.driver","drivers/Windows/Chrome/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void loginTwitter() throws InterruptedException {
        String urlTwitter = "https://twitter.com/login";
        driver.get(urlTwitter);
        Thread.sleep(2000);
    }

    @AfterMethod
    public void cerrarDriver(){
        driver.quit();
    }
}
