package com.process.mytest.factoryCheck;

import java.util.Iterator;
import java.util.Set;
import java.util.WeakHashMap;

public class KeyHolder {
    private static final WeakHashMap<OnFactoryKeyListener, Long> weakHashMap = new WeakHashMap<>();

    public void setFactoryKeyListener(OnFactoryKeyListener factoryKeyListener) {
        if (null != factoryKeyListener && !weakHashMap.containsKey(factoryKeyListener)) {
            weakHashMap.put(factoryKeyListener, System.currentTimeMillis());
        }
    }

    public interface OnFactoryKeyListener {
        int NG = 1001;
        int PASS = 1002;

        boolean onFactoryKeyDown(int keyCode);
    }

    public boolean onKeyHolder(int keyCode) {
        switch (keyCode) {
            case 1001:
                return notifyKeyDown(OnFactoryKeyListener.NG);
            case 1002:
                return notifyKeyDown(OnFactoryKeyListener.PASS);
        }
        return false;
    }

    private boolean notifyKeyDown(int keyCode) {
        if (null != weakHashMap && weakHashMap.size() > 0) {
            Set<OnFactoryKeyListener> listenerSet = weakHashMap.keySet();
            Iterator<OnFactoryKeyListener> iterator = listenerSet.iterator();
            while (iterator.hasNext()) {
                OnFactoryKeyListener factoryKeyListener = iterator.next();
                if (null != factoryKeyListener) {
                    factoryKeyListener.onFactoryKeyDown(keyCode);
                }
            }
        }
        return false;
    }

}
