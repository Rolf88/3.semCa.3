/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest.jsonconverter;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import entity.User;

/**
 *
 * @author RolfMoikjær
 */
public class JSONConverter {

    private static JsonObject userToJsonObject(User user) {
        JsonObject obj = new JsonObject();
        obj.addProperty("id", user.getId());
        obj.addProperty("userName", user.getUserName());
        obj.addProperty("password", user.getPassword());
        if (user.getRoles() != null) {
            for (String roles : user.getRoles()) {
                obj.addProperty("roles", roles);

            }
        }
        return obj;
    }

    public static String userToJson(User user) {
        return new Gson().toJson(userToJsonObject(user));
    }
}
