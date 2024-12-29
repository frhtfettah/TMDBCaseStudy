package functions;

import io.restassured.path.json.JsonPath;

import java.util.List;

public class JsonOperation {

    public static String jsonParseToString(String responseBody, String parameterToBeParsed){
        JsonPath js = new JsonPath(responseBody);
        return js.get(parameterToBeParsed).toString();
    }

    public static List<String> jsonParseToStringList(String responseBody, String parameterToBeParsed){
        JsonPath js = new JsonPath(responseBody);
        return js.get(parameterToBeParsed);
    }

    public static List<List<String>> jsonParseToStringArrayList(String responseBody, String parameterToBeParsed){
        JsonPath js = new JsonPath(responseBody);
        return js.get(parameterToBeParsed);
    }

    public static List<Float> jsonParseToFloatList(String responseBody, String parameterToBeParsed){
        JsonPath js = new JsonPath(responseBody);
        return js.get(parameterToBeParsed);
    }

    public static List<List<Integer>> jsonParseToIntegerArrayList(String responseBody, String parameterToBeParsed){
        JsonPath js = new JsonPath(responseBody);
        return js.get(parameterToBeParsed);
    }

    public static Integer jsonParseToInteger(String responseBody, String parameterToBeParsed){
        JsonPath js = new JsonPath(responseBody);
        return js.get(parameterToBeParsed);
    }

    public static List<Integer> jsonParseToIntegerList(String responseBody, String parameterToBeParsed){
        JsonPath js = new JsonPath(responseBody);
        return js.get(parameterToBeParsed);
    }

    public static Boolean jsonParseToBoolean(String responseBody, String parameterToBeParsed){
        JsonPath js = new JsonPath(responseBody);
        return js.get(parameterToBeParsed);
    }
}
