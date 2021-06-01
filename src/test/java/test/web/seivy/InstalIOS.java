package test.web.seivy;

import auxiliar.web.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import pom.web.kobiton.LoginKobiton;

public class InstalIOS extends TestBase {

    @Test(dataProvider = "web", description = "intala seivy IOS : True : NA")
    public void instalarIOS(String browser) {
        String user="jflores@cognits.co";
        String pass="hermandadT1";
        loadUrl(driver,"https://portal.kobiton.com/devices");
        LoginKobiton loginKobiton = new LoginKobiton(driver);
        loginKobiton.sendEmailorusername(user);
        loginKobiton.sendPassword(pass);
        loginKobiton.clickLogin();
        waitSeconds(8);
        WebDriverWait wait = new WebDriverWait(driver,5);
        WebElement launch= driver.findElement(By.xpath("//button[text()='Launch']/ancestor::div[contains(@class,'views__DeviceInfo-dWqvfH gLzUwe')]//*[text()='iPhone 6s']/..//button[text()='Launch' and not(@disabled='')]/.."));
        String url=getAttributeNoVisible(launch,"href",driver);
        loadUrl(driver,url);
        waitSeconds(20);
        encontrarElementoXpath("//*[@id='ita-guardrail-close-button']",driver,wait).click();
        encontrarElementoXpath("//*[text()='Install Apps']",driver,wait).click();
        System.out.println("Pausa");
    }

}
