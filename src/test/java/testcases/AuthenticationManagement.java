package testcases;

import constants.ApiParameters;
import constants.Constants;
import functions.RestOperation;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;

import static constants.Constants.statusCodeOK;
import static functions.JsonOperation.*;

public class AuthenticationManagement {

    @Test
    public void authenticationSuccess(){
        String[] response = RestOperation.sendGetRequest("authentication");

        Assert.assertEquals(response[0], statusCodeOK);
        String check = jsonParseToString(response[1], "success");
        Assert.assertEquals(check, "true");
    }

    @Test
    public void favoriteMovies(){
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put(ApiParameters.language, Constants.language);
        queryParams.put(ApiParameters.page, Constants.page);
        queryParams.put(ApiParameters.sortBy, Constants.sortByAsc);
        queryParams.put(ApiParameters.sessionId, Constants.sessionId);
        queryParams.put(ApiParameters.accountId, Constants.accountId);

        HashMap<String, Object> pathParams = new HashMap<>();
        pathParams.put(ApiParameters.accountId, Constants.accountId);

        String[] response = RestOperation.sendGetRequest("account/{account_id}/favorite/movies", queryParams, pathParams);

        Assert.assertEquals(response[0], statusCodeOK);
        List<String> check = jsonParseToStringList(response[1], "results.original_title");
        String titleFirst = check.get(0);
        String titleSecond = check.get(1);
        String titleThird = check.get(2);
        String titleFourth = check.get(3);
        Assert.assertEquals(titleFirst, "Deadpool & Wolverine");
        Assert.assertEquals(titleSecond, "Inside Out 2");
        Assert.assertEquals(titleThird, "Venom: The Last Dance");
        Assert.assertEquals(titleFourth, "Anna and the King");
    }

    @Test
    public void ratedMovies(){
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put(ApiParameters.language, Constants.language);
        queryParams.put(ApiParameters.page, Constants.page);
        queryParams.put(ApiParameters.sortBy, Constants.sortByAsc);
        queryParams.put(ApiParameters.sessionId, Constants.sessionId);
        queryParams.put(ApiParameters.accountId, Constants.accountId);

        HashMap<String, Object> pathParams = new HashMap<>();
        pathParams.put(ApiParameters.accountId, Constants.accountId);

        String[] response = RestOperation.sendGetRequest("account/{account_id}/rated/movies", queryParams, pathParams);

        Assert.assertEquals(response[0], statusCodeOK);
        List<String> checkTitle = jsonParseToStringList(response[1], "results.original_title");
        String titleFirst = checkTitle.getFirst();
        Assert.assertEquals(titleFirst, "The Shawshank Redemption");
    }

    @Test
    public void lists(){
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put(ApiParameters.page, Constants.page);
        queryParams.put(ApiParameters.sessionId, Constants.sessionId);
        queryParams.put(ApiParameters.accountId, Constants.accountId);

        HashMap<String, Object> pathParams = new HashMap<>();
        pathParams.put(ApiParameters.accountId, Constants.accountId);

        String[] response = RestOperation.sendGetRequest("account/{account_id}/lists", queryParams, pathParams);

        Assert.assertEquals(response[0], statusCodeOK);
        List<String> checkName = jsonParseToStringList(response[1], "results.name");
        Assert.assertTrue(checkName.toString().contains("testtvshows"));
        Assert.assertTrue(checkName.toString().contains("testlist"));
    }
}
