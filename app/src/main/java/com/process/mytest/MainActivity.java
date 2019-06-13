package com.process.mytest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.process.mytest.activity.FactoryActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * Hello World!
     */
    private Button mBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mBt = (Button) findViewById(R.id.bt);
        mBt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.bt:
                Toast.makeText(this, "skip onclick...", Toast.LENGTH_SHORT).show();
//                toSkip(GitTestActivity.class);
                toSkip(FactoryActivity.class);
                break;
        }
    }

    private void toSkip(Class<?> activity) {
        if (null != activity) {
            Intent intent = new Intent(this, activity);
            startActivity(intent);
        }
    }

}
