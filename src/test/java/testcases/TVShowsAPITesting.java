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

public class TVShowsAPITesting {

    @Test
    public void popularTVShows(){
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put(ApiParameters.language, Constants.language);
        queryParams.put(ApiParameters.page, Constants.page);

        String[] response = RestOperation.sendGetRequest("tv/popular", queryParams);

        Assert.assertEquals(response[0], statusCodeOK);
        List<String> checkName = jsonParseToStringList(response[1], "results.original_name");
        List<List<Integer>> checkGenre = jsonParseToIntegerArrayList(response[1], "results.genre_ids");
        List<String> checkDate = jsonParseToStringList(response[1], "results.first_air_date");

        List<Integer> genreFirst = checkGenre.getFirst();
        Assert.assertTrue(checkName.toString().contains("Ici tout commence"));
        Assert.assertTrue(genreFirst.toString().contains("10766"));
        Assert.assertTrue(checkDate.toString().contains("2020-11-02"));

    }

    @Test
    public void tvShowDetails(){
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put(ApiParameters.language, Constants.language);

        HashMap<String, Object> pathParams = new HashMap<>();
        pathParams.put(ApiParameters.tv_id, "1438");

        String[] response = RestOperation.sendGetRequest("tv/{tv_id}", queryParams, pathParams);

        Assert.assertEquals(response[0], statusCodeOK);
        Integer checkEpisodeCount = jsonParseToInteger(response[1], "number_of_episodes");
        String checkStatus = jsonParseToString(response[1], "status");
        List<String> checkSeasonAirDate = jsonParseToStringList(response[1], "seasons.air_date");
        List<String> checkSeasonEpisodeCount = jsonParseToStringList(response[1], "seasons.episode_count");

        Assert.assertEquals(checkEpisodeCount, 60);
        Assert.assertEquals(checkStatus, "Ended");
        Assert.assertEquals(checkSeasonAirDate.size(), 6);
        Assert.assertEquals(checkSeasonAirDate.getFirst(), "2006-08-20");
        Assert.assertEquals(checkSeasonAirDate.get(1), "2002-06-02");
        Assert.assertEquals(checkSeasonEpisodeCount.size(), 6);
        Assert.assertEquals(checkSeasonEpisodeCount.get(2), 12);
        Assert.assertEquals(checkSeasonEpisodeCount.get(4), 13);
    }

    @Test
    public void trendingTVShowsWeekly(){
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put(ApiParameters.language, Constants.language);

        String[] response = RestOperation.sendGetRequest("trending/tv/week", queryParams);

        Assert.assertEquals(response[0], statusCodeOK);
        List<String> checkName = jsonParseToStringList(response[1], "results.name");
        List<String> checkDate = jsonParseToStringList(response[1], "results.first_air_date");

        Assert.assertEquals(checkName.size(), 20);
        Assert.assertEquals(checkDate.size(), 20);
        Assert.assertTrue(checkName.toString().contains("Squid Game"));
        Assert.assertTrue(checkDate.toString().contains("2021-09-17"));
    }
}
