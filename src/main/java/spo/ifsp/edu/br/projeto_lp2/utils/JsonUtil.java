package spo.ifsp.edu.br.projeto_lp2.utils;

import org.json.JSONArray;
import org.json.JSONObject;
import spo.ifsp.edu.br.projeto_lp2.models.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JsonUtil {
    public static List<User> getUsersFromJson(String json) throws Exception {
        List<User> users = new ArrayList<>();

        JSONArray usersNode = new JSONObject(json).getJSONArray("results");

        for (int i = 0; i < usersNode.length(); i++) {
            JSONObject jsonUser = usersNode.getJSONObject(i);

            User user = new User();

            user.setGender(jsonUser.getString("gender").charAt(0));
            user.setName(
                    jsonUser.getJSONObject("name").getString("first"),
                    jsonUser.getJSONObject("name").getString("last"),
                    jsonUser.getJSONObject("name").getString("title")
            );
            user.setLocation(
                    jsonUser.getJSONObject("location").getString("street"),
                    jsonUser.getJSONObject("location").getString("city"),
                    jsonUser.getJSONObject("location").getString("state"),
                    String.valueOf(jsonUser.getJSONObject("location").getInt("postcode")),
                    Double.parseDouble(jsonUser.getJSONObject("location").getJSONObject("coordinates").getString("latitude")),
                    Double.parseDouble(jsonUser.getJSONObject("location").getJSONObject("coordinates").getString("longitude"))
            );
            user.setTimezone(
                    jsonUser.getJSONObject("location").getJSONObject("timezone").getString("offset"),
                    jsonUser.getJSONObject("location").getJSONObject("timezone").getString("description")
            );
            user.setEmail(jsonUser.getString("email"));
            user.setBirthday(DateUtil.getDateFromString(jsonUser.getJSONObject("dob").getString("date")));
            user.setRegistered(DateUtil.getDateFromString(jsonUser.getJSONObject("registered").getString("date")));
            user.setTelephoneNumbers(Arrays.asList(jsonUser.getString("phone").split(";")));
            user.setMobilePhoneNumbers(Arrays.asList(jsonUser.getString("cell").split(";")));
            user.setPicture(
                    jsonUser.getJSONObject("picture").getString("large"),
                    jsonUser.getJSONObject("picture").getString("medium"),
                    jsonUser.getJSONObject("picture").getString("thumbnail")
            );
            user.setNationality("BR");

            users.add(user);
        }

        return users;
    }
}
