package test.web.modulo1;

import auxiliar.web.TestBase;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
/**
 * story: nombre story
 * ID story: numero
 *
**/
public class TestExtension extends TestBase {

    @Test(dataProvider = "web")
    public void loginTwitter(String browser) throws InterruptedException {
        driver.get("chrome-extension://pgjjikdiikihdfpoppgaidccahalehjh/index.html");
        WebDriverWait wait = new WebDriverWait(driver,5);
        encontrarElementoXpath("//span[text()='INICIO']",driver,wait).click();
        Thread.sleep(2000);
        WebDriverWait waitAnalisis = new WebDriverWait(driver,200);
        if(existeXpath("//span[text()='INICIO']",waitAnalisis)){
            String subida=encontrarElementoXpath("//span[text()='SUBIDA']/../following-sibling::div[1]/span[1]",driver,wait).getText();
            System.out.println(subida);
        }
    }

    @AfterMethod
    public void cerrarDriver(){
        driver.quit();
    }
}

