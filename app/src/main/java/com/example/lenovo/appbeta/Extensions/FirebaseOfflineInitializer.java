package com.example.lenovo.appbeta.Extensions;

import android.app.Application;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by jeriko on 10/4/17.
 */

public class FirebaseOfflineInitializer extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
    /* Enable disk persistence  */
        if (!FirebaseApp.getApps(this).isEmpty()) {
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        }
    }
}
