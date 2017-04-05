package com.app.meaz.meaz.Networking.Interfaces;


import com.app.meaz.meaz.Models.PackageList;
import com.app.meaz.meaz.Models.Product;


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
    Call<PackageList> listProducts(@Query("q") String search);
//    ?q={string}&size=5&pretty=true
}
