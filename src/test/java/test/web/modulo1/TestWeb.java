package test.web.modulo1;

import auxiliar.web.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import pom.mobile.modulo1.Page;
import pom.web.modulo1.Login;

public class TestWeb extends TestBase {

    @Test(dataProvider = "web", description = "164932376 : True : NA")
    public void loginTwitter(String browser) throws InterruptedException {
        String urlTwitter = "https://twitter.com/paginaLogin";
        driver.get(urlTwitter);

        Login paginaLogin = new Login(driver);// opcion tanto A como B

        WebDriverWait wait = new WebDriverWait(driver,3);// opcion A
        click(paginaLogin.botonLogin,driver,wait);// opcion A

        paginaLogin.clickBotonLogin();// opcion B OJO el método fue definido en el POM

        // puede ser ambos criterio de cada quien: opcion C

        Thread.sleep(2000);
    }
}
