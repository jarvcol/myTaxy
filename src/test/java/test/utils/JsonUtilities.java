package test.utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;
import java.util.stream.StreamSupport;
import static java.util.stream.Collectors.toList;

public class JsonUtilities {

    public static List<Integer> getListOfIntegerValuesFromList(String parameterName, JSONArray objectList){
        return StreamSupport.stream(objectList.spliterator(), false)
                .map(JSONObject.class::cast)
                .map(o -> o.getInt(parameterName))
                .collect(toList());
    }

    public static List<String> getListOfStringValuesFromList(String parameterName, JSONArray objectList){
        return StreamSupport.stream(objectList.spliterator(), false)
                .map(JSONObject.class::cast)
                .map(o -> o.getString(parameterName))
                .collect(toList());
    }

    public static JSONObject getObjectFromListByParameter(String parameterName, String parameterValue, JSONArray userList){
        Optional<JSONObject> matchingObject = StreamSupport.stream(userList.spliterator(), false)
                .map(JSONObject.class::cast)
                .filter(o -> o.getString(parameterName).equals(parameterValue))
                .findFirst();
        return matchingObject.orElse(null);
    }

    public static Collection<Object[]> getIterableFromList(List list){
        Collection<Object[]> objList = new ArrayList<Object[]>();
        list.forEach(item -> objList.add(new Object[]{item}));
        return objList;
    }
}

