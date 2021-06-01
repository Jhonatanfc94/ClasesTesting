package test.mobile.modulo1;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pom.mobile.modulo1.Missguided;

import java.net.MalformedURLException;

public class AddItemBag{
    Missguided missguided;
    AppiumDriver driver;

    @BeforeClass
    public void setUp() throws MalformedURLException {
        AppiumServiceBuilder serverBuilder = new AppiumServiceBuilder();
        serverBuilder.usingAnyFreePort();
        serverBuilder.withArgument(GeneralServerFlag.LOG_LEVEL,"error");
        AppiumDriverLocalService server = AppiumDriverLocalService.buildService(serverBuilder);
        server.start();

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "Mi A2 Lite");
        capabilities.setCapability("udid","73a6a2480405");//adb devices
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("platformVersion", "10");

        capabilities.setCapability("appPackage", "com.poqstudio.app.platform.missguided");
        //adb shell "dumpsys activity activities | grep mResumedActivity"
        //mResumedActivity: ActivityRecord{73d0dbe u0 com.poqstudio.app.platform.missguided/com.poqstudio.app.platform.presentation.main.view.BottomNavigationActivity t289}

        capabilities.setCapability("appActivity","com.poqstudio.app.platform.presentation.splash.view.SplashActivity");

        capabilities.setCapability("autoGrantPermissions", true);
        capabilities.setCapability("noReset", "true");
        driver = new AppiumDriver<MobileElement>(server.getUrl(), capabilities);
    }
    @BeforeMethod
    public void startPom() throws InterruptedException {
        missguided = new Missguided(driver);
    }
    @Test
    public void testSummerOutFitCategory() throws InterruptedException {
        missguided.clickMenuShop();
        Assert.assertTrue(missguided.clickSummerOutfits());

        long miliSeconds = (long)10 *1000;
        try{
            Thread.sleep(miliSeconds);
        }catch(Exception Error){
            Error.printStackTrace();
        }
    }

    @AfterClass
    public void teardown(){
        driver.quit();
    }
}
