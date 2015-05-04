package jp.peranikov.puhaar.views.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import jp.peranikov.puhaar.R;
import jp.peranikov.puhaar.models.Photo;
import jp.peranikov.puhaar.utils.LruBitmapCache;

/**
 * Created by Yuto on 2015/05/04.
 */
public class PhotoAdapter extends ArrayAdapter<Photo> {
    private RequestQueue mQueue;
    private ImageLoader mImageLoader;
    private LayoutInflater mInflater;

    public PhotoAdapter(Context context, int resource, ArrayList<Photo> photos, RequestQueue queue) {
        super(context, resource, photos);
        mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        mImageLoader = new ImageLoader(queue, new LruBitmapCache());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.photo_listview_item, parent, false);

            holder = new ViewHolder(convertView);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }

        Photo photo = (Photo)getItem(position);
        String url = photo.url();

        ImageLoader.ImageListener listener = ImageLoader.getImageListener(
                holder.imageView,
                android.R.drawable.spinner_background,
                android.R.drawable.ic_dialog_alert);
        Log.v("photo", url);


        mImageLoader.get(url, listener);

        return convertView;
    }

    static class ViewHolder {
        ImageView imageView;

        public ViewHolder(View view) {
            this.imageView = (ImageView)view.findViewById(R.id.imageview);
        }
    }
}
