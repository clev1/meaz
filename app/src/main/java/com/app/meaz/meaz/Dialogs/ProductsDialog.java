package com.app.meaz.meaz.Dialogs;


import android.app.Activity;
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
import com.app.meaz.meaz.Interfaces.OnSelectionInterface;
import com.app.meaz.meaz.MainActivity;
import com.app.meaz.meaz.Models.Hit;
import com.app.meaz.meaz.Models.Hits;
import com.app.meaz.meaz.Models.Product;
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

public class ProductsDialog extends DialogFragment implements OnSelectionInterface{

    private ListView listView;
    final static String TAG = "ProductsDialog";
    String search;
    public ProductsDialog() {

    }

    public static ProductsDialog newInstance() {

        Bundle args = new Bundle();
        ProductsDialog fragment = new ProductsDialog();
        fragment.setArguments(args);
        return fragment;
    }

    public static void dismissDialog() {
        dismissDialog();
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
        Bundle bundle = this.getArguments();
        if(bundle != null) {
            search = bundle.getString("search", "Default");
        }
        else {
            Log.d(TAG, "The bundle is null dickhead!");
        }
        FirebaseController firebaseController = new FirebaseController(search);
        firebaseController.setOnFirebaseQueryComplete(new FirebaseController.OnFirebaseQueryComplete() {
            @Override
            public void onFirebaseQueryComplete(ArrayList<Source> listOfHits) {

                ProductListAdapter productListAdapter = new ProductListAdapter(getContext(), R.layout.product_list_row, listOfHits);
                Log.d(TAG, "The count returns: " + productListAdapter.getCount());
                listView.setAdapter(productListAdapter);
                productListAdapter.setTestListener(new ProductListAdapter.testListener() {
                    @Override
                    public void onDataSet(Product product) {
                        Log.d(TAG, "The product selected is: " + product);
                        MainActivity mainActivity = (MainActivity) getActivity();
                        mainActivity.productSelection(product);
                        dismiss();
                    }
                });
            }
        });

        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }


    @Override
    public void setSelectedProduct(Product product) {
        Log.d(TAG, "The product passed back shows: " + product);
    }
}
