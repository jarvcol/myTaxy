package test.utils;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;

public class JsonUtilities {

    public static int getUserIdByUserNameFromUserList(String userName, JSONArray userList){
        return getObjectFromListByParameter("username", userName, userList).getInt("id");
    }

    public static List<Integer> getListOfIntegerValuesFromList(String parameterName, JSONArray objectList){
        List<Integer> intList = new ArrayList<Integer>();
        for (int i = 0; i < objectList.length(); i++){
            intList.add(objectList.getJSONObject(i).getInt(parameterName));
        }
        return intList;
    }

    public static List<String> getListOfStringValuesFromList(String parameterName, JSONArray objectList){
        List<String> stringlist = new ArrayList<String>();
        for (int i = 0; i < objectList.length(); i++){
            stringlist.add(objectList.getJSONObject(i).getString(parameterName));
        }
        return stringlist;
    }

    private static JSONObject getObjectFromListByParameter(String parameterName, String parameterValue, JSONArray userList){
        for (int i = 0; i < userList.length(); i++){
            if(compareObjectByParameter(parameterName, parameterValue, userList.getJSONObject(i)))
                return userList.getJSONObject(i);
        }
        return null;
    }

    private static boolean compareObjectByParameter(String parameterName, String parameterValue, JSONObject obj){
        if (obj.getString(parameterName).equals(parameterValue))
            return true;
        else
            return false;
    }
}

