package com.app.meaz.meaz.Utils;


import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.app.meaz.meaz.Adapters.ProductAdapter;
import com.app.meaz.meaz.Models.Product;
import com.app.meaz.meaz.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;

import static android.content.ContentValues.TAG;

/**
 * Created by Clev1 on 2/18/17.
 * ${EMAIL}
 */

public class DatabaseUtils {

    private static FirebaseAuth mAuth;
    private static DatabaseReference db;
    private static Product product = null;
    private static Context context;

    public DatabaseUtils(Context context) {
        this.context = context;
    }

    public static void fireBaseSignIn() {
        mAuth = FirebaseAuth.getInstance();
        mAuth.signInAnonymously();
    }

    public static void fireBaseInit() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        db = database.getReference();
    }

    public static void fireBaseQuery(String string) {

        Log.d(TAG, "Firebase query initiated");
        Query query = db.orderByChild("part_1").equalTo(string);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    for(DataSnapshot data : dataSnapshot.getChildren()) {
                        product = data.getValue(Product.class);
                        ProductAdapter pd = new ProductAdapter(context, R.layout.activity_main, product);
                        pd.setData();
                    }
                }
                else {
                    Log.d(TAG, "The value didn't exist");
                    Toast.makeText(context, "No results found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, "Query was cancelled");
            }
        });
    }
}
