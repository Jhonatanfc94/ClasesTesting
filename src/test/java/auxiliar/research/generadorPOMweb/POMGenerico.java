package auxiliar.research.generadorPOMweb;

import auxiliar.web.GeneralMethods;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class POMGenerico extends GeneralMethods {
    WebDriver driver;
    WebDriverWait wait;
    AjaxElementLocatorFactory factory;

    final String reglaNoInvibilidadAnd = " and not(contains(@style,'none')) and not(@type='hidden') and not(ancestor::div[contains(@class,'hid')]) and not(ancestor::div[contains(@style,'display: none')]) and not(ancestor::div[contains(@role,'combobox')])";
    final String reglaNoInvibilidad = "not(contains(@style,'none')) and not(@type='hidden') and not(ancestor::div[contains(@class,'hid')]) and not(ancestor::div[contains(@style,'display: none')]) and not(ancestor::div[contains(@role,'combobox')])";
    final String reglaNoMultiselect = " and not(ancestor::div[contains(@*,'dropdown')])";

    final String reglaText="//input[(@type='text' or @type='date')"+reglaNoInvibilidadAnd +"]";
    final String reglaText2="//input[not(@type='button' or @type='submit' or @type='radio' or @type='checkbox')"+ reglaNoInvibilidadAnd +"]";
    final String reglaText3="//textarea["+ reglaNoInvibilidad +"]";
    @FindBy(xpath = reglaText+"|"+reglaText2+"|"+reglaText3)
    private List<WebElement> inputTxt;

    final String reglaButton="//input[(@type='button' or @type='submit')"+ reglaNoInvibilidadAnd+reglaNoMultiselect+"]";
    final String reglaButton2="//button[(@type='button' or @type='submit' or contains(@onclick,'javascript'))"+ reglaNoInvibilidadAnd +reglaNoMultiselect+"]";
    final String reglaButton3="//a[contains(@href,'tab')]//font["+ reglaNoInvibilidad +reglaNoMultiselect+"]";
    @FindBy(xpath = reglaButton+"|"+reglaButton2+"|"+reglaButton3)
    private List<WebElement> button;

    final String reglaSelect="//select["+reglaNoInvibilidad+reglaNoMultiselect+"]";
    @FindBy(xpath = reglaSelect)
    private List<WebElement> select;

    final String reglaRadioButton="//input[(@type='radio')"+reglaNoInvibilidadAnd+"]";
    @FindBy(xpath = reglaRadioButton)
    private List<WebElement> radioButton;

    final String reglaDropDown="//select["+reglaNoInvibilidad+"]/ancestor::div[contains(@*,'dropdown')]/select";
    @FindBy(xpath = reglaDropDown)
    private List<WebElement> dropDown;

    public POMGenerico(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver,8);
        factory = new AjaxElementLocatorFactory(this.driver, 8);
        PageFactory.initElements(factory, this);
    }

    public void imprimirRegla(String tipoElemento){
        System.out.println("Regla Txt:"+reglaText);
        System.out.println("Regla Txt:"+reglaText2);
        System.out.println("Regla Txt:"+reglaText3);

        System.out.println("Regla Button:"+reglaButton);
        System.out.println("Regla Button:"+reglaButton2);
        System.out.println("Regla Button:"+reglaButton3);

        System.out.println("Regla Select:"+reglaSelect);

        System.out.println("Regla Radio Button:"+reglaRadioButton);

        System.out.println("Regla reglaDropDown:"+reglaDropDown);
    }

    public List<WebElement> obtenerListaInput(){
        return inputTxt;
    }
    public List<WebElement> obtenerListaButton(){
        return button;
    }
    public List<WebElement> obtenerListaSelect(){
        return  select;
    }
    public List<WebElement> obtenerListaRadioButton(){
        return  radioButton;
    }
    public List<WebElement> obtenerListaDropDown(){
        return dropDown;
    }
}
