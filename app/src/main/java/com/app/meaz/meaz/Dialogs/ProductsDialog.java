package com.app.meaz.meaz.Dialogs;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.app.meaz.meaz.Adapters.ProductListAdapter;
import com.app.meaz.meaz.Models.Hit;
import com.app.meaz.meaz.Models.Hits;
import com.app.meaz.meaz.Models.Source;
import com.app.meaz.meaz.Networking.Controllers.FirebaseController;

import com.app.meaz.meaz.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Clev1 on 3/5/17.
 * ${EMAIL}
 */

public class ProductsDialog extends DialogFragment {

    private ListView listView;
    final static String TAG = "ProductsDialog";
    public ProductsDialog() {

    }

    public static ProductsDialog newInstance() {

        Bundle args = new Bundle();
        ProductsDialog fragment = new ProductsDialog();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.product_list_dialog, container, false);
        listView = (ListView) v.findViewById(R.id.list);
        FirebaseController firebaseController = new FirebaseController();
        firebaseController.setOnFirebaseQueryComplete(new FirebaseController.OnFirebaseQueryComplete() {
            @Override
            public void onFirebaseQueryComplete(ArrayList<Source> listOfHits) {
                ProductListAdapter productListAdapter = new ProductListAdapter(getContext(), R.layout.product_list_row, listOfHits);
                Log.d(TAG, "The count returns: " + productListAdapter.getCount());
                listView.setAdapter(productListAdapter);

            }
        });

        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

}
