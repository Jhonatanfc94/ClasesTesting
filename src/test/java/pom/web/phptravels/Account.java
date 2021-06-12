package pom.web.phptravels;

import auxiliar.web.GeneralMethods;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Account extends GeneralMethods {
    WebDriver driver;
    WebDriverWait wait;
    AjaxElementLocatorFactory factory;

    @FindBy(xpath = "//*[@class='dropdown dropdown-login dropdown-tab']//*[@id='dropdownCurrency']")
    WebElement dropDownMenu;
    //reservation
    @FindBy(xpath = "//*[@id='bookings']/div//i[contains(@class,'rating')]/../following-sibling::a/b")
    WebElement nameHotel;
    @FindBy(xpath = "//*[@id='bookings']/div//i[contains(@class,'rating')]/../following-sibling::*//*[contains(text(),'USD')]/..")
    WebElement price;
    @FindBy(xpath = "//*[@id='bookings']/div//*[contains(text(),'Booking ID')]/..")
    WebElement bookingInformation;
    //side menu
    @FindBy(xpath = "//a[text()='My Profile']")
    WebElement myProfile;
    //my profile
    @FindBy(xpath = "//input[@name='firstname']")
    WebElement txtfirstname;
    @FindBy(xpath = "//input[@name='lastname']")
    WebElement txtlastname;
    @FindBy(xpath = "//input[@name='Email']")
    WebElement txtEmail;
    @FindBy(xpath = "//input[@name='address1']")
    WebElement txtaddress1;
    @FindBy(xpath = "//input[@name='address2']")
    WebElement txtaddress2;
    @FindBy(xpath = "//input[@name='city']")
    WebElement txtcity;
    @FindBy(xpath = "//input[@name='phone']")
    WebElement txtphone;


    public Account(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver,5);
        factory = new AjaxElementLocatorFactory(this.driver, 5);
        PageFactory.initElements(factory, this);
    }

    public void clickDropDownMenu(){
        click(dropDownMenu,driver,wait);
    }
    //Bookings
    public String getNameHotel(){
        return getTextoDeElemento(nameHotel,driver,wait);
    }
    public String getPrice(){
        return getTextoDeElemento(price,driver,wait);
    }
    public String getBookingInformation(){
        return getTextoDeElemento(bookingInformation,driver,wait);
    }
    //My profile
    public void clickMyProfile(){
        click(myProfile,driver,wait);
    }
    public String getFirstName(){
        return getAttribute(txtfirstname,"value",driver,wait);
    }
    public String getLastname(){
        return getAttribute(txtlastname,"value",driver,wait);
    }
    public String getPhone(){
        return getAttribute(txtphone,"value",driver,wait);
    }

}
