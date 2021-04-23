package auxiliar.mobile;

import auxiliar.LocalConfiguration;
import org.openqa.selenium.remote.DesiredCapabilities;

public class DriverCapabilities {

    public static DesiredCapabilities android(){
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("deviceName", LocalConfiguration.mobileCapabilities.deviceName);
        desiredCapabilities.setCapability("udid", LocalConfiguration.mobileCapabilities.udId);
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("platformVersion", LocalConfiguration.mobileCapabilities.platformVersion);
        desiredCapabilities.setCapability("appPackage", LocalConfiguration.mobileCapabilities.appPackage);
        desiredCapabilities.setCapability("appActivity", LocalConfiguration.mobileCapabilities.appActivity);
        desiredCapabilities.setCapability("autoGrantPermissions", true);
        desiredCapabilities.setCapability("noReset", "true");
        return desiredCapabilities;
    }

    public static DesiredCapabilities iOS(){
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("deviceName", LocalConfiguration.mobileCapabilities.deviceName);
        desiredCapabilities.setCapability("udid", LocalConfiguration.mobileCapabilities.udId);
        desiredCapabilities.setCapability("platformName", "IOS");
        desiredCapabilities.setCapability("platformVersion", LocalConfiguration.mobileCapabilities.platformVersion);
        desiredCapabilities.setCapability("bundleId", LocalConfiguration.mobileCapabilities.bundleId);
        desiredCapabilities.setCapability("xcodeOrgId)", LocalConfiguration.mobileCapabilities.xcodeOrgId);
        desiredCapabilities.setCapability("xcodeSigningId", "iPhone Developer");
        desiredCapabilities.setCapability("automationName", "XCUITest");
        return desiredCapabilities;
    }
}
