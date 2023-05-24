package com.example.proteinbar;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonParserUser {
    private String tagId = "user_id";
    private String tagName = "name";
    private String tagLastName = "lastname";
    private String tagPhone = "phone";
    private String tagAddress = "address";
    private String tagUserName = "username";
    private String tagPasswod = "password";

    public ArrayList<UserModel> jsonParserFunction (String text){
        ArrayList<UserModel> flowers = new ArrayList<>();

        try {
            JSONArray jsonArray = new JSONArray(text);
            for (int i = 0; i < jsonArray.length(); i++) {
                UserModel user = new UserModel();
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                user.setId(jsonObject.getInt(tagId));
                user.setFirstname(jsonObject.getString(tagName));
                user.setLastname(jsonObject.getString(tagLastName));
                user.setUsername(jsonObject.getString(tagUserName));
                user.setPassword(jsonObject.getString(tagPasswod));
                user.setPhone(jsonObject.getString(tagPhone));
                user.setAddress(jsonObject.getString(tagAddress));

                flowers.add(user);
            }
            return flowers;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
