package jp.peranikov.puhaar.factories;

import org.json.JSONException;
import org.json.JSONObject;

import jp.peranikov.puhaar.models.User;

/**
 * Created by Yuto on 2015/05/10.
 */
public class UserFactory {
    public static User create(JSONObject jsonObject) throws JSONException {
        User user = new User();

        user
            .id(jsonObject.getInt("id"))
            .name(jsonObject.getString("name"))
            .iconUrl(jsonObject.getString("icon_url"));

        return user;
    }
}
