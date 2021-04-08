package auxiliar.tools;

import java.net.HttpURLConnection;
import java.net.URL;

public class HttpConnect {
    public static String obtenerCodigoRespuestaLink(String linkUrl) {
        String respuesta="No se lleno la respuesta";
        try {
            URL url = new URL(linkUrl);
            HttpURLConnection httpURLConnect=(HttpURLConnection)url.openConnection();
            httpURLConnect.setConnectTimeout(3000);
            httpURLConnect.connect();
            if(httpURLConnect.getResponseCode()==200) {
                System.out.println(linkUrl+" - "+httpURLConnect.getResponseMessage());
                respuesta=httpURLConnect.getResponseMessage();
            }
            if(httpURLConnect.getResponseCode()==HttpURLConnection.HTTP_NOT_FOUND) {
                System.out.println(linkUrl+" - "+httpURLConnect.getResponseMessage() + " - "+ HttpURLConnection.HTTP_NOT_FOUND);
                respuesta=httpURLConnect.getResponseMessage() + " - "+ HttpURLConnection.HTTP_NOT_FOUND;
            }
        } catch (Exception ignored) { }
        return respuesta;
    }
}
