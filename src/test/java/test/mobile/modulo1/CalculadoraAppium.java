package test.mobile.modulo1;

import java.net.MalformedURLException;
import java.net.URL;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

public class CalculadoraAppium {
    AppiumDriver driver;

    @BeforeClass
    public void setUp() throws MalformedURLException{
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "Mi A2 Lite");
        capabilities.setCapability("udid","73a6a2480405");//adb devices
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("platformVersion", "10");

        capabilities.setCapability("appPackage", "com.google.android.calculator");
        //adb devices
        //adb shell dumpsys  activity
        //adb shell "dumpsys activity activities | grep mResumedActivity"
        //mResumedActivity: ActivityRecord{73d0dbe u0 com.poqstudio.app.platform.missguided/com.poqstudio.app.platform.presentation.main.view.BottomNavigationActivity t289}

        capabilities.setCapability("appActivity","com.android.calculator2.Calculator");
        URL url = new URL("http://localhost:4723/wd/hub");
        driver = new AppiumDriver<MobileElement>(url, capabilities);
    }

    @Test
    public void testCal() throws InterruptedException {
        Thread.sleep(5000);
    }

    @AfterClass
    public void teardown(){
        driver.quit();
    }
}
