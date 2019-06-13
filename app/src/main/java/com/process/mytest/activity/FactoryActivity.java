package com.process.mytest.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.process.mytest.R;
import com.process.mytest.factoryCheck.CheckManager;
import com.process.mytest.factoryCheck.KeyHolder;
import com.process.mytest.factoryCheck.OnFactoryKeyListener;

public class FactoryActivity extends AppCompatActivity implements View.OnClickListener, KeyHolder.OnFactoryKeyListener {
    public static final String TAG = "check";
    private CheckManager checkManager;
    /**
     * NG
     */
    private Button mBtNg;
    /**
     * PASS
     */
    private Button mBtPass;

    private KeyHolder keyHolder = new KeyHolder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factory);
        initView();
        checkManager = new CheckManager(this);
        keyHolder.setFactoryKeyListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (null != checkManager) {
            checkManager.startCheck();
        }
    }

    private void initView() {
        mBtNg = (Button) findViewById(R.id.bt_ng);
        mBtNg.setOnClickListener(this);
        mBtPass = (Button) findViewById(R.id.bt_pass);
        mBtPass.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int keyCode;
        switch (v.getId()) {
            default:
                keyCode = OnFactoryKeyListener.UNKNOWN;
                break;
            case R.id.bt_ng:
                keyCode = OnFactoryKeyListener.NG;
                break;
            case R.id.bt_pass:
                keyCode = OnFactoryKeyListener.PASS;
                break;
        }
        Log.d(TAG, "onClick: keyCode= " + keyCode);
        if (null != checkManager) {
            checkManager.onFactoryKeyDown(keyCode);
        }
    }

    @Override
    public boolean onFactoryKeyDown(int keyCode) {
        if (null != checkManager) {
            return checkManager.onFactoryKeyDown(keyCode);
        }
        return false;
    }
}
