package pom.web.phptravels;

import auxiliar.web.GeneralMethods;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DropDownMenu extends GeneralMethods {

    WebDriver driver;
    WebDriverWait wait;
    AjaxElementLocatorFactory factory;

    @FindBy(xpath = "//*[@class='dropdown dropdown-login dropdown-tab']//*[@id='dropdownCurrency']")
    WebElement dropDownMenu;

    public DropDownMenu(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver,10);
        factory = new AjaxElementLocatorFactory(this.driver, 10);
        PageFactory.initElements(factory, this);
    }

    public void clickDropDownMenu(){
        click(dropDownMenu,driver,wait);
    }

    public void clickOptionDropDownMenu(String option){
        WebElement optionLink = encontrarElementoXpath("//a[text()='"+option+"']",driver,wait);
        click(optionLink,driver,wait);
    }
}
