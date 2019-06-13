package com.process.mytest.factoryCheck;

import android.content.Context;
import android.util.Log;

public class CameraCheck extends Check implements OnFactoryKeyListener {

    CameraCheck(Context context) {
        super(context, CHECKCAMERA);
    }

    @Override
    void onStart() {
        Log.d(TAG, "onStart: camera check...");
        postRunnable(new Runnable() {
            @Override
            public void run() {
                postCheckResult(false);
            }
        }, 50 * 1000);
    }

    @Override
    void onStop() {
        Log.d(TAG, "onStop: camera check...");
    }

    @Override
    public boolean onFactoryKeyDown(int keyCode) {
        switch (keyCode) {
//            case NG:
//                postCheckResult(false);
//                return true;
//            case PASS:
//                postCheckResult(true);
//                return true;
        }
        return false;
    }
}
