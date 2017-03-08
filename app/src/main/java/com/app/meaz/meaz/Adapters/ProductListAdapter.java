package com.app.meaz.meaz.Adapters;


import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.meaz.meaz.Dialogs.ProductsDialog;
import com.app.meaz.meaz.Interfaces.OnSelectionInterface;
import com.app.meaz.meaz.Models.Hit;
import com.app.meaz.meaz.Models.Hits;
import com.app.meaz.meaz.Models.Product;
import com.app.meaz.meaz.Models.Source;
import com.app.meaz.meaz.R;
import com.app.meaz.meaz.Utils.ImageUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Clev1 on 3/5/17.
 * ${EMAIL}
 */

public class ProductListAdapter extends ArrayAdapter<Source> {
    OnSelectionInterface selectionInterface;
    final static String TAG = "ProductListAdapter";
    List<Source> items;
    Context context;

    @BindView(R.id.list_title)
    TextView listTitle;

    public ProductListAdapter(Context context, int resource, ArrayList<Source> items) {
        super(context, R.layout.product_list_row,items);
        this.items = items;
        this.context = context;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Source source = getItem(position);
        ViewHolder viewHolder;
        final View result;
        if(convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.product_list_row, parent, false);
            viewHolder.title = (TextView) convertView.findViewById(R.id.list_title);
            viewHolder.image = (ImageButton) convertView.findViewById(R.id.list_image);
            result = convertView;
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }
        StringBuilder newUrl = new StringBuilder(source.getImageURL());
        newUrl.insert(0, "http://");
        ImageUtil imageUtil = new ImageUtil(newUrl.toString(), viewHolder.image, context);
        imageUtil.setImage();
        viewHolder.title.setText(source.getTitle());
        viewHolder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Product product = new Product();
                product.setTitle(source.getTitle());
                product.setFrontName(source.getFrontName());
                product.setPart_1(source.getPart1());
                product.setBackName(source.getBackName());
                product.setBackPart(source.getBackPart());
                product.setThreadColor(source.getThreadColor());
                product.setImageURL(source.getImageURL());

            }
        });
        ButterKnife.bind(this,convertView);
        return convertView;
    }

    private static class ViewHolder {
        TextView title;
        ImageButton image;
    }

}
