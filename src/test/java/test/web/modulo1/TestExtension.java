package test.web.modulo1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;

public class TestExtension {
    WebDriver driver;

    @BeforeMethod
    public void configurarDriver(){
        System.setProperty("webdriver.chrome.driver","drivers/Windows/Chrome/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        //File file = new File("extensions/chrome/extension_2_0_0_0.crx");
        File file = new File("extensions/chrome/speedTest.crx");
        options.addExtensions(file);
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        options.merge(capabilities);
        driver = new ChromeDriver(options);
    }

    @Test
    public void loginTwitter() throws InterruptedException {
        String urlTwitter = "https://twitter.com/login";
        driver.get("chrome-extension://pgjjikdiikihdfpoppgaidccahalehjh/index.html");
        //chrome-extension://pgjjikdiikihdfpoppgaidccahalehjh/scripts/main.css.map
        Thread.sleep(2000);
    }

    @AfterMethod
    public void cerrarDriver(){
        driver.quit();
    }
}

