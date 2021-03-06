package auxiliar.web;

import auxiliar.LocalConfiguration;
import org.openqa.selenium.Dimension;
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
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

public class DriverCapabilities {

    public static void windowDimension(WebDriver driver){
        if(LocalConfiguration.web.habilitarPantallaModificable){
            driver.manage().window().setSize(new Dimension(LocalConfiguration.web.ancho, LocalConfiguration.web.alto));
        }else if(LocalConfiguration.web.habilitarPantallaModificableAleatoria){
            Random rand = new Random();
            int resolucionRandom=rand.nextInt(LocalConfiguration.web.resoluciones.length);
            int anchoAleatorio=LocalConfiguration.web.resoluciones[resolucionRandom][0];
            int altoAleatorio=LocalConfiguration.web.resoluciones[resolucionRandom][1];
            driver.manage().window().setSize(new Dimension(anchoAleatorio, altoAleatorio));
        }else{
            driver.manage().window().maximize();
        }
    }

    /* Linux */
    public static WebDriver locaLinuxFirefox() {
        System.setProperty("webdriver.gecko.driver", "drivers/Linux/Firefox/geckodriver");
        FirefoxOptions capabilities = new FirefoxOptions();
        capabilities.setCapability("marionette", true);
        FirefoxDriver driver = new FirefoxDriver(capabilities);
        windowDimension(driver);
        driver.manage().timeouts().pageLoadTimeout(500, TimeUnit.SECONDS);
        return driver;
    }

    public static WebDriver localLinuxChrome() {
        System.setProperty("webdriver.chrome.driver", "drivers/Linux/Chrome/chromedriver");
        ChromeDriver driver = new ChromeDriver();
        windowDimension(driver);
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
        windowDimension(driver);
        driver.manage().timeouts().pageLoadTimeout(500, TimeUnit.SECONDS);
        return driver;
    }

    public static WebDriver localWindowsChrome() {
        System.setProperty("webdriver.chrome.driver", "Drivers\\Windows\\Chrome\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        windowDimension(driver);
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
        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().pageLoadTimeout(500, TimeUnit.SECONDS);
        driver.manage().window().setSize(new Dimension(800, 600));
        return driver;
    }

    public static WebDriver localWindowsEdge() {
        System.setProperty("webdriver.edge.driver", "Drivers\\Windows\\Edge\\msedgedriver.exe");
        EdgeDriver driver = new EdgeDriver();
        windowDimension(driver);
        driver.manage().timeouts().pageLoadTimeout(500, TimeUnit.SECONDS);
        return driver;
    }

    /* macOS */
    public static WebDriver localMacOSChrome() {
        System.setProperty("webdriver.chrome.driver", "drivers/macOS/Chrome/chromedriver");
        ChromeDriver driver = new ChromeDriver();
        windowDimension(driver);
        driver.manage().timeouts().pageLoadTimeout(500, TimeUnit.SECONDS);
        return driver;
    }

    public static WebDriver localMacOSFirefox() {
        System.setProperty("webdriver.gecko.driver", "drivers/macOS/Firefox/geckodriver");
        FirefoxOptions capabilities = new FirefoxOptions();
        capabilities.setCapability("marionette", true);
        FirefoxDriver driver = new FirefoxDriver(capabilities);
        windowDimension(driver);
        driver.manage().timeouts().pageLoadTimeout(500, TimeUnit.SECONDS);
        return driver;
    }

    public static WebDriver localMacOSEdge() {
        System.setProperty("webdriver.edge.driver", "drivers/macOS/Edge/msedgedriver");
        EdgeDriver driver = new EdgeDriver();
        windowDimension(driver);
        driver.manage().timeouts().pageLoadTimeout(500, TimeUnit.SECONDS);
        return driver;
    }

    public static WebDriver localMacOSSafari() {
        SafariDriver driver = new SafariDriver();
        windowDimension(driver);
        driver.manage().timeouts().pageLoadTimeout(500, TimeUnit.SECONDS);
        return driver;
    }

}
