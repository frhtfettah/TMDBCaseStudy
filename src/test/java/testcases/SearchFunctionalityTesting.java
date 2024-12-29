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

public class SearchFunctionalityTesting {

    @Test
    public void searchMovies(){
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put(ApiParameters.query, "City");
        queryParams.put(ApiParameters.includeAdult, Constants.includeAdult);
        queryParams.put(ApiParameters.language, Constants.language);
        queryParams.put(ApiParameters.page, Constants.page);

        String[] response = RestOperation.sendGetRequest("search/movie", queryParams);

        Assert.assertEquals(response[0], statusCodeOK);
        List<String> checkTitle = jsonParseToStringList(response[1], "results.title");
        List<Integer> checkOverview = jsonParseToIntegerList(response[1], "results.vote_average");

        Assert.assertEquals(checkTitle.size(), 20);
        Assert.assertEquals(checkOverview.size(), 20);
        Assert.assertTrue(checkTitle.toString().contains("Love in the Big City"));
        Assert.assertTrue(checkOverview.toString().contains("7.2"));
    }

    @Test
    public void searchTV(){
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put(ApiParameters.query, "Suits");
        queryParams.put(ApiParameters.includeAdult, Constants.includeAdult);
        queryParams.put(ApiParameters.language, Constants.language);
        queryParams.put(ApiParameters.page, Constants.page);

        String[] response = RestOperation.sendGetRequest("search/tv", queryParams);

        Assert.assertEquals(response[0], statusCodeOK);
        List<String> checkName = jsonParseToStringList(response[1], "results.name");
        List<Integer> checkOverview = jsonParseToIntegerList(response[1], "results.vote_average");

        Assert.assertEquals(checkName.size(), 20);
        Assert.assertEquals(checkOverview.size(), 20);
        Assert.assertTrue(checkName.toString().contains("Suits"));
        Assert.assertTrue(checkOverview.toString().contains("7.0"));
    }

    @Test
    public void searchKeyword(){
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put(ApiParameters.query, "chris");
        queryParams.put(ApiParameters.page, Constants.page);

        String[] response = RestOperation.sendGetRequest("search/keyword", queryParams);

        Assert.assertEquals(response[0], statusCodeOK);
        List<String> checkName = jsonParseToStringList(response[1], "results.name");
        List<Integer> checkId = jsonParseToIntegerList(response[1], "results.id");

        Assert.assertEquals(checkName.size(), 20);
        Assert.assertEquals(checkId.size(), 20);
        Assert.assertTrue(checkName.toString().contains("christmas special"));
        Assert.assertTrue(checkId.toString().contains("255088"));
    }

    @Test
    public void searchCollection(){
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put(ApiParameters.query, "action");
        queryParams.put(ApiParameters.includeAdult, Constants.includeAdult);
        queryParams.put(ApiParameters.language, Constants.language);
        queryParams.put(ApiParameters.page, Constants.page);

        String[] response = RestOperation.sendGetRequest("search/collection", queryParams);

        Assert.assertEquals(response[0], statusCodeOK);
        List<String> checkName = jsonParseToStringList(response[1], "results.name");
        List<Integer> checkId = jsonParseToIntegerList(response[1], "results.id");
        List<String> checkOverview = jsonParseToStringList(response[1], "results.overview");

        Assert.assertTrue(checkName.toString().contains("Happy Hooligan Live-Action Collection"));
        Assert.assertTrue(checkId.toString().contains("1034934"));
        Assert.assertTrue(checkOverview.toString().contains("Based on Frederick Burr Opper's Happy Hooligan comic strip character."));
    }

    @Test
    public void searchPerson(){
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put(ApiParameters.query, "christoph");
        queryParams.put(ApiParameters.includeAdult, Constants.includeAdult);
        queryParams.put(ApiParameters.language, Constants.language);
        queryParams.put(ApiParameters.page, Constants.page);

        String[] response = RestOperation.sendGetRequest("search/person", queryParams);

        Assert.assertEquals(response[0], statusCodeOK);
        List<String> checkName = jsonParseToStringList(response[1], "results.name");
        List<Integer> checkPopularity = jsonParseToIntegerList(response[1], "results.popularity");
        List<String> checkKnownForTitle = jsonParseToStringList(response[1], "results.known_for.title");

        Assert.assertTrue(checkName.toString().contains("Christopher Lloyd"));
        Assert.assertEquals(checkPopularity.size(), 20);
        Assert.assertTrue(checkKnownForTitle.toString().contains("Back to the Future"));
    }
}
