package com.app.meaz.meaz.Utils;


import android.util.Log;

import com.app.meaz.meaz.Models.Product;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
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

    public static void fireBaseSignIn() {
        mAuth = FirebaseAuth.getInstance();
        mAuth.signInAnonymously();
    }

    public static void fireBaseInit() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        db = database.getReference();
    }

    public static void fireBaseQuery(String string) {
        db.orderByChild("part_1").equalTo(string).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot data: dataSnapshot.getChildren()){
                    Product product = data.getValue(Product.class);
                    Log.d(TAG, "Value shows: " + product.getTitle());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, "The database error is: " + databaseError);
            }
        });
    }
}
