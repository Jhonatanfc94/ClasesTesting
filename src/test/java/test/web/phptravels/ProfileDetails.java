package test.web.phptravels;

import auxiliar.web.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;
import pom.web.phptravels.Account;
import pom.web.phptravels.DropDownMenu;
import pom.web.phptravels.Login;

public class ProfileDetails extends TestBase {
    String userFronted="user@phptravels.com";
    String passFronted="demouser";
    String userBackEnd="admin@phptravels.com";
    String passBackEnd="demoadmin";

    String userName="Demo";
    String userLastName="User";

    DropDownMenu dropDownMenu;
    Login login;
    Account account;

    @Test(dataProvider = "web")
    public void loginTwitter(String browser){
        inicializarVariables();
        loadUrl(driver,"https://www.phptravels.net/");
        dropDownMenu.clickDropDownMenu();
        dropDownMenu.clickOptionDropDownMenu("Login");
        login.sendUserName(userFronted);
        login.sendPassword(passFronted);
        login.clickButtonLogin();
        if(!login.getHiMessage()){
            Assert.fail("The user expected for Login: "+userName+" "+userLastName+" No found");
        }
        dropDownMenu.clickDropDownMenu();
        dropDownMenu.clickOptionDropDownMenu("Account");
        account.clickMyProfile();
        String firstName= account.getFirstName();
        if(firstName.contains(userName)){
            System.out.println(firstName);
        }
        String lastName= account.getLastname();
        if(lastName.contains(userLastName)){
            System.out.println(lastName);
        }
        String phone= account.getPhone();
        System.out.println(phone);
    }


    public void inicializarVariables(){
        dropDownMenu = new DropDownMenu(driver);
        login = new Login(driver);
        account = new Account(driver);
    }
}
