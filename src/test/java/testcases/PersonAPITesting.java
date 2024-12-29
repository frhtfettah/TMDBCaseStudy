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

public class PersonAPITesting {

    @Test
    public void popularPeople(){
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put(ApiParameters.language, Constants.language);
        queryParams.put(ApiParameters.page, Constants.page);

        String[] response = RestOperation.sendGetRequest("person/popular", queryParams);

        Assert.assertEquals(response[0], statusCodeOK);
        List<String> checkNames = jsonParseToStringList(response[1], "results.name");
        List<List<String >> checkOverview = jsonParseToStringArrayList(response[1], "results.known_for.title");

        Assert.assertEquals(checkNames.size(), 20);
        Assert.assertEquals(checkOverview.size(), 20);
        Assert.assertTrue(checkNames.toString().contains("Robert Timothy Smith"));
        Assert.assertTrue(checkOverview.toString().contains("Dear Santa"));
    }

    @Test
    public void movieDetails(){
        HashMap<String, Object> queryParams = new HashMap<>();
        queryParams.put(ApiParameters.language, Constants.language);

        HashMap<String, Object> pathParams = new HashMap<>();
        pathParams.put(ApiParameters.personId, "27319");

        String[] response = RestOperation.sendGetRequest("person/{person_id}", queryParams, pathParams);

        Assert.assertEquals(response[0], statusCodeOK);
        String checkName = jsonParseToString(response[1], "name");
        String checkBirthday = jsonParseToString(response[1], "birthday");
        String checkImdbId = jsonParseToString(response[1], "imdb_id");
        String checkPlaceofBirth = jsonParseToString(response[1], "place_of_birth");
        String checkBiography = jsonParseToString(response[1], "biography");

        Assert.assertEquals(checkName, "Christoph Waltz");
        Assert.assertEquals(checkBirthday, "1956-10-04");
        Assert.assertEquals(checkImdbId, "nm0910607");
        Assert.assertEquals(checkPlaceofBirth, "Vienna, Austria");
        Assert.assertTrue(checkBiography.contains("Austrian actor who also holds German citizenship"));
    }
}
