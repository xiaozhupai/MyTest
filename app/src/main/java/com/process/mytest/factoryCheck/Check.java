package com.process.mytest.factoryCheck;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

public abstract class Check {
    public static final String TAG = "check";
    public static final String CHECKWIFI = "wifi";
    public static final String CHECKVOLUME = "volume";
    public static final String CHECKCAMERA = "camera";
    private Context context;
    private String currCheckName;
    private boolean startChecking;
    public OnCheckChangeStatusListener onCheckChangeStatusListener;
    public Handler handler = new Handler(Looper.getMainLooper());

    public interface OnCheckChangeStatusListener {
        void onCheckChangeStatus(Check check, boolean result);
    }

    public void setOnCheckChangeStatusListener(OnCheckChangeStatusListener onCheckChangeStatusListener) {
        this.onCheckChangeStatusListener = onCheckChangeStatusListener;
    }

    public Check(Context context, String currCheckName) {
        this.context = context;
        this.currCheckName = currCheckName;
    }

    public void postRunnable(Runnable runnable) {
        if (null != handler && null != runnable) {
            handler.post(runnable);
        }
    }

    public void postRunnable(Runnable runnable, long delay) {
        if (null != handler && null != runnable) {
            handler.postDelayed(runnable, delay > 0 ? delay : 0);
        }
    }

    public String getCurrCheckName() {
        return currCheckName;
    }

    public Context getContext() {
        return context;
    }

    void doStart() {
        if (!startChecking) {
            startChecking = true;
            onStart();
        }
    }

    void doStop() {
        if (startChecking) {
            startChecking = false;
            onStop();
        }
    }

    abstract void onStart();

    abstract void onStop();

    public enum END {
        NG,
        PASS,
        HAND
    }

    public void postCheckResult(boolean pass) {
        postCheckResult(pass ? END.PASS : END.NG);
    }

    public void postCheckResult(END end) {
        boolean result = false;
        switch (end) {
            case NG:
                result = false;
                break;
            case PASS:
                result = true;
                break;
            case HAND:
                result = false;
                break;
        }
        if (null != onCheckChangeStatusListener) {
            onCheckChangeStatusListener.onCheckChangeStatus(this, result);
        }
    }
}
