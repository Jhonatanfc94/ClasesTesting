package pom.web.kobiton;

import auxiliar.web.GeneralMethods;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginSeivyWeb extends GeneralMethods{

	WebDriver driver;
	WebDriverWait wait;
	AjaxElementLocatorFactory factory;

	@FindBy(xpath = "//input[@id='frmLogin_main_tbxUserName']")
	WebElement txtEmailorusername;
	@FindBy(xpath = "//input[@id='frmLogin_main_tbxPassword']")
	WebElement txtPassword;
	@FindBy(xpath = "//input[@id='frmLogin_main_btnLogin']")
	WebElement buttonLogin;
	@FindBy(xpath = "//input[@id='frmLogin_btnRegisterYes']")
	WebElement buttonVincularAhora;
	@FindBy(xpath = "//input[@id='frmLogin_btnContactUs']")
	WebElement linkContactanos;

	@FindBy(xpath = "//div[text()='Productos']")
	WebElement menuProductos;

	public LoginSeivyWeb(WebDriver driver){
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
	public void clickVincularAhora(){
		click(buttonVincularAhora,driver,wait);
	}

	public void clickLinkContactanos(){
		click(linkContactanos,driver,wait);
	}
	public void clickMenuProductos(){
		click(menuProductos,driver,wait);
	}
}
