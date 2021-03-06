package com.app.meaz.meaz.Networking.Controllers;


import android.content.Context;
import android.util.Log;
import android.widget.ListView;

import com.app.meaz.meaz.Adapters.ProductAdapter;
import com.app.meaz.meaz.Adapters.ProductListAdapter;
import com.app.meaz.meaz.Models.Hit;
import com.app.meaz.meaz.Models.Hits;
import com.app.meaz.meaz.Models.PackageList;
import com.app.meaz.meaz.Models.Product;
import com.app.meaz.meaz.Models.Source;
import com.app.meaz.meaz.Networking.Interfaces.FirebaseService;
import com.app.meaz.meaz.Networking.RestClient;
import com.app.meaz.meaz.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Authenticator;
import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Route;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Clev1 on 3/1/17.
 * ${EMAIL}
 */

public class FirebaseController {

    final static String TAG = "FirebaseController";
    private String searchText;
    private OnFirebaseQueryComplete onFirebaseQueryComplete;
    private final String baseURL = "https://1xm3j9fwzz:q1mlr4ubrx@apple-8534675.us-east-1.bonsaisearch.net";

    public FirebaseController(String search) {
        if(search != null) {
            searchText = search;
        }
        else {
            Log.d(TAG, "The value of search is null!");
        }
        this.onFirebaseQueryComplete = null;
        start();
    }

    public void setListOfHits(List<Hit> listOfHits) {

    }

    public List<Hit> getListofHits() {
     return null;
    }

    private void start() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        OkHttpClient client = initHttpClient();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseURL).addConverterFactory(GsonConverterFactory.create(gson)).client(client)
                .build();
        FirebaseService fs = retrofit.create(FirebaseService.class);
        Call<PackageList> call = fs.listProducts(searchText);
        call.enqueue(new Callback<PackageList>() {
            @Override
            public void onResponse(Call<PackageList> call, Response<PackageList> response) {
                if(response.isSuccessful()) {
                    Log.d(TAG, "The request was successful" + response.body());
                    PackageList packageList = response.body();
                    Hits hits = packageList.getHits();
                    List<Hit> list;
                    ArrayList<Source> listOfSources = new ArrayList();
                    list = hits.getHits();
                    for(Hit hit: list) {
                        Source source = hit.getSource();
                        listOfSources.add(source);
                    }
                    Log.d(TAG, "The size of the list is: " + listOfSources.size());
                    if(onFirebaseQueryComplete != null) {
                        onFirebaseQueryComplete.onFirebaseQueryComplete(listOfSources);
                    }

//                    for(Hit hit: hits.getHits()) {
//                        Log.d(TAG, "The value of the hit shows: " + hit.getSource().getTitle());
//                        ProductAdapter productAdapter = new ProductAdapter(context, layoutResourceId, hit.getSource());
//                        productAdapter.setData();
//                    }


                }
                else {
                    Log.d(TAG, "The request wasn't successful" + response);
                }
            }

            @Override
            public void onFailure(Call<PackageList> call, Throwable t) {
                Log.d(TAG, "The onFailure method was called" + t);
                t.printStackTrace();
            }
        });
    }

    public OkHttpClient initHttpClient() {
        final String authToken = Credentials.basic("1xm3j9fwzz","q1mlr4ubrx");
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        okHttpClient.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                Request.Builder requestBuilder = original.newBuilder()
                        .header("Authorization", authToken);
                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });
        OkHttpClient client = okHttpClient.build();
        return client;
    }

    public interface OnFirebaseQueryComplete {
        void onFirebaseQueryComplete(ArrayList<Source> listOfHits);
    }

    public void setOnFirebaseQueryComplete(OnFirebaseQueryComplete onFirebaseQueryComplete) {
        this.onFirebaseQueryComplete = onFirebaseQueryComplete;
    }

}

