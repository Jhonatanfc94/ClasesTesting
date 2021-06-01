package test.web.seivy;

import auxiliar.LocalConfiguration;
import auxiliar.web.TestBase;
import org.openqa.selenium.Dimension;
import org.testng.annotations.Test;
import pom.web.kobiton.LoginSeivyWeb;

public class OLBRespoonsive extends TestBase {
    @Test(dataProvider = "web", description = "intala seivy IOS : True : NA")
    public void instalarIOS(String browser) {
        int resolucion=0;

        int anchoAleatorio=LocalConfiguration.web.resoluciones[resolucion][0];
        int altoAleatorio=LocalConfiguration.web.resoluciones[resolucion][1];
        driver.manage().window().setSize(new Dimension(anchoAleatorio, altoAleatorio));
        loadUrl(driver,"https://gmf-dev.temenoscloud.com/apps/konyolb/#_frmLogin");
        String user="111120210201";
        String pass="Kony@2031";
        LoginSeivyWeb login = new LoginSeivyWeb(driver);
        login.sendEmailorusername(user);
        login.sendPassword(pass);
        login.clickLogin();
        waitSeconds(6);
        login.clickVincularAhora();
        waitSeconds(8);
        login.clickLinkContactanos();
        waitSeconds(2);
        login.clickMenuProductos();

        System.out.println();
    }
}
