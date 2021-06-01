package pom.web.kobiton;

import auxiliar.web.GeneralMethods;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginKobiton extends GeneralMethods{

	WebDriver driver;
	WebDriverWait wait;
	AjaxElementLocatorFactory factory;

	@FindBy(xpath = "//input[@name='emailOrUsername']")
	WebElement txtEmailorusername;
	@FindBy(xpath = "//input[@name='password']")
	WebElement txtPassword;
	@FindBy(xpath = "//button[text()='login']")
	WebElement buttonLogin;

	public LoginKobiton(WebDriver driver){
		this.driver = driver;
		wait = new WebDriverWait(driver,5);
		factory = new AjaxElementLocatorFactory(this.driver, 5);
		PageFactory.initElements(factory, this);
		}

	public void sendEmailorusername(String emailorusername){
		sendText(txtEmailorusername,emailorusername,driver,wait);
	}
	public void sendPassword(String password){
		sendText(txtPassword,password,driver,wait);
	}
	public void clickLogin(){
		click(buttonLogin,driver,wait);
	}

}
