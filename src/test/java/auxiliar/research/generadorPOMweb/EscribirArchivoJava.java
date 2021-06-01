package auxiliar.research.generadorPOMweb;

import org.testng.Assert;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;
import java.util.regex.Pattern;

public class EscribirArchivoJava {

    String nombreClase;

    public EscribirArchivoJava(String nombreClase){
        this.nombreClase=nombreClase;
    }

    public void crearJava(List<String> listaElementos, List<String> listaMetodos){
        PrintWriter writer = null;
        try {
            String ruta = System.getProperty("user.dir")+"\\src\\test\\java\\auxiliar\\research\\generadorPOMweb\\"+nombreClase+".java";
            String[] rutaA=ruta.split(Pattern.quote(File.separator));
            String pack=rutaA[rutaA.length-2];
            writer = new PrintWriter(ruta, "UTF-8");
            writer.println("package "+pack+";");
            writer.println("");
            writer.println("import auxiliar.web.GeneralMethods;");
            writer.println("import org.openqa.selenium.WebDriver;");
            writer.println("import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;");
            writer.println("import org.openqa.selenium.WebElement;");
            writer.println("import org.openqa.selenium.support.ui.WebDriverWait;");
            writer.println("import org.openqa.selenium.support.FindBy;");
            writer.println("import org.openqa.selenium.support.PageFactory;");
            writer.println("");
            writer.println("public class "+nombreClase+" extends GeneralMethods{");
            writer.println("");
            writer.println("\tWebDriver driver;");
            writer.println("\tWebDriverWait wait;");
            writer.println("\tAjaxElementLocatorFactory factory;");
            writer.println("");
            for (String elemento : listaElementos) {
                writer.println("\t"+elemento);
            }
            writer.println("");
            writer.println("\tpublic "+nombreClase+"(WebDriver driver){");
            writer.println("\t\tthis.driver = driver;");
            writer.println("\t\twait = new WebDriverWait(driver,5);");
            writer.println("\t\tfactory = new AjaxElementLocatorFactory(this.driver, 5);");
            writer.println("\t\tPageFactory.initElements(factory, this);");
            writer.println("\t\t}");
            writer.println("");
            for (String metodo : listaMetodos) {
                writer.println("\t"+metodo);
            }
            writer.println("");
            writer.println("}");
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e+"");
        } finally {
            if(writer != null) {
                writer.close();
            }
        }
    }
}
