package com.app.meaz.meaz.Models;


import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Clev1 on 3/5/17.
 * ${EMAIL}
 */

public class ProductList {
    @SerializedName("_source")
    private List<Product> products;
    private Product product;

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

}
