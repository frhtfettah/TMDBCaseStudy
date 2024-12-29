package testcases;

import constants.ApiParameters;
import constants.Constants;
import functions.RestOperation;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

import static constants.Constants.*;
import static functions.JsonOperation.*;

public class ErrorHandlingCases {

    @Test
    public void authenticationInvalidToken() {

        String[] response = RestOperation.sendGetRequest("authentication", "invalidToken");

        Assert.assertEquals(response[0], statusCodeUnauthorized);
        Integer checkStatusCode = jsonParseToInteger(response[1], "status_code");
        String checkStatusMessage = jsonParseToString(response[1], "status_message");
        Boolean checkSuccess = jsonParseToBoolean(response[1], "success");

        Assert.assertEquals(checkStatusCode, 7);
        Assert.assertEquals(checkStatusMessage, "Invalid API key: You must be granted a valid key.");
        Assert.assertEquals(checkSuccess, false);
    }

    @Test
    public void authenticationEmpty(){

        String[] response = RestOperation.sendGetRequest("authentication", "");

        Assert.assertEquals(response[0], statusCodeUnauthorized);
        Integer checkStatusCode = jsonParseToInteger(response[1], "status_code");
        String checkStatusMessage = jsonParseToString(response[1], "status_message");
        Boolean checkSuccess = jsonParseToBoolean(response[1], "success");

        Assert.assertEquals(checkStatusCode, 7);
        Assert.assertEquals(checkStatusMessage, "Invalid API key: You must be granted a valid key.");
        Assert.assertEquals(checkSuccess, false);
    }

    @Test
    public void invalidMovieId(){
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put(ApiParameters.language, Constants.language);

        HashMap<String, Object> pathParams = new HashMap<>();
        pathParams.put(ApiParameters.movieId, "invalidMovieId");

        String[] response = RestOperation.sendGetRequest("movie/{movie_id}", queryParams, pathParams);

        Assert.assertEquals(response[0], statusCodeNotFound);
        Boolean checkSuccess = jsonParseToBoolean(response[1], "success");
        Integer checkStatusCode = jsonParseToInteger(response[1], "status_code");
        String checkStatusMessage = jsonParseToString(response[1], "status_message");

        Assert.assertEquals(checkStatusCode, 6);
        Assert.assertEquals(checkStatusMessage, "Invalid id: The pre-requisite id is invalid or not found.");
        Assert.assertEquals(checkSuccess, false);
    }

    @Test
    public void trendingWithWrongTimeline(){
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put(ApiParameters.language, Constants.language);

        String[] response = RestOperation.sendGetRequest("trending/tv/month", queryParams);

        Assert.assertEquals(response[0], statusCodeBadRequest);
        Boolean checkSuccess = jsonParseToBoolean(response[1], "success");
        Integer checkStatusCode = jsonParseToInteger(response[1], "status_code");
        String checkStatusMessage = jsonParseToString(response[1], "status_message");

        Assert.assertEquals(checkStatusCode, 5);
        Assert.assertEquals(checkStatusMessage, "Invalid parameters: Your request parameters are incorrect.");
        Assert.assertEquals(checkSuccess, false);
    }

    @Test
    public void createListWithInvalidSessionId(){
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put(ApiParameters.sessionId, "invalidSessionID");

        JSONObject body = new JSONObject();
        body.put(ApiParameters.iso639, Constants.iso639);
        body.put(ApiParameters.iso3166, Constants.iso3166);
        body.put(ApiParameters.name, Constants.name);
        body.put(ApiParameters.description, Constants.description);
        body.put(ApiParameters.isPublic, Constants.isPublic);
        body.put(ApiParameters.showComments, Constants.showComments);
        body.put(ApiParameters.sortBy, Constants.sortByOriginalOrder);

        String[] response = RestOperation.sendPostRequest("list", queryParams, body);

        Assert.assertEquals(response[0], statusCodeUnauthorized);
        Boolean checkSuccess = jsonParseToBoolean(response[1], "success");
        Integer checkStatusCode = jsonParseToInteger(response[1], "status_code");
        String checkStatusMessage = jsonParseToString(response[1], "status_message");

        Assert.assertEquals(checkSuccess, false);
        Assert.assertEquals(checkStatusCode, 3);
        Assert.assertEquals(checkStatusMessage, "Authentication failed: You do not have permissions to access the service.");
    }

    @Test
    public void addItemToListWithInvalidParameters(){
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put(ApiParameters.sessionId, Constants.sessionId);

        HashMap<String, Object> pathParams = new HashMap<>();
        pathParams.put(ApiParameters.listId, "8503210");

        JSONObject body = new JSONObject();
        body.put("invalidParameter", "invalidValue");

        String[] response = RestOperation.sendPostRequest("list/{list_id}/add_item", queryParams, pathParams, body);

        Assert.assertEquals(response[0], statusCodeBadRequest);
        Boolean checkSuccess = jsonParseToBoolean(response[1], "success");
        Integer checkStatusCode = jsonParseToInteger(response[1], "status_code");
        String checkStatusMessage = jsonParseToString(response[1], "status_message");

        Assert.assertEquals(checkSuccess, false);
        Assert.assertEquals(checkStatusCode, 5);
        Assert.assertEquals(checkStatusMessage, "Invalid parameters: Your request parameters are incorrect.");
    }

    @Test
    public void addFavoriteWithInvalidParameter(){
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put(ApiParameters.sessionId, Constants.sessionId);

        HashMap<String, Object> pathParams = new HashMap<>();
        pathParams.put(ApiParameters.accountId, Constants.accountId);

        JSONObject body = new JSONObject();
        body.put("invalidParameter", "invalidValue");

        String[] response = RestOperation.sendPostRequest("account/{account_id}/favorite", queryParams, pathParams, body);

        Assert.assertEquals(response[0], statusCodeBadRequest);
        Boolean checkSuccess = jsonParseToBoolean(response[1], "success");
        Integer checkStatusCode = jsonParseToInteger(response[1], "status_code");
        String checkStatusMessage = jsonParseToString(response[1], "status_message");

        Assert.assertEquals(checkSuccess, false);
        Assert.assertEquals(checkStatusCode, 5);
        Assert.assertEquals(checkStatusMessage, "Invalid parameters: Your request parameters are incorrect.");
    }
}
