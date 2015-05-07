package jp.peranikov.puhaar;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import jp.peranikov.puhaar.models.Photo;
import jp.peranikov.puhaar.views.adapters.PhotoAdapter;


public class MainActivity extends ActionBarActivity {

    ListView photoListView;
    RequestQueue queue;
    PhotoAdapter photoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        request();
    }

    private void request() {
        final String tag_json_obj = "json_obj_req";

        String url = "http://puhaar.jp/api/v1/photos";
        queue = Volley.newRequestQueue(this);
        final ArrayList<Photo> photos = new ArrayList<Photo>();
        photoAdapter = new PhotoAdapter(this, 0, photos, queue);

        photoListView = (ListView)findViewById(R.id.photo_list_view);
        photoListView.setAdapter(photoAdapter);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++ ) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        Integer id = jsonObject.getInt("id");
                        String url = jsonObject.getJSONObject("image").getString("url");
                        String comment  = jsonObject.getString("comment");

                        Photo photo = new Photo();
                        photo
                            .id(id)
                            .image(BitmapFactory.decodeResource(getResources(), R.drawable.abc_ic_ab_back_mtrl_am_alpha))
                            .url(url)
                            .comment(comment);

                        photos.add(photo);

                    } catch (JSONException e) {
                        Log.d("Erorr:", e.getMessage());
                        e.printStackTrace();
                    }
                }
                photoAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(tag_json_obj, "Error: " + error.getMessage());
            }
        });

        AppController.getInstance().addToRequestQueue(jsonArrayRequest, tag_json_obj);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
