package auxiliar.web;

import auxiliar.LocalConfiguration;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

public class TestBase extends GeneralMethods {
    protected WebDriver driver;

    @BeforeMethod
    public void init(Object[] args){
        System.out.println(System.getProperty("os.name"));
        switch (System.getProperty("os.name")){
            case "Windows 10":
            case "Windows Server 2016":
                System.out.println("Windows");
                switch (args[0].toString()){
                    case "chrome":
                        driver = DriverCapabilities.localWindowsChrome();
                        break;
                    case "chromeExtension":
                        driver = DriverCapabilities.localWindowsChromeExtension();
                        break;
                    default:
                        driver = DriverCapabilities.localWindowsFirefox();
                        break;
                }
                break;
            case "Mac OS X":
                System.out.println("MacOS environment");
                switch (args[0].toString()){
                    case "chrome":
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
                    case "chrome":
                        driver = DriverCapabilities.localLinuxChrome();
                        break;
                    default: //Default browser Firefox
                        driver = DriverCapabilities.locaLinuxFirefox();
                        break;
                }
                break;
        }
    }

    @AfterMethod
    public void close(){
        driver.quit();
    }


    @DataProvider(name = "web")
    public  Object[][] allDrivers (ITestContext context){
        Object [][] driverOption;
        if(context.getCurrentXmlTest().getParameter("browserName")==null){
            driverOption = new Object[][]{{LocalConfiguration.web.navegador}};
        }else if(context.getCurrentXmlTest().getParameter("browserName").equals("random")){
            //lista con navegadores y coger uno random
            driverOption = new Object[][]{{"poner el random aca"}};
        }else{
            driverOption = new Object [][]{{context.getCurrentXmlTest().getParameter("browserName")}};
        }
        return driverOption;
    }
}
