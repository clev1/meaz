package com.app.meaz.meaz.Utils;


import android.util.Log;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
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

    public static void fireBaseSignIn() {
        mAuth = FirebaseAuth.getInstance();
        mAuth.signInAnonymously();
    }
}
