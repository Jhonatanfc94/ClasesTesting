package pom.web.phptravels;

import auxiliar.web.GeneralMethods;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Login extends GeneralMethods {

    WebDriver driver;
    WebDriverWait wait;
    AjaxElementLocatorFactory factory;

    @FindBy(xpath = "//input[@name='username']")
    WebElement txtUserName;
    @FindBy(xpath = "//input[@name='password']")
    WebElement txtPassword;
    @FindBy(xpath = "//button[@class='btn btn-primary btn-lg btn-block loginbtn']")
    WebElement buttonLogin;
    @FindBy(xpath = "//h3[contains(text(),'Hi')]")
    WebElement messageHi;



    public Login(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver,5);
        factory = new AjaxElementLocatorFactory(this.driver, 5);
        PageFactory.initElements(factory, this);
    }

    public void sendUserName(String user){
        sendText(txtUserName,user,driver,wait);
    }
    public void sendPassword(String pass){
        sendText(txtPassword,pass,driver,wait);
    }
    public void clickButtonLogin(){
        click(buttonLogin,driver,wait);
    }
    public boolean getHiMessage(){
        boolean existHiMessage=false;
        try{
            wait.until(ExpectedConditions.elementToBeClickable(messageHi));
            existHiMessage= true;
        }catch(Exception e){}
        return existHiMessage;
    }
}
