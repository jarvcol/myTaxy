package test.utils;

import org.json.JSONArray;
import org.json.JSONObject;

public class JsonUtilities {

    public static int getUserIdByUserNameFromUserList(String userName, JSONArray userList){
        return getObjectFromListByParameter("username", userName, userList).getInt("id");
    }

    private static JSONObject getObjectFromListByParameter(String parameterName, String parameterValue, JSONArray userList){
        JSONObject obj= null;
        for (int i = 0; i < userList.length(); i++){
            obj = userList.getJSONObject(i);
            if (obj.getString(parameterName).equals(parameterValue))
                return obj;
        }
        return null;
    }
}

