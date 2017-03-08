package com.app.meaz.meaz.Adapters;


import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.app.meaz.meaz.Models.Hit;
import com.app.meaz.meaz.Models.Hits;
import com.app.meaz.meaz.Models.Source;
import com.app.meaz.meaz.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Clev1 on 3/5/17.
 * ${EMAIL}
 */

public class ProductListAdapter extends ArrayAdapter<Source> {

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
        Source source = getItem(position);
        ViewHolder viewHolder;
        final View result;
        if(convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.product_list_row, parent, false);
            viewHolder.title = (TextView) convertView.findViewById(R.id.list_title);
            result = convertView;
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }
        viewHolder.title.setText(source.getTitle());
        return convertView;
    }

    private static class ViewHolder {
        TextView title;
    }

}
