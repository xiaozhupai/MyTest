package com.process.mytest.factoryCheck;

import android.content.Context;
import android.util.Log;

public class WifiCheck extends Check implements OnFactoryKeyListener {

    WifiCheck(Context context) {
        super(context, CHECKWIFI);
    }

    @Override
    void onStart() {
        Log.d(TAG, "onStart: wifi check...");
//        postRunnable(new Runnable() {
//            @Override
//            public void run() {
//                postCheckResult(true);
//            }
//        }, 2 * 1000);
    }

    @Override
    void onStop() {
        Log.d(TAG, "onStop: wifi check...");
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
