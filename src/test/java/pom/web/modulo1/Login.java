package pom.web.modulo1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Login {
    WebDriver driver;
    WebDriverWait wait;
    AjaxElementLocatorFactory ajaxElementLocatorFactory;

    @FindBy(xpath="//input[@name='session[username_or_email]']")
    public WebElement txtUsuario;
    @FindBy(xpath="//input[@name='session[password]']")
    public WebElement txtPassword;
    @FindBy(xpath="//span[text()='Log in']")
    public WebElement botonLogin;

    public Login(WebDriver driver){
        this.driver=driver;
        wait = new WebDriverWait(driver,5);
        ajaxElementLocatorFactory = new AjaxElementLocatorFactory(driver,8);
        PageFactory.initElements(ajaxElementLocatorFactory,this);
    }

    public void ingresarUsuario(String usuario){
        wait.until(ExpectedConditions.visibilityOf(txtUsuario)).sendKeys(usuario);
    }

    public void ingresarPassword(String pass){
        wait.until(ExpectedConditions.visibilityOf(txtPassword)).sendKeys(pass);
    }

    public void clickBotonLogin(){
        wait.until(ExpectedConditions.visibilityOf(botonLogin)).click();
    }
}
