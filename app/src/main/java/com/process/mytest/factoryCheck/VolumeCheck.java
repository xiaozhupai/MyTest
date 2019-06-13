package com.process.mytest.factoryCheck;

import android.content.Context;
import android.util.Log;

public class VolumeCheck extends Check implements OnFactoryKeyListener{

    VolumeCheck(Context context) {
        super(context, CHECKVOLUME);
    }

    @Override
    void onStart() {
        Log.d(TAG, "onStart: volume check...");
//        postRunnable(new Runnable() {
//            @Override
//            public void run() {
//                postCheckResult(false);
//            }
//        }, 2 * 1000);
    }

    @Override
    void onStop() {
        Log.d(TAG, "onStop: volume check...");
    }

    @Override
    public boolean onFactoryKeyDown(int keyCode) {
        switch (keyCode) {
            case NG:
                postCheckResult(false);
                return true;
            case PASS:
                postCheckResult(true);
                return true;
        }
        return false;
    }
}
