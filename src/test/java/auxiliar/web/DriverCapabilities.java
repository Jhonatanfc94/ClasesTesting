package auxiliar.web;

import auxiliar.LocalConfiguration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxDriverLogLevel;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;

import java.io.File;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

public class DriverCapabilities {

    /* Linux */
    public static WebDriver locaLinuxFirefox() {
        System.setProperty("webdriver.gecko.driver", "drivers/Linux/Firefox/geckodriver");
        FirefoxOptions capabilities = new FirefoxOptions();
        capabilities.setCapability("marionette", true);
        FirefoxDriver driver = new FirefoxDriver(capabilities);
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(500, TimeUnit.SECONDS);
        return driver;
    }

    public static WebDriver localLinuxChrome() {
        System.setProperty("webdriver.chrome.driver", "drivers/Linux/Chrome/chromedriver");
        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(500, TimeUnit.SECONDS);
        return driver;
    }

    /* Windows */
    public static WebDriver localWindowsFirefox() {
        System.setProperty("webdriver.gecko.driver", "Drivers\\Windows\\Firefox\\geckodriver.exe");
        FirefoxOptions capabilities = new FirefoxOptions();
        capabilities.setCapability("marionette", true);
        FirefoxProfile firefoxProfile = new FirefoxProfile();
        capabilities.setCapability(FirefoxDriver.PROFILE, firefoxProfile);
        FirefoxOptions options = new FirefoxOptions();
        options.merge(capabilities);
        options.setLogLevel(FirefoxDriverLogLevel.fromLevel(Level.OFF));
        WebDriver driver = new FirefoxDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(500, TimeUnit.SECONDS);
        return driver;
    }

    public static WebDriver localWindowsChrome() {
        System.setProperty("webdriver.chrome.driver", "Drivers\\Windows\\Chrome\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(500, TimeUnit.SECONDS);
        return driver;
    }
    public static WebDriver localWindowsChromeExtension() {
        System.setProperty("webdriver.chrome.driver","drivers/Windows/Chrome/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        File file = new File("extensions/chrome/"+ LocalConfiguration.web.extension +".crx");
        options.addExtensions(file);
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        options.merge(capabilities);
        ChromeDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(500, TimeUnit.SECONDS);
        return driver;
    }

    public static WebDriver localWindowsEdge() {
        System.setProperty("webdriver.edge.driver", "Drivers\\Windows\\Edge\\msedgedriver.exe");
        EdgeDriver driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(500, TimeUnit.SECONDS);
        return driver;
    }

    /* macOS */
    public static WebDriver localMacOSChrome() {
        System.setProperty("webdriver.chrome.driver", "drivers/macOS/Chrome/chromedriver");
        ChromeDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(500, TimeUnit.SECONDS);
        return driver;
    }

    public static WebDriver localMacOSFirefox() {
        System.setProperty("webdriver.gecko.driver", "drivers/macOS/Firefox/geckodriver");
        FirefoxOptions capabilities = new FirefoxOptions();
        capabilities.setCapability("marionette", true);
        FirefoxDriver driver = new FirefoxDriver(capabilities);
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(500, TimeUnit.SECONDS);
        return driver;
    }

    public static WebDriver localMacOSEdge() {
        System.setProperty("webdriver.edge.driver", "drivers/macOS/Edge/msedgedriver");
        EdgeDriver driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(500, TimeUnit.SECONDS);
        return driver;
    }

    public static WebDriver localMacOSSafari() {
        SafariDriver driver = new SafariDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(500, TimeUnit.SECONDS);
        return driver;
    }

}
