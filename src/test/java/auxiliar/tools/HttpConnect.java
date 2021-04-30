package auxiliar.tools;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpConnect {

    public static HttpResponse<String> HttpSendRequest(String url,String key){
        HttpResponse<String> response=null;
        String apiToken=key;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                //.header("X-TrackerToken",apiToken)
                .header("X-TrackerToken",apiToken)
                .build();
        try{
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.statusCode());

        }catch (Exception e){
            e.printStackTrace();
        }
        return response;
    }
}
