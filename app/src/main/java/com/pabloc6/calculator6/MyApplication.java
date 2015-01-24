package com.pabloc6.calculator6;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by Pablo on 24/1/2015.
 */
public class MyApplication extends Application {

    //    protected static SQLiteOpenHelper database;
    protected SQLUtils sqlUtils;

    private static final String TAG = "DEBUG->SQLUtils";

    @Override
    public void onCreate() {
        super.onCreate();

        final String TAG_LOCAL = TAG + ".onCreate";
        Log.d(TAG_LOCAL, "IN");

        sqlUtils = new SQLUtils(this);

        Log.d("DEBUG->", "Hello application");
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        final String TAG_LOCAL = TAG + ".onTerminate";
        Log.d(TAG_LOCAL, "IN");
        Log.d("DEBUG->", "Goodbye application");
    }
}
