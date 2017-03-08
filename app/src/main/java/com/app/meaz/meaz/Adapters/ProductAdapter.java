package com.app.meaz.meaz.Adapters;


import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.meaz.meaz.MainActivity;
import com.app.meaz.meaz.Models.Product;
import com.app.meaz.meaz.Models.Source;
import com.app.meaz.meaz.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Clev1 on 2/20/17.
 * ${EMAIL}
 */

public class ProductAdapter {
    private final Context context;
    private final Product product;
    private final int layoutResourceId;

    public ProductAdapter(Context context, int layoutResourceId, Product product) {
        this.context = context;
        this.product = product;
        this.layoutResourceId = layoutResourceId;
    }

    public void setData() {
        Activity activity = (Activity) context;
        View view = ((Activity)context).getWindow().getDecorView().findViewById(R.id.activity_main);
        View v = view.findViewById(R.id.activity_main);
        ViewHolder holder = null;

        if(view != null) {

            holder = new ViewHolder();
            holder.title = (TextView)v.findViewById(R.id.product_title);
            holder.frontName = (TextView)v.findViewById(R.id.front_name);
            holder.frontPartNumber = (TextView)v.findViewById(R.id.front_part_number);
            holder.backName = (TextView)v.findViewById(R.id.back_name);
            holder.backPartNumber = (TextView)v.findViewById(R.id.back_part_number);
            holder.threadColor = (TextView)v.findViewById(R.id.thread_color_text);
            holder.imageView = (ImageView)v.findViewById(R.id.image_view);

            Product product = this.product;
            holder.title.setText(product.getTitle());
            holder.frontName.setText(product.getFrontName());
            holder.frontPartNumber.setText(product.getPart_1());
            holder.backName.setText(product.getBackName());
            holder.backPartNumber.setText(product.getBackPart());
            holder.threadColor.setText(product.getThreadColor());
            //        holder.imageView.setImageBitmap(product.getImageURL());

        }

    }

    static class ViewHolder {
        TextView title;
        TextView frontName;
        TextView frontPartNumber;
        TextView backName;
        TextView backPartNumber;
        TextView threadColor;
        ImageView imageView;

    }
}

