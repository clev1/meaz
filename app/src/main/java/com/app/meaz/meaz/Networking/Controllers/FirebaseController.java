package com.app.meaz.meaz.Networking.Controllers;


import android.util.Log;

import com.app.meaz.meaz.Models.Product;
import com.app.meaz.meaz.Models.ProductList;
import com.app.meaz.meaz.Networking.Interfaces.FirebaseService;
import com.app.meaz.meaz.Networking.RestClient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
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
    private final String baseURL = "https://1xm3j9fwzz:q1mlr4ubrx@apple-8534675.us-east-1.bonsaisearch.net";

    public void setSearchText(String s) {

        this.searchText = s;

    }

    public void start() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        OkHttpClient client = initHttpClient();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseURL).addConverterFactory(GsonConverterFactory.create(gson)).client(client)
                .build();
        FirebaseService fs = retrofit.create(FirebaseService.class);
        Call<Object> call = fs.listProducts("Black");
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                if(response.isSuccessful()) {
                    Log.d(TAG, "The request was successful" + response.body());

                }
                else {
                    Log.d(TAG, "The request wasn't successful" + response);
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
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

}
