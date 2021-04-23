package test.mobile.modulo1;

import auxiliar.mobile.TestBase;
import org.testng.annotations.*;
import pom.mobile.modulo1.Page;

import static org.testng.Assert.assertTrue;

public class TestGoogle extends TestBase {
    Page page;

    @BeforeMethod
    public void startPom(){
        page = new Page(driver);
    }

    @Test(priority = 1, description = "")
    public void testClickInputSearch(){
        assertTrue(page.clickInputSearch());
    }

    @Test(priority = 2, description = "")
    public void testSendKeyInputUrlBar(){
        assertTrue(page.sendKeyInputUrlBar("prueba"));
    }
}
