package testcases;

import constants.ApiParameters;
import constants.Constants;
import functions.RestOperation;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;

import static constants.Constants.statusCodeCreated;
import static constants.Constants.statusCodeOK;
import static functions.JsonOperation.*;

public class UserListsAndFavorites {

    Integer listId = 0;

    @Test(priority = 1)
    public void createList(){
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put(ApiParameters.sessionId, Constants.sessionId);

        JSONObject body = new JSONObject();
        body.put(ApiParameters.iso639, Constants.iso639);
        body.put(ApiParameters.iso3166, Constants.iso3166);
        body.put(ApiParameters.name, Constants.name);
        body.put(ApiParameters.description, Constants.description);
        body.put(ApiParameters.isPublic, Constants.isPublic);
        body.put(ApiParameters.showComments, Constants.showComments);
        body.put(ApiParameters.sortBy, Constants.sortByAsc);

        String[] response = RestOperation.sendPostRequest("list", queryParams, body);

        Assert.assertEquals(response[0], statusCodeCreated);
        Boolean checkSuccess = jsonParseToBoolean(response[1], "success");
        Integer checkStatusCode = jsonParseToInteger(response[1], "status_code");
        String checkStatusMessage = jsonParseToString(response[1], "status_message");
        listId = jsonParseToInteger(response[1], "list_id");
        System.out.println("Created List ID: " + listId);

        Assert.assertEquals(checkSuccess, true);
        Assert.assertEquals(checkStatusCode, 1);
        Assert.assertEquals(checkStatusMessage, "Success.");
    }

    @Test(priority = 2)
    public void addItemToList(){
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put(ApiParameters.sessionId, Constants.sessionId);

        HashMap<String, Object> pathParams = new HashMap<>();
        pathParams.put(ApiParameters.listId, listId);

        JSONObject body = new JSONObject();
        body.put("media_type", "tv");
        body.put("media_id", 1439);

        String[] response = RestOperation.sendPostRequest("list/{list_id}/add_item", queryParams, pathParams, body);

        Assert.assertEquals(response[0], statusCodeCreated);
        Boolean checkSuccess = jsonParseToBoolean(response[1], "success");
        Integer checkStatusCode = jsonParseToInteger(response[1], "status_code");
        String checkStatusMessage = jsonParseToString(response[1], "status_message");

        Assert.assertEquals(checkSuccess, true);
        Assert.assertEquals(checkStatusCode, 12);
        Assert.assertEquals(checkStatusMessage, "The item/record was updated successfully.");
    }

    @Test(priority = 3)
    public void checkList(){
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put(ApiParameters.language, Constants.language);
        queryParams.put(ApiParameters.movieId, 1439);

        HashMap<String, Object> pathParams = new HashMap<>();
        pathParams.put(ApiParameters.listId, listId);

        String[] response = RestOperation.sendGetRequest("list/{list_id}/item_status", queryParams, pathParams);

        Assert.assertEquals(response[0], statusCodeOK);
        String checkListId = jsonParseToString(response[1], "id");
        Boolean checkItemStatus = jsonParseToBoolean(response[1], "item_present");

        Assert.assertEquals(checkListId, listId.toString());
        Assert.assertEquals(checkItemStatus, true);
    }

    @Test(priority = 4)
    public void removeItemFromList(){
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put(ApiParameters.sessionId, Constants.sessionId);

        HashMap<String, Object> pathParams = new HashMap<>();
        pathParams.put(ApiParameters.listId, listId);

        JSONObject body = new JSONObject();
        body.put("media_type", "tv");
        body.put("media_id", 1439);

        String[] response = RestOperation.sendPostRequest("list/{list_id}/remove_item", queryParams, pathParams, body);

        Assert.assertEquals(response[0], statusCodeOK);
        Boolean checkSuccess = jsonParseToBoolean(response[1], "success");
        Integer checkStatusCode = jsonParseToInteger(response[1], "status_code");
        String checkStatusMessage = jsonParseToString(response[1], "status_message");

        Assert.assertEquals(checkSuccess, true);
        Assert.assertEquals(checkStatusCode, 13);
        Assert.assertEquals(checkStatusMessage, "The item/record was deleted successfully.");
    }

    @Test(priority = 5)
    public void deleteList(){
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put(ApiParameters.sessionId, Constants.sessionId);

        HashMap<String, Object> pathParams = new HashMap<>();
        pathParams.put(ApiParameters.listId, listId);

        String[] response = RestOperation.sendDeleteRequest("list/{list_id}", queryParams, pathParams);

        Assert.assertEquals(response[0], statusCodeOK);
        Boolean checkSuccess = jsonParseToBoolean(response[1], "success");
        Integer checkStatusCode = jsonParseToInteger(response[1], "status_code");
        String checkStatusMessage = jsonParseToString(response[1], "status_message");

        Assert.assertEquals(checkSuccess, true);
        Assert.assertEquals(checkStatusCode, 13);
        Assert.assertEquals(checkStatusMessage, "The item/record was deleted successfully.");
    }

    @Test(priority = 6)
    public void addTvToFavoriteList(){
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put(ApiParameters.sessionId, Constants.sessionId);

        HashMap<String, Object> pathParams = new HashMap<>();
        pathParams.put(ApiParameters.accountId, Constants.accountId);

        JSONObject body = new JSONObject();
        body.put("media_type", "tv");
        body.put("media_id", 1439);
        body.put("favorite", true);

        String[] response = RestOperation.sendPostRequest("account/{account_id}/favorite", queryParams, pathParams, body);

        Assert.assertEquals(response[0], statusCodeCreated);
        Boolean checkSuccess = jsonParseToBoolean(response[1], "success");
        Integer checkStatusCode = jsonParseToInteger(response[1], "status_code");
        String checkStatusMessage = jsonParseToString(response[1], "status_message");

        Assert.assertEquals(checkSuccess, true);
        Assert.assertEquals(checkStatusCode, 1);
        Assert.assertEquals(checkStatusMessage, "Success.");
    }

    @Test(priority = 6)
    public void addTvToWatchList(){
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put(ApiParameters.sessionId, Constants.sessionId);

        HashMap<String, Object> pathParams = new HashMap<>();
        pathParams.put(ApiParameters.accountId, Constants.accountId);

        JSONObject body = new JSONObject();
        body.put("media_type", "tv");
        body.put("media_id", 1439);
        body.put("watchlist", true);

        String[] response = RestOperation.sendPostRequest("account/{account_id}/watchlist", queryParams, pathParams, body);

        Assert.assertEquals(response[0], statusCodeCreated);
        Boolean checkSuccess = jsonParseToBoolean(response[1], "success");
        Integer checkStatusCode = jsonParseToInteger(response[1], "status_code");
        String checkStatusMessage = jsonParseToString(response[1], "status_message");

        Assert.assertEquals(checkSuccess, true);
        Assert.assertEquals(checkStatusCode, 1);
        Assert.assertEquals(checkStatusMessage, "Success.");
    }

    @Test(priority = 7)
    public void favoriteTv(){
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put(ApiParameters.language, Constants.language);
        queryParams.put(ApiParameters.page, Constants.page);
        queryParams.put(ApiParameters.sessionId, Constants.sessionId);
        queryParams.put(ApiParameters.sortBy, Constants.sortByAsc);

        HashMap<String, Object> pathParams = new HashMap<>();
        pathParams.put(ApiParameters.accountId, Constants.accountId);

        String[] response = RestOperation.sendGetRequest("account/{account_id}/favorite/tv", queryParams, pathParams);

        Assert.assertEquals(response[0], statusCodeOK);
        List<String> checkName = jsonParseToStringList(response[1], "results.name");
        Assert.assertTrue(checkName.toString().contains("Here's Lucy"));
    }

    @Test(priority = 7)
    public void watchlistTv(){
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put(ApiParameters.language, Constants.language);
        queryParams.put(ApiParameters.page, Constants.page);
        queryParams.put(ApiParameters.sessionId, Constants.sessionId);
        queryParams.put(ApiParameters.sortBy, Constants.sortByAsc);

        HashMap<String, Object> pathParams = new HashMap<>();
        pathParams.put(ApiParameters.accountId, Constants.accountId);

        String[] response = RestOperation.sendGetRequest("account/{account_id}/watchlist/tv", queryParams, pathParams);

        Assert.assertEquals(response[0], statusCodeOK);
        List<String> checkName = jsonParseToStringList(response[1], "results.name");
        Assert.assertTrue(checkName.toString().contains("Here's Lucy"));
    }

    @Test(priority = 8)
    public void removeTvFromFavoriteList(){
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put(ApiParameters.sessionId, Constants.sessionId);

        HashMap<String, Object> pathParams = new HashMap<>();
        pathParams.put(ApiParameters.accountId, Constants.accountId);

        JSONObject body = new JSONObject();
        body.put("media_type", "tv");
        body.put("media_id", 1439);
        body.put("favorite", false);

        String[] response = RestOperation.sendPostRequest("account/{account_id}/favorite", queryParams, pathParams, body);

        Assert.assertEquals(response[0], statusCodeOK);
        Boolean checkSuccess = jsonParseToBoolean(response[1], "success");
        Integer checkStatusCode = jsonParseToInteger(response[1], "status_code");
        String checkStatusMessage = jsonParseToString(response[1], "status_message");

        Assert.assertEquals(checkSuccess, true);
        Assert.assertEquals(checkStatusCode, 13);
        Assert.assertEquals(checkStatusMessage, "The item/record was deleted successfully.");
    }

    @Test(priority = 8)
    public void removeTvFromWatchList(){
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put(ApiParameters.sessionId, Constants.sessionId);

        HashMap<String, Object> pathParams = new HashMap<>();
        pathParams.put(ApiParameters.accountId, Constants.accountId);

        JSONObject body = new JSONObject();
        body.put("media_type", "tv");
        body.put("media_id", 1439);
        body.put("watchlist", false);

        String[] response = RestOperation.sendPostRequest("account/{account_id}/watchlist", queryParams, pathParams, body);

        Assert.assertEquals(response[0], statusCodeOK);
        Boolean checkSuccess = jsonParseToBoolean(response[1], "success");
        Integer checkStatusCode = jsonParseToInteger(response[1], "status_code");
        String checkStatusMessage = jsonParseToString(response[1], "status_message");

        Assert.assertEquals(checkSuccess, true);
        Assert.assertEquals(checkStatusCode, 13);
        Assert.assertEquals(checkStatusMessage, "The item/record was deleted successfully.");
    }

    @Test(priority = 6)
    public void addMovieToFavoriteList(){
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put(ApiParameters.sessionId, Constants.sessionId);

        HashMap<String, Object> pathParams = new HashMap<>();
        pathParams.put(ApiParameters.accountId, Constants.accountId);

        JSONObject body = new JSONObject();
        body.put("media_type", "movie");
        body.put("media_id", 933260);
        body.put("favorite", true);

        String[] response = RestOperation.sendPostRequest("account/{account_id}/favorite", queryParams, pathParams, body);

        Assert.assertEquals(response[0], statusCodeCreated);
        Boolean checkSuccess = jsonParseToBoolean(response[1], "success");
        Integer checkStatusCode = jsonParseToInteger(response[1], "status_code");
        String checkStatusMessage = jsonParseToString(response[1], "status_message");

        Assert.assertEquals(checkSuccess, true);
        Assert.assertEquals(checkStatusCode, 1);
        Assert.assertEquals(checkStatusMessage, "Success.");
    }

    @Test(priority = 6)
    public void addMovieToWatchList(){
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put(ApiParameters.sessionId, Constants.sessionId);

        HashMap<String, Object> pathParams = new HashMap<>();
        pathParams.put(ApiParameters.accountId, Constants.accountId);

        JSONObject body = new JSONObject();
        body.put("media_type", "movie");
        body.put("media_id", 933260);
        body.put("watchlist", true);

        String[] response = RestOperation.sendPostRequest("account/{account_id}/watchlist", queryParams, pathParams, body);

        Assert.assertEquals(response[0], statusCodeCreated);
        Boolean checkSuccess = jsonParseToBoolean(response[1], "success");
        Integer checkStatusCode = jsonParseToInteger(response[1], "status_code");
        String checkStatusMessage = jsonParseToString(response[1], "status_message");

        Assert.assertEquals(checkSuccess, true);
        Assert.assertEquals(checkStatusCode, 1);
        Assert.assertEquals(checkStatusMessage, "Success.");
    }

    @Test(priority = 7)
    public void favoriteMovie(){
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put(ApiParameters.language, Constants.language);
        queryParams.put(ApiParameters.page, Constants.page);
        queryParams.put(ApiParameters.sessionId, Constants.sessionId);
        queryParams.put(ApiParameters.sortBy, Constants.sortByAsc);

        HashMap<String, Object> pathParams = new HashMap<>();
        pathParams.put(ApiParameters.accountId, Constants.accountId);

        String[] response = RestOperation.sendGetRequest("account/{account_id}/favorite/movies", queryParams, pathParams);

        Assert.assertEquals(response[0], statusCodeOK);
        List<String> checkName = jsonParseToStringList(response[1], "results.title");
        Assert.assertTrue(checkName.toString().contains("The Substance"));
    }

    @Test(priority = 7)
    public void watchlistMovie(){
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put(ApiParameters.language, Constants.language);
        queryParams.put(ApiParameters.page, Constants.page);
        queryParams.put(ApiParameters.sessionId, Constants.sessionId);
        queryParams.put(ApiParameters.sortBy, Constants.sortByAsc);

        HashMap<String, Object> pathParams = new HashMap<>();
        pathParams.put(ApiParameters.accountId, Constants.accountId);

        String[] response = RestOperation.sendGetRequest("account/{account_id}/watchlist/movies", queryParams, pathParams);

        Assert.assertEquals(response[0], statusCodeOK);
        List<String> checkName = jsonParseToStringList(response[1], "results.title");
        Assert.assertTrue(checkName.toString().contains("The Substance"));
    }

    @Test(priority = 8)
    public void removeMovieFromFavoriteList(){
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put(ApiParameters.sessionId, Constants.sessionId);

        HashMap<String, Object> pathParams = new HashMap<>();
        pathParams.put(ApiParameters.accountId, Constants.accountId);

        JSONObject body = new JSONObject();
        body.put("media_type", "movie");
        body.put("media_id", 933260);
        body.put("favorite", false);

        String[] response = RestOperation.sendPostRequest("account/{account_id}/favorite", queryParams, pathParams, body);

        Assert.assertEquals(response[0], statusCodeOK);
        Boolean checkSuccess = jsonParseToBoolean(response[1], "success");
        Integer checkStatusCode = jsonParseToInteger(response[1], "status_code");
        String checkStatusMessage = jsonParseToString(response[1], "status_message");

        Assert.assertEquals(checkSuccess, true);
        Assert.assertEquals(checkStatusCode, 13);
        Assert.assertEquals(checkStatusMessage, "The item/record was deleted successfully.");
    }

    @Test(priority = 8)
    public void removeMovieFromWatchList(){
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put(ApiParameters.sessionId, Constants.sessionId);

        HashMap<String, Object> pathParams = new HashMap<>();
        pathParams.put(ApiParameters.accountId, Constants.accountId);

        JSONObject body = new JSONObject();
        body.put("media_type", "movie");
        body.put("media_id", 933260);
        body.put("watchlist", false);

        String[] response = RestOperation.sendPostRequest("account/{account_id}/watchlist", queryParams, pathParams, body);

        Assert.assertEquals(response[0], statusCodeOK);
        Boolean checkSuccess = jsonParseToBoolean(response[1], "success");
        Integer checkStatusCode = jsonParseToInteger(response[1], "status_code");
        String checkStatusMessage = jsonParseToString(response[1], "status_message");

        Assert.assertEquals(checkSuccess, true);
        Assert.assertEquals(checkStatusCode, 13);
        Assert.assertEquals(checkStatusMessage, "The item/record was deleted successfully.");
    }
}
