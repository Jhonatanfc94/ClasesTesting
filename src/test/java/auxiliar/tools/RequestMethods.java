package auxiliar.tools;

import io.restassured.http.Headers;
import io.restassured.response.Response;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class RequestMethods {
    public Response multiHeadersGetRequest(String url, Headers headers) {
        return given()
                .headers(headers)
                .when()
                .get(url)
                .then()
                .extract()
                .response();
    }

    public Response makeGetRequest(String url) {
        return given()
                .header("Content=Type", "application/json")
                .when()
                .get(url)
                .then()
                .extract()
                .response();
    }

    public Response makeGetRequest(String url, HashMap params) {
        return given()
                .header("Content=Type", "application/json")
                .params(params)
                .get(url)
                .then()
                .extract()
                .response();
    }

    public Response makeDeleteRequest(String url) {
        return given()
                .header("Content=Type", "application/json")
                .when()
                .delete(url)
                .then()
                .extract()
                .response();
    }

    public Response makePostRequest(String url, HashMap body) {
        return given()
                .header("Content=Type", "application/json")
                .body(body)
                .when()
                .post(url)
                .then()
                .extract()
                .response();
    }
}
