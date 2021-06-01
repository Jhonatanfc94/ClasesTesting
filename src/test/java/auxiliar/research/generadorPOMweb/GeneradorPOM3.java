package auxiliar.research.generadorPOMweb;

import auxiliar.web.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GeneradorPOM3 extends TestBase {
    POMGenerico pomGenerico;
    EscribirArchivoJava escribirArchivoJava;
    WebDriverWait wait;

    List<String> listaElementos;
    List<String> listaMetodos;

    public String url= "https://portal.kobiton.com/login";
    public String nombreClase="Login";

    String tipoTxt="txt";
    String tipoButton="button";

    @Test(description = "Abre un url y genera POM", dataProvider = "web" )
    private void generaPOM(String browserName) {
        inicializarVariables();
        loadUrl(driver,url);
        waitSeconds(2);

        obtenerElementosDOM();
        obtenerMetodos();
        escribirArchivoJava.crearJava(listaElementos,listaMetodos);
    }

    private void inicializarVariables(){
        pomGenerico = new POMGenerico(driver);
        wait = new WebDriverWait(driver,5);
        escribirArchivoJava = new EscribirArchivoJava(nombreClase);
    }

    private void obtenerElementosDOM(){
        List<String>listaElementosTxt=obtenerListaElementos(pomGenerico.obtenerListaInput(),tipoTxt);
        listaElementosTxt= elementosConAtributoRepetido(listaElementosTxt);

        List<String>listaElementosButton=obtenerListaElementos(pomGenerico.obtenerListaButton(),tipoButton);
        listaElementosButton= elementosConAtributoRepetido(listaElementosButton);

        List<String>listaElementosSelect=obtenerListaElementos(pomGenerico.obtenerListaSelect(),"select");
        listaElementosSelect= elementosConAtributoRepetido(listaElementosSelect);

        List<String>listaElementosRadioButton=obtenerListaElementos(pomGenerico.obtenerListaRadioButton(),"radio");
        listaElementosRadioButton= elementosConAtributoRepetido(listaElementosRadioButton);

        List<String>listaElementosDropDownUL=obtenerListaElementos(pomGenerico.obtenerListaDropDown(),"dropDownUl");
        List<String>listaElementosDropDownButton=obtenerListaElementos(pomGenerico.obtenerListaDropDown(),"dropDownButton");
        listaElementosDropDownUL= elementosConAtributoRepetido(listaElementosDropDownUL);
        listaElementosDropDownButton= elementosConAtributoRepetido(listaElementosDropDownButton);

        listaElementos = new LinkedList<>();
        listaElementos.addAll(listaElementosTxt);
        listaElementos.addAll(listaElementosButton);
        listaElementos.addAll(listaElementosSelect);
        listaElementos.addAll(listaElementosRadioButton);
        listaElementos.addAll(listaElementosDropDownUL);
        listaElementos.addAll(listaElementosDropDownButton);
    }
    private List<String>obtenerListaElementos(List<WebElement>listaWebElements,String tipoElemento){
        List<String> strLista = new LinkedList<>();
        int contador=0;
        for (WebElement elemento : listaWebElements) {
            System.out.println("Numero de elementos "+tipoElemento+":"+(contador+1));
            strLista.add(obtenerXpathElemento(elemento,tipoElemento));
            contador++;
        }
        return strLista;
    }
    private String obtenerXpathElemento(WebElement webElement, String tipoElemento){
        String[] nombreAtributo = obtenerNombreAtributo(webElement,tipoElemento);
        String nombreElemento=nombreAtributo[0];
        String atributoElemento=nombreAtributo[1];
        String tagNameElemento=webElement.getTagName();

        if(nombreElemento.length()<1){
            Assert.fail("Atributo no encontrado");
        }
        String nombreM=primeraMayuscula(nombreElemento);
        nombreM=limpiarNombre(nombreM);
        String definicion;

        definicion="\"//"+tagNameElemento+"["+atributoElemento+"='"+nombreElemento+"']\"";
        if(tipoElemento.equals("radio")){
            if(!visibilidadWebElement(webElement,wait)){
                definicion="\"//"+tagNameElemento+"["+atributoElemento+"='"+nombreElemento+"']/..\"";
            }
        }
        if(tipoElemento.equals("dropDownButton")){
            definicion="\"//"+tagNameElemento+"["+atributoElemento+"='"+nombreElemento+"']/../button\"";
        }
        if(tipoElemento.equals("dropDownUl")){
            definicion="\"//"+tagNameElemento+"["+atributoElemento+"='"+nombreElemento+"']/..//ul\"";
        }

        definicion="@FindBy(xpath = "+definicion+")\n"+"\tWebElement "+tipoElemento+nombreM+";";

        return definicion;
    }
    private String limpiarNombre(String nombre){
        String nombreLimpio=nombre;
        if(nombreLimpio.contains("]")){
            nombreLimpio=nombreLimpio.replace("]","");
        }
        if(nombreLimpio.contains("[")){
            nombreLimpio=nombreLimpio.replace("[","");
        }
        return nombreLimpio;
    }
    public String primeraMayuscula(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        } else {
            String[] palabras=str.split("_");
            StringBuilder palabraCamel= new StringBuilder();
            for(String palabra:palabras){
                String palabraMinuscula=palabra.toLowerCase();
                palabraCamel.append(primeraMayusculaPorPalabra(palabraMinuscula));
            }
            palabras= palabraCamel.toString().split(" ");
            palabraCamel = new StringBuilder();
            for(String palabra:palabras){
                String palabraMinuscula=palabra.toLowerCase();
                palabraCamel.append(primeraMayusculaPorPalabra(palabraMinuscula));
            }
            return palabraCamel.toString();
        }
    }
    private String primeraMayusculaPorPalabra(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        } else {
            String palabraMinuscula=str.toLowerCase();
            return palabraMinuscula.substring(0, 1).toUpperCase() + palabraMinuscula.substring(1);
        }
    }

    private List<String> elementosConAtributoRepetido(List<String> elementos){
        List<String> listaFinal=new ArrayList<>();
        List<String> copiaElementos=new ArrayList<>(elementos);
        StringBuilder remplazo = new StringBuilder();
        int contador=0;
        while(contador<copiaElementos.size()){
            String elementoActual=copiaElementos.get(contador);
            copiaElementos.remove(contador);
            if(copiaElementos.contains(elementoActual)){
                String[] separado=elementoActual.split("\"");
                separado[1]="\"("+separado[1]+")[1]\"";
                for (String s : separado) {
                    remplazo.append(s);
                }
                separado= remplazo.toString().split("WebElement ");
                separado[1]="WebElement "+separado[1].replace(";","")+"1;";
                remplazo = new StringBuilder();
                for (String value : separado) {
                    remplazo.append(value);
                }
                listaFinal.add(remplazo.toString());
                copiaElementos.add(contador, remplazo.toString());
                contador=contador+1;
                copiaElementos.remove(elementoActual);

                separado=elementoActual.split("\"");
                separado[1]="\"("+separado[1]+")[2]\"";
                remplazo = new StringBuilder();
                for (String s : separado) {
                    remplazo.append(s);
                }
                separado= remplazo.toString().split("WebElement ");
                separado[1]="WebElement "+separado[1].replace(";","")+"2;";
                remplazo = new StringBuilder();
                for (String s : separado) {
                    remplazo.append(s);
                }
                listaFinal.add(remplazo.toString());
            }else{
                listaFinal.add(elementoActual);
            }
            copiaElementos.add(contador, remplazo.toString());
            contador=contador+1;
        }
        return listaFinal;
    }

    private void obtenerMetodos(){
        List<String>listaMetodosTxt=obtenerListaMetodos(pomGenerico.obtenerListaInput(),tipoTxt);
        listaMetodosTxt=metodoRepetido(listaMetodosTxt);
        List<String>listaMetodosButton=obtenerListaMetodos(pomGenerico.obtenerListaButton(),tipoButton);
        listaMetodosButton=metodoRepetido(listaMetodosButton);
        List<String>listaMetodosSelect=obtenerListaMetodos(pomGenerico.obtenerListaSelect(),"select");
        listaMetodosSelect=metodoRepetido(listaMetodosSelect);
        List<String>listaMetodosRadioButton=obtenerListaMetodos(pomGenerico.obtenerListaRadioButton(),"radio");
        listaMetodosRadioButton=metodoRepetido(listaMetodosRadioButton);
        List<String>listaMetodosDropDown=obtenerListaMetodos(pomGenerico.obtenerListaDropDown(),"dropDown");
        listaMetodosDropDown=metodoRepetido(listaMetodosDropDown);

        listaMetodos = new LinkedList<>();
        listaMetodos.addAll(listaMetodosTxt);
        listaMetodos.addAll(listaMetodosButton);
        listaMetodos.addAll(listaMetodosSelect);
        listaMetodos.addAll(listaMetodosRadioButton);
        listaMetodos.addAll(listaMetodosDropDown);
    }
    private List<String>obtenerListaMetodos(List<WebElement>listaWebElements,String tipoElemento){
        List<String> strLista = new LinkedList<>();
        int contador=0;
        for (WebElement elemento : listaWebElements) {
            System.out.println("Numero de Metodo:"+contador+" Tipo "+tipoElemento);
            strLista.add(obtenerMetodo(elemento,tipoElemento));
            contador++;
        }
        return strLista;
    }
    private List<String> metodoRepetido(List<String> metodos){
        List<String> listaFinal=new ArrayList<>();
        List<String> copiaMetodos=new ArrayList<>(metodos);
        StringBuilder remplazo= new StringBuilder();
        for(int i=0;i<copiaMetodos.size();i++){
            String metodoActual=copiaMetodos.get(i);
            copiaMetodos.remove(i);
            if(copiaMetodos.contains(metodoActual)){
                copiaMetodos.remove(metodoActual);
                String coma=",";
                String parentesis="\\(";
                String[] separado=metodoActual.split(coma);
                separado[0]=separado[0]+"1";
                remplazo = new StringBuilder();
                for(int j=0;j<separado.length;j++){
                    if(j!=separado.length-1){
                        remplazo.append(separado[j]).append(coma);
                    }else{
                        remplazo.append(separado[j]);
                    }
                }

                separado= remplazo.toString().split(parentesis);
                separado[0]=separado[0]+"1";
                remplazo = new StringBuilder();
                for(int j=0;j<separado.length;j++){
                    if(j!=separado.length-1){
                        remplazo.append(separado[j]).append("(");
                    }else{
                        remplazo.append(separado[j]);
                    }
                }
                listaFinal.add(remplazo.toString());

                separado=metodoActual.split(coma);
                separado[0]=separado[0]+"2";
                remplazo = new StringBuilder();
                for(int j=0;j<separado.length;j++){
                    if(j!=separado.length-1){
                        remplazo.append(separado[j]).append(coma);
                    }else{
                        remplazo.append(separado[j]);
                    }
                }

                separado= remplazo.toString().split(parentesis);
                separado[0]=separado[0]+"2";
                remplazo = new StringBuilder();
                for(int j=0;j<separado.length;j++){
                    if(j!=separado.length-1){
                        remplazo.append(separado[j]).append("(");
                    }else{
                        remplazo.append(separado[j]);
                    }
                }
                listaFinal.add(remplazo.toString());
            }else{
                listaFinal.add(metodoActual);
            }
            copiaMetodos.add(i, remplazo.toString());
        }
        return listaFinal;
    }
    private String[] obtenerNombreAtributo(WebElement webElement, String tipoElemento){
        String name;
        String id;
        String href;
        String value;
        String text;

        if(!tipoElemento.equals("radio")&&!tipoElemento.contains("dropDown")){
            name=getAttribute(webElement,"name",driver,wait);
            id=getAttribute(webElement,"id",driver,wait);
            href=getAttribute(webElement,"href",driver,wait);
            value=getAttribute(webElement,"value",driver,wait);
            text=getTextoDeElemento(webElement,driver,wait);
        }else{
            name=getAttributeNoVisible(webElement,"name",driver);
            id=getAttributeNoVisible(webElement,"id",driver);
            href=getAttributeNoVisible(webElement,"href",driver);
            value=getAttributeNoVisible(webElement,"value",driver);
            text=getTextoDeElementoNoVisible(webElement,driver);
        }


        boolean nameAtribute=!(name==null || name.isEmpty());
        boolean idAtribute=!(id==null || id.isEmpty());
        boolean hrefAtribute=!(href==null || href.isEmpty());
        boolean valueAtribute=!(value==null || value.isEmpty());
        boolean textAtribute=!text.isEmpty();

        String nombre="";
        String atributo="";
        if ("button".equals(tipoElemento)) {
            if (textAtribute) {
                nombre = text;
                atributo = "text()";
            } else if (valueAtribute) {
                nombre = value;
                atributo = "@value";
            } else if (nameAtribute) {
                nombre = name;
                atributo = "@name";
            } else if (idAtribute) {
                nombre = id;
                atributo = "@id";
            } else if (hrefAtribute) {
                nombre = href;
                atributo = "@href";
            }
        } else {
            if (nameAtribute) {
                nombre = name;
                atributo = "@name";
            } else if (idAtribute) {
                nombre = id;
                atributo = "@id";
            } else if (hrefAtribute) {
                nombre = href;
                atributo = "@href";
            } else if (valueAtribute) {
                nombre = value;
                atributo = "@value";
            } else if (textAtribute) {
                nombre = text;
                atributo = "text()";
            }
        }
        return new String[]{nombre,atributo};
    }
    private String obtenerMetodo(WebElement webElement,String tipoElemento){
        String nombre=obtenerNombreAtributo(webElement,tipoElemento)[0];
        nombre=limpiarNombre(nombre);
        if(nombre.length()<1){
            Assert.fail("Nombre no encontrado");
        }
        String nombreM=primeraMayuscula(nombre);
        nombreM=limpiarNombre(nombreM);
        nombre=primeraMinusculaPorPalabra(nombreM);

        String metodo;
        switch (tipoElemento) {
            case "txt":  metodo = "public void send"+nombreM+"(String "+nombre+"){\n" +
                    "\t\tsendText("+tipoElemento+nombreM+","+nombre+",driver,wait);\n" +
                    "\t}";
                break;
            case "button":  metodo = "public void click"+nombreM+"(){\n" +
                    "\t\tclick("+tipoElemento+nombreM+",driver,wait);\n" +
                    "\t}";
                break;
            case "select":  metodo = "public void seleccionar"+nombreM+"(String "+nombre+"){\n" +
                    "\t\tseleccionarOpcionPorTexto("+tipoElemento+nombreM+","+nombre+",driver,wait);\n" +
                    "\t}";
                break;
            case "radio": metodo = "public void clickRadio"+nombreM+"(){\n" +
                    "\t\tclick("+tipoElemento+nombreM+",driver,wait);\n" +
                    "\t}";
                break;
            case "dropDown":
                metodo = "public void seleccionarMultiselect"+nombreM+"(String[] "+nombre+"){\n" +
                    "\t\tseleccionarOpcionesMultiselect("+tipoElemento+"Button"+nombreM+","+tipoElemento+"Ul"+nombreM+","+nombre+",driver,wait);\n" +
                    "\t}";
                break;
            default: metodo = "tipo de elemento inválido";
                break;
        }

        return metodo;
    }
    private static String primeraMinusculaPorPalabra(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        } else {
            return str.substring(0, 1).toLowerCase() + str.substring(1);
        }
    }


}
