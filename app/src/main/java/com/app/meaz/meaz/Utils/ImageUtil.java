package com.app.meaz.meaz.Utils;


import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

/**
 * Created by Clev1 on 3/7/17.
 * ${EMAIL}
 */

public class ImageUtil {
    final static String TAG = "ImageUtil";
    private String url;
    private ImageView imageView;
    Context context;

    public ImageUtil (String url, ImageView imageView, Context context) {
        this.url = url;
        this.imageView = imageView;
        this.context = context;
    }

    public void setImage() {

        Picasso.Builder builder = new Picasso.Builder(context);
        builder.listener(new Picasso.Listener() {
            @Override
            public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception) {
                exception.printStackTrace();
            }
        });
        builder.build().load(url).resize(120,120).centerCrop().into(imageView);
        Picasso.with(context).setLoggingEnabled(true);
    }


}
