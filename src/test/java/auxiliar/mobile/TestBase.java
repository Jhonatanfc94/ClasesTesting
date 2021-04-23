package auxiliar.mobile;

import auxiliar.LocalConfiguration;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

public class TestBase{
    public AppiumDriver<MobileElement> driver;
    public AppiumDriverLocalService server;
    AppiumServiceBuilder serverBuilder;
    DesiredCapabilities desiredCapabilities;

    @BeforeClass
    public void setUp() {
        serverBuilder = new AppiumServiceBuilder();
        serverBuilder.usingAnyFreePort();
        serverBuilder.withArgument(GeneralServerFlag.LOG_LEVEL,"error");
        server = AppiumDriverLocalService.buildService(serverBuilder);
        server.start();
        switch (LocalConfiguration.mobileCapabilities.platformName){
            case "iOS": desiredCapabilities = DriverCapabilities.iOS();
            default: desiredCapabilities = DriverCapabilities.android();
        }
        driver = new AppiumDriver<>(server.getUrl(), desiredCapabilities);
        driver.manage().timeouts().implicitlyWait(30L, TimeUnit.SECONDS);
    }

    @AfterClass
    public void closeApp(){
        driver.closeApp();
        server.stop();
    }
}
