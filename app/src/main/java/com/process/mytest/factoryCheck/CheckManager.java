package com.process.mytest.factoryCheck;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckManager implements Check.OnCheckChangeStatusListener, OnFactoryKeyListener {
    public static final String TAG = "check";
    private Check checking;
    private Context context;
    private List<Check> checkList = new ArrayList<>();
    private Map<String, Boolean> resultMap = new HashMap<>();

    public CheckManager(Context context) {
        this.context = context;
        checkList.add(new WifiCheck(context));
        checkList.add(new VolumeCheck(context));
        checkList.add(new CameraCheck(context));
    }

    public void startCheck() {
        Log.d(TAG, "checkManager startCheck: ...");
        resect();
        next();
    }

    private void resect() {
        checking = null;
        resultMap.clear();
    }

    private void stopCheck() {
        if (null != checking) {
            checking.doStop();
        }
    }

    public void next() {
        int size = checkList.size();
        if (size > 0) {
            stopCheck();
            int currIndex = null != checking ? (checkList.indexOf(checking)+1) : -1;
            if (currIndex >= size ) {
                //end check.
                checking=null;
                Log.d(TAG, "next: Check finish...");
                Log.d(TAG, "check result: " + resultMap);
                Log.d(TAG, "check result toJson: " + new Gson().toJson(resultMap));
                return;
            }
            Check check = checkList.get(currIndex);
            if (null!=check){
                checking = check;
                check.setOnCheckChangeStatusListener(this);
                check.doStart();
            }
        }
    }

    @Override
    public void onCheckChangeStatus(Check check, boolean result) {
        if (null != check) {
            String currCheckName = check.getCurrCheckName();
            Log.d(TAG, "onCheckChangeStatus: Check name: " + currCheckName + " result: " + result);
            resultMap.put(currCheckName, result);
        }
        next();
    }

    @Override
    public boolean onFactoryKeyDown(int keyCode) {
        if (null != checking && checking instanceof OnFactoryKeyListener && ((OnFactoryKeyListener) checking).onFactoryKeyDown(keyCode)) {
            return true;
        }
        switch (keyCode) {
            case NG:
                Log.d(TAG, "onFactoryKeyDown: 消费NG键 " + keyCode);
                return true;
            case PASS:
                Log.d(TAG, "onFactoryKeyDown: 消费PASS键 " + keyCode);
                return true;
        }
        return false;
    }
}
