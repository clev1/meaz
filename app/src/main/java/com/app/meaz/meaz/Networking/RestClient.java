package com.app.meaz.meaz.Networking;


import com.app.meaz.meaz.Networking.Interfaces.FirebaseService;

import retrofit2.Retrofit;

/**
 * Created by Clev1 on 3/1/17.
 * ${EMAIL}
 */

public class RestClient {

    public Retrofit initClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://vzcdmfd7:n7d9hdamumcldcls@apple-8534675.us-east-1.bonsaisearch.net")
                .build();
        return retrofit;
    }

}
