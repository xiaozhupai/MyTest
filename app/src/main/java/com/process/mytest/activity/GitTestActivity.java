package com.process.mytest.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.process.mytest.R;

public class GitTestActivity extends AppCompatActivity {
    public static final String TAG=GitTestActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_git_test);
    }

    private void test1(){
        Log.d(TAG, "test1: 第一次提交");
        Log.d(TAG, "test1: 第二次提交");
        Log.d(TAG, "test1: 第三次提交");
    }

    private void update(){
        Log.d(TAG, "update: 回退到第二次提交进行修改代码");
        Log.d(TAG, "update: 再次回退到这里进行修改");
    }

    private void test2(){
        Log.d(TAG, "test2: 本地提交");
    }
}
