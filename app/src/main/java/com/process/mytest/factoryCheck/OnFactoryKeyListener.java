package com.process.mytest.factoryCheck;

public interface OnFactoryKeyListener {
    int UNKNOWN=-1;
    int NG=1001;
    int PASS=1002;
    boolean onFactoryKeyDown(int keyCode);
}
