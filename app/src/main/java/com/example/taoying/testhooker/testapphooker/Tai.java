package com.example.taoying.testhooker.testapphooker;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class Tai extends Activity {
    private static final String TAG = "Tai";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: aaa");
    }
}
