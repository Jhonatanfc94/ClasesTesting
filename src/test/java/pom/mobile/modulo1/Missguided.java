package pom.mobile.modulo1;

import auxiliar.mobile.GeneralMethods;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Missguided extends GeneralMethods {
    @AndroidFindBy(xpath = "//*[@resource-id='com.poqstudio.app.platform.missguided:id/navigation_item_two']/*[@resource-id='com.poqstudio.app.platform.missguided:id/icon']")
    @iOSXCUITFindBy(accessibility = "")
    private MobileElement menuShop;

    @AndroidFindBy(xpath = "//*[@text='Summer outfits']")
    @iOSXCUITFindBy(accessibility = "")
    private MobileElement summerOutfits;

    AppiumDriver<MobileElement> driver;

    public Missguided(AppiumDriver<MobileElement> driver){
        this.driver = driver;
        this.driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        PageFactory.initElements(new AppiumFieldDecorator(this.driver), this);
    }

    public boolean clickMenuShop(){
        try {
            new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOf(menuShop));
            new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(menuShop));
            menuShop.click();
            return true;
        } catch (Exception Error) {
            Error.printStackTrace();
            return false;
        }
    }
    public boolean clickSummerOutfits(){
        try {
            new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOf(summerOutfits));
            new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(summerOutfits));
            summerOutfits.click();
            return true;
        } catch (Exception Error) {
            Error.printStackTrace();
            return false;
        }
    }
}
