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

public class MoviesAPITesting {

    @Test
    public void retrievePopularMovies(){
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put(ApiParameters.language, Constants.language);
        queryParams.put(ApiParameters.page, Constants.page);

        String[] response = RestOperation.sendGetRequest("movie/popular", queryParams);

        Assert.assertEquals(response[0], statusCodeOK);
        List<String> checkTitles = jsonParseToStringList(response[1], "results.original_title");
        List<String> checkOverview = jsonParseToStringList(response[1], "results.overview");
        List<Float> checkVote = jsonParseToFloatList(response[1], "results.vote_average");

        Assert.assertEquals(checkTitles.getFirst(), "Red One");
        Assert.assertEquals(checkOverview.getFirst(), "After Santa Claus (codename: Red One) is kidnapped, " +
                "the North Pole's Head of Security must team up with the world's most infamous tracker in a globe-trotting, action-packed mission to save Christmas.");
        Assert.assertTrue(checkVote.getFirst() > 6f);
    }

    @Test
    public void movieDetails(){
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put(ApiParameters.language, Constants.language);

        HashMap<String, Object> pathParams = new HashMap<>();
        pathParams.put(ApiParameters.movieId, "56831");

        String[] response = RestOperation.sendGetRequest("movie/{movie_id}", queryParams, pathParams);

        Assert.assertEquals(response[0], statusCodeOK);
        String checkTitle = jsonParseToString(response[1], "original_title");
        List<String> checkGenres = jsonParseToStringList(response[1], "genres.name");
        String checkReleaseDate = jsonParseToString(response[1], "release_date");
        String checkIMDBId = jsonParseToString(response[1], "imdb_id");

        Assert.assertEquals(checkTitle, "The Sunset Limited");
        Assert.assertEquals(checkGenres.get(0), "Drama");
        Assert.assertEquals(checkGenres.get(1), "TV Movie");
        Assert.assertEquals(checkReleaseDate, "2011-02-12");
        Assert.assertEquals(checkIMDBId, "tt1510938");
    }
}
