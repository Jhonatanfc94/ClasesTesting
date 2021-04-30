package auxiliar.coverage;

import auxiliar.tools.HttpConnect;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.Assert;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;


public class Pivotal {

    //--Remplazar "#text"
    static String raizTest = System.getProperty("user.dir") + "\\src\\test\\java\\test\\web";
    static String rutaTest = raizTest;
    static int sumaParametro1 = 0;
    static List<String> archivos;


    public static final String URL_PREFIX = "https://www.pivotaltracker.com/services/v5";
    public static final String path = "/projects/2139329/stories?fields=id%2Cstory_type&with_story_type=feature&created_after=2021-01-01T00:00:00Z";

    public static void main(String[] args){
        String apiToken="e7cd8ea98b753219838e25bb982725be";
        HttpResponse<String> response = HttpConnect.HttpSendRequest(URL_PREFIX+path,apiToken);
        JSONParser parser = new JSONParser();
        try{
            JSONArray jsonArray = (JSONArray) parser.parse(response.body());
            for(int i=0;i<jsonArray.size();i++){
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                System.out.print("ID: "+jsonObject.get("id"));
                System.out.print("| type: "+jsonObject.get("story_type"));
                WordFileSearch.contador(jsonObject.get("id")+"");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void contador(String parametro1){
        File carpetaT = new File(rutaTest);
        archivos = new ArrayList<>();
        List<String> clases = listarFicherosPorCarpeta(carpetaT);
        String aux = "";
        for (String clase : clases) {
            if (!clase.split("\\\\")[clase.split("\\\\").length - 2].equals(aux)) {
                //System.out.println("\n#####   PACKAGE " + (aux = clase.split("\\\\")[clase.split("\\\\").length - 2]) + "   #####");
            }
            int totalPorClase = contarCasosDePrueba(clase,parametro1);
            //System.out.println(clase.split("\\\\")[clase.split("\\\\").length - 1] + ", [" + parametro1.replace("(", "") + ": " + totalPorClase + "]");
        }
        System.out.println("| Total : " + sumaParametro1);
        sumaParametro1=0;
    }

    public static List<String> listarFicherosPorCarpeta(File carpeta) {
        for (File ficheroEntrada : carpeta.listFiles()) {
            if (ficheroEntrada.isDirectory()) {
                listarFicherosPorCarpeta(ficheroEntrada);
            } else {
                archivos.add(ficheroEntrada.getAbsolutePath());
            }
        }
        return archivos;
    }

    public static int contarCasosDePrueba(String absolutePath, String parametro1) {
        File clase = new File(absolutePath);
        int contador = 0;
        try {
            FileReader fileReader = new FileReader(clase);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String linea;
            while ((linea = bufferedReader.readLine()) != null) {
                if (linea.contains(parametro1)) {
                    contador++;
                    sumaParametro1++;
                }
            }
            fileReader.close();
        } catch (Exception e) {
            System.out.println("Excepción leyendo fichero " + clase + ": ");
            Assert.fail(e.getMessage());
        }
        return contador;
    }
}
