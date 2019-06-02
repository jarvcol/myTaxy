package test.utils;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;

public class JsonUtilities {

    public static int getUserIdByUserNameFromUserList(String userName, JSONArray userList){
        return getObjectFromListByParameter("username", userName, userList).getInt("id");
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

