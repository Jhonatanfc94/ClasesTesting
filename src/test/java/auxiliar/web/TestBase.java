package auxiliar.web;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TestBase extends GeneralMethods {
    protected WebDriver driver;
    protected WebDriver getDriver(){
        return driver;
    }

    @BeforeMethod
    public void init(Object[] args){
        System.out.println(System.getProperty("os.name"));
        switch (System.getProperty("os.name")){
            case "Windows 10":
            case "Windows Server 2016":
                System.out.println("Windows Environment 2016");
                switch (args[0].toString()){
                    case "Chrome":
                        driver = DriverCapabilities.localWindowsChrome();
                        break;
                    default:
                        driver = DriverCapabilities.localWindowsFirefox();
                        break;
                }
                break;
            case "Mac OS X":
                System.out.println("MacOS environment");
                switch (args[0].toString()){
                    case "Chrome":
                        driver = DriverCapabilities.localMacOSChrome();
                        break;
                    default:
                        driver = DriverCapabilities.localMacOSFirefox();
                        break;
                }
                break;
            default:
                System.out.println("Unix/Linux settings");
                switch (args[0].toString()){
                    case "Chrome":
                        driver = DriverCapabilities.localLinuxChrome();
                        break;
                    default: //Default browser Firefox
                        driver = DriverCapabilities.locaLinuxFirefox();
                        break;
                }
                break;
        }
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void close(){
        driver.quit();
    }

}
