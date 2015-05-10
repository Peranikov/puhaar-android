package jp.peranikov.puhaar.factories;

import org.json.JSONException;
import org.json.JSONObject;

import jp.peranikov.puhaar.models.Photo;

/**
 * Created by Yuto on 2015/05/10.
 */
public class PhotoFactory {
    public static Photo create(JSONObject jsonObject) throws JSONException {
        Integer id     = jsonObject.getInt("id");
        String url     = jsonObject.getJSONObject("image").getString("thumb_url");
        String comment = jsonObject.getString("comment");

        Photo photo = new Photo();
        photo
            .id(id)
            .url(url)
            .comment(comment)
            .user(UserFactory.create(jsonObject.getJSONObject("user")));

        return photo;
    }
}
