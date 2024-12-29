package functions;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;

import java.util.HashMap;

import static constants.Constants.authToken;
import static constants.Constants.baseUri;

public class RestOperation {

    public static String[] sendGetRequest(String url){

        RestAssured.baseURI = baseUri;
        RequestSpecification httpRequest = RestAssured.given().header("Authorization", authToken);
        Response response = httpRequest.request(Method.GET, url);

        int responseCode = response.getStatusCode();
        String responseBody = response.getBody().asString();

        String[] result = new String[2];
        result[0]= Integer.toString(responseCode);
        result[1]=responseBody;

        return result;
    }

    public static String[] sendGetRequest(String url, HashMap<String, Object> queryParams){

        RestAssured.baseURI = baseUri;
        RequestSpecification httpRequest = RestAssured.given().header("Authorization", authToken).queryParams(queryParams);
        Response response = httpRequest.request(Method.GET, url);

        int responseCode = response.getStatusCode();
        String responseBody = response.getBody().asString();

        String[] result = new String[2];
        result[0]= Integer.toString(responseCode);
        result[1]=responseBody;

        return result;
    }

    public static String[] sendGetRequest(String url, HashMap<String, Object> queryParams, HashMap<String, Object> pathParams){

        RestAssured.baseURI = baseUri;
        RequestSpecification httpRequest = RestAssured.given().header("Authorization", authToken).queryParams(queryParams).pathParams(pathParams);
        Response response = httpRequest.request(Method.GET, url);

        int responseCode = response.getStatusCode();
        String responseBody = response.getBody().asString();
        String[] result = new String[2];
        result[0]= Integer.toString(responseCode);
        result[1]=responseBody;

        return result;
    }

    public static String[] sendGetRequest(String url, String authToken){

        RestAssured.baseURI = baseUri;
        RequestSpecification httpRequest = RestAssured.given().header("Authorization", authToken);
        Response response = httpRequest.request(Method.GET, url);

        int responseCode = response.statusCode();
        String responseBody = response.getBody().asString();
        String[] result = new String[2];
        result[0] = Integer.toString(responseCode);
        result[1] = responseBody;

        return result;
    }

    public static String[] sendPostRequest(String url, HashMap<String, Object> queryParams, JSONObject body){

        RestAssured.baseURI = baseUri;
        RequestSpecification httpRequest = RestAssured.given().header("Authorization", authToken).
                header("content-type","application/json").queryParams(queryParams).body(body.toString());
        Response response = httpRequest.request(Method.POST, url);

        int responseCode = response.getStatusCode();
        String responseBody = response.getBody().asString();

        String[] result = new String[2];
        result[0]= Integer.toString(responseCode);
        result[1]=responseBody;

        return result;
    }

    public static String[] sendPostRequest(String url, HashMap<String, Object> queryParams, HashMap<String, Object> pathParams, JSONObject body){

        RestAssured.baseURI = baseUri;
        RequestSpecification httpRequest = RestAssured.given().header("Authorization", authToken).
                header("content-type","application/json").queryParams(queryParams).pathParams(pathParams).body(body.toString());
        Response response = httpRequest.request(Method.POST, url);

        int responseCode = response.getStatusCode();
        String responseBody = response.getBody().asString();
        String[] result = new String[2];
        result[0]= Integer.toString(responseCode);
        result[1]=responseBody;

        return result;
    }

    public static String[] sendDeleteRequest(String url, HashMap<String, Object> queryParams, HashMap<String, Object> pathParams){

        RestAssured.baseURI = baseUri;
        RequestSpecification httpRequest = RestAssured.given().header("Authorization", authToken).queryParams(queryParams).pathParams(pathParams);
        Response response = httpRequest.request(Method.DELETE, url);

        int responseCode = response.getStatusCode();
        String responseBody = response.getBody().asString();
        String[] result = new String[2];
        result[0]= Integer.toString(responseCode);
        result[1]=responseBody;

        return result;
    }
}
