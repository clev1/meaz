package com.app.meaz.meaz.Networking.Interfaces;


import com.app.meaz.meaz.Models.Product;
import com.app.meaz.meaz.Models.ProductList;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Clev1 on 3/1/17.
 * ${EMAIL}
 */

public interface FirebaseService {
    @GET("/firebase/product/_search/")
    Call<Object> listProducts(@Query("q") String search);
//    ?q={string}&size=5&pretty=true
}
