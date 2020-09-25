package com.kingja.loadsir.core;

import android.content.Context;
import android.view.View;

import com.kingja.loadsir.callback.Callback;

/**
 * Description:TODO
 * Create Time:2017/9/28 6:45
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface Transport {
    void order(Context context, View view, Callback callback);
}
