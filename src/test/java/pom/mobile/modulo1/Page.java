package pom.mobile.modulo1;

import auxiliar.mobile.GeneralMethods;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

/**
 * @author Name
 * @Created: Date
 * @Modified: Name and date
 */

public class Page extends GeneralMethods {
    @AndroidFindBy(id = "search_box_text")
    @iOSXCUITFindBy(accessibility = "search_box_text")
    private MobileElement inputSearch;

    @AndroidFindBy(id = "url_bar")
    @iOSXCUITFindBy(accessibility = "url_bar")
    private MobileElement inputUrlBar;

    AppiumDriver<MobileElement> driver;

    public Page(AppiumDriver<MobileElement> driver){
        this.driver = driver;
        this.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
    }

    public boolean clickInputSearch(){
        return click(driver, inputSearch, 10);
    }

    public boolean sendKeyInputUrlBar(String text){
        return sendKeysNativeBoard(driver, inputUrlBar, text,10);
    }
}
