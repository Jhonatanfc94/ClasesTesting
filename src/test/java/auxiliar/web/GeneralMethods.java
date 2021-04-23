package auxiliar.web;

import auxiliar.Data;
import auxiliar.tools.EnviarCorreo;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class GeneralMethods {
    private final String windows="Windows 10";
    private final String windowsServer="Windows Server 2016";
    private String sistema="os.name";

    public void loadUrl(WebDriver driver, String url){
        if (url.isEmpty()){
            Assert.fail("Empty URL given");
        }
        try {
            driver.get(url);
            waitPageLoad(driver);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    //CLICK
    public void click(WebElement element, WebDriver driver, WebDriverWait wait){
        try{
            goToElement(element,driver);
            highlight(driver,element);
            wait.until(ExpectedConditions.visibilityOf(element));
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
            waitPageLoad(driver);
        }catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }
    public void clickXpathViaJS(String xpath, WebDriver driver, WebDriverWait wait){
        try{
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpath)));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath(xpath)));
            waitPageLoad(driver);
        }catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }
    public void clickElementoViaJS(WebElement elemento, WebDriver driver){
        try{
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", elemento);
            waitPageLoad(driver);
        }catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }
    public void clickLista(List<WebElement> listaWebElements,WebDriver driver, WebDriverWait wait){
        for (WebElement camposColumna : listaWebElements) {
            highlight(driver,camposColumna);
            click(camposColumna,driver,wait);
        }
    }

    //SEND TEXT
    public void sendText(WebElement casillaTexto, String texto,WebDriver driver, WebDriverWait wait){
        try{
            goToElement(casillaTexto,driver);
            highlight(driver,casillaTexto);
            wait.until(ExpectedConditions.visibilityOf(casillaTexto));
            casillaTexto.sendKeys(Keys.BACK_SPACE,Keys.BACK_SPACE,Keys.BACK_SPACE,Keys.BACK_SPACE);
            if (System.getProperty(sistema).equals(windows)||System.getProperty(sistema).equals(windowsServer)){
                casillaTexto.sendKeys(Keys.CONTROL,"a");
            }else{
                casillaTexto.sendKeys(Keys.COMMAND,"a");
            }
            casillaTexto.sendKeys(texto);
        }catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    //GET TEXT
    public String getTextoDeElemento(WebElement elemento, WebDriver driver, WebDriverWait wait){
        try{
            goToElement(elemento,driver);
            highlight(driver,elemento);
            wait.until(ExpectedConditions.elementToBeClickable(elemento));
        }catch (Exception e) {
            Assert.fail(e.getMessage());
        }
        return elemento.getText();
    }

    //SELECT
    public void selectOptionByText(WebElement select, String option, WebDriver driver, WebDriverWait wait){
        try{
            goToElement(select,driver);
            highlight(driver,select);
            wait.until(ExpectedConditions.visibilityOf(select));
            Select options = new Select(select);
            options.selectByVisibleText(option);
        }catch(Exception e){
            Assert.fail(e.getMessage());
        }
    }
    public String getFirstSelected(WebElement select, WebDriver driver, WebDriverWait wait){
        String seleccion="";
        try{
            goToElement(select,driver);
            highlight(driver,select);
            wait.until(ExpectedConditions.visibilityOf(select));
            Select opciones = new Select(select);
            seleccion = opciones.getFirstSelectedOption().getText();
        }catch(Exception e){
            Assert.fail(e.getMessage());
        }
        return seleccion;
    }

    public void selectOptionByContainText(WebElement selector, String opcion, WebDriver driver, WebDriverWait wait) {
        Select select = new Select(selector);
        try {
            goToElement(selector, driver);
            wait.until(ExpectedConditions.visibilityOf(selector));
            highlight(driver, selector);
            List<WebElement> listOfElementsOfSelect = select.getOptions();
            for (WebElement elementOfSelect : listOfElementsOfSelect) {
                String txtOfElement = elementOfSelect.getText().trim();
                if (txtOfElement.toLowerCase().contains(opcion.trim().toLowerCase())) {
                    select.selectByVisibleText(txtOfElement);
                    break;
                }
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }
    public List<String> getOptionsSelect(WebElement selector, WebDriver driver, WebDriverWait wait){
        Select select = new Select(selector);
        List<String> listaOpciones=new ArrayList<>();
        try {
            goToElement(selector, driver);
            wait.until(ExpectedConditions.visibilityOf(selector));
            highlight(driver, selector);
            List<WebElement> listOfElementsOfSelect = select.getOptions();
            for (WebElement campos : listOfElementsOfSelect) {
                highlight(driver,campos);
                listaOpciones.add(campos.getText().trim());
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
        return listaOpciones;
    }

    //CHECK
    private boolean isCheck( WebElement chk,WebDriverWait wait){
        return wait.until(ExpectedConditions.visibilityOf(chk)).getAttribute("checked")!= null;
    }
    public void check(WebElement chk,WebDriver driver,WebDriverWait wait){
        if(!isCheck(chk,wait)){
            click(chk,driver,wait);
        }
    }

    //ALARM
    private String acceptAlert(WebDriver driver){
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        alert.accept();
        return alertText;
    }

    //ATTRIBUTE
    public String getAttribute(WebElement element,String atributo, WebDriver driver, WebDriverWait wait){
        highlight(driver, element);
        wait.until(ExpectedConditions.visibilityOf(element));
        return element.getAttribute(atributo);
    }
    public String getAttributeNoVisible(WebElement element,String atributo, WebDriver driver){
        highlight(driver, element);
        return element.getAttribute(atributo);
    }
    public void quitarAtributoReadOnly(WebElement elemento, WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('readonly','readonly')", elemento);
    }

    //BOOLEAN
    public boolean visibilidadWebElement(List<WebElement> elements, WebDriverWait wait){
        try{
            wait.until(ExpectedConditions.visibilityOfAllElements(elements));
            return true;
        }catch (Exception e) {
            return false;
        }
    }
    public boolean visibilidadWebElement(WebElement element, WebDriverWait wait){
        try{
            wait.until(ExpectedConditions.visibilityOf(element));
            return true;
        }catch (Exception e) {
            return false;
        }
    }
    public boolean existeXpath(String xpath, WebDriverWait wait){
        try{
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    //Web Element
    public WebElement encontrarElementoXpath(String xpath,WebDriver driver,WebDriverWait wait){
        WebElement buscado=null;
        try{
            buscado =wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
            goToElement(buscado,driver);
            highlight(driver,buscado);
        }catch (Exception e) {
            Assert.fail(e.getMessage());
        }
        return buscado;
    }
    public List<WebElement> encontrarListaElementosXpath(String xpath,WebDriver driver,WebDriverWait wait){
        List<WebElement> lista = new LinkedList<>();
        try{
            lista =wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(xpath)));
            goToElement(lista.get(0),driver);
            for (WebElement camposColumna : lista) {
                highlight(driver,camposColumna);
            }
        }catch (Exception e) {
            Assert.fail(e.getMessage());
        }
        return lista;
    }

    //GENERAL
    public void imprimirListaString(List<String> lista, String encabezado, String vineta) {
        System.out.println(encabezado);
        for(String elemento : lista) {
            System.out.println(vineta + elemento);
        }
    }
    public void imprimirListaDouble(List<Double> lista, String encabezado, String vineta) {
        System.out.println(encabezado);
        for(Double elemento : lista) {
            System.out.println(vineta + elemento);
        }
    }
    public void waitSeconds(int seconds){
        long miliSeconds = (long)seconds *1000;
        try{
            Thread.sleep(miliSeconds);
        }catch(Exception e){
            Assert.fail(e.getMessage());
        }
    }
    public int getRandomPosition(List<String> list) {
        return ThreadLocalRandom.current().nextInt(list.size());
    }

    public void goToElement(WebElement element, WebDriver driver){
        try {
            Thread.sleep(500);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            Thread.sleep(500);
        }catch (Exception e){}
    }
    public void highlight(WebDriver driver, WebElement element){
        if (driver instanceof JavascriptExecutor) {
            ((JavascriptExecutor)driver).executeScript("arguments[0].style.border='3px solid green'", element);
        }
    }
    public void waitPageLoad(WebDriver driver) {
        WebDriverWait wait;
        ExpectedCondition<Boolean> pageLoadCondition;
        try {
            pageLoadCondition = new
                    ExpectedCondition<Boolean>() {
                        public Boolean apply(WebDriver driver) {
                            return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("interactive");
                        }
                    };
            wait = new WebDriverWait(driver, 1);
            wait.until(pageLoadCondition);
        }catch (Exception e) {}
        try {
            pageLoadCondition = new
                    ExpectedCondition<Boolean>() {
                        public Boolean apply(WebDriver driver) {
                            return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
                        }
                    };
            wait = new WebDriverWait(driver, 30);
            wait.until(pageLoadCondition);
        } catch (Exception e) {}
    }
    //GMAIL REGRESION
    public void notificarRegresion(String messageRegresion) {
        String clase="";
        if (Thread.currentThread().getStackTrace().length > 2) {
            clase= Thread.currentThread().getStackTrace()[2].getClassName();
        } else {
            clase= "undefined";
        }
        String metodo="";
        if (Thread.currentThread().getStackTrace().length > 2) {
            metodo= Thread.currentThread().getStackTrace()[2].getMethodName();
        } else {
            metodo= "undefined";
        }

        String asuntoCorreo = "REGRESIÓN: [" + clase + "] --" + (new Date());
        String cuerpoCorreo = "<strong> Clase: </strong>[" + clase + "]" + "<br />" +
                "<strong>Método:</strong>. [" + metodo + "]" + "<br />" +
                "<strong>Motivo de fallo:</strong> " + messageRegresion;
        EnviarCorreo.enviarCorreo(asuntoCorreo, cuerpoCorreo);
        Assert.fail(messageRegresion);
    }

    //FILES
    public boolean compararArchivos(File archivo1, File archivo2) {
        try {
            if (FileUtils.contentEquals(archivo1, archivo2)) {
                return true;
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
        return false;
    }

    public void borrarArchivo(String nameArchivo) {
        File archivo = new File(Data.descargas.directorioDeDescargas + nameArchivo);
        if(!archivo.delete()){
            System.out.println("No se elimino ningun archivo");
        }
    }

    public boolean waitDescargaDeArchivo(String name) {
        boolean flag = false;
        File archivoDescargado = new File(Data.descargas.directorioDeDescargas);
        boolean archivoDercargadoBoolean=false;
        while(!archivoDercargadoBoolean){
            File[] dir_contents = archivoDescargado.listFiles();
            waitSeconds(5);
            if(dir_contents.length==2){
                archivoDercargadoBoolean=true;
            }
        }
        File[] dir_contents = archivoDescargado.listFiles();

        for (int i = 0; i < dir_contents.length; i++) {
            if (dir_contents[i].getName().equals(name))
                flag=true;
        }
        return flag;
    }

    public String getArchivoDescargadoContains(String nameContains){
        String pathBase=System.getProperty("user.dir")+"\\downloads\\";
        File carpeta = new File(pathBase);
        String[] listado = carpeta.list();
        String contains="";
        if (listado == null || listado.length == 0) {
            System.out.println("No hay elementos dentro de la carpeta actual");
        }
        else {
            for (String s : listado) {
                if (s.contains(nameContains)) {
                    contains = s;
                }
            }
        }
        return contains;
    }
    public void eliminarArchivosCarpetaDescarga(){
        String pathBase=System.getProperty("user.dir")+"\\downloads\\";
        File carpeta = new File(pathBase);
        String[] listado = carpeta.list();
        if(listado!=null){
            for (String s : listado) {
                borrarArchivo(s);
            }
        }
    }
    public String getContenidoDeUnArchivo(String name){
        String contenidoDelArchivo = "";
        String linea;
        Path path= Paths.get(Data.descargas.directorioDeDescargas + "\\" + name);
        try(BufferedReader bufer = Files.newBufferedReader(path)){
            while ((linea = bufer.readLine()) != null) {
                contenidoDelArchivo = contenidoDelArchivo.concat(linea + "\n");
            }
        }catch(IOException e){
            Assert.fail(e.getMessage());
        }
        return contenidoDelArchivo;
    }
}
