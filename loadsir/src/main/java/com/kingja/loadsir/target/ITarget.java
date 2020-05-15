package com.kingja.loadsir.target;

import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.core.LoadLayout;

/**
 * Description:TODO
 * Create Time:2019/8/29 0029 下午 2:43
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface ITarget {

    /**
     * @return v1.3.8
     */
    @Override
    boolean equals(Object target);

    /**
     * 1.removeView 2.确定LP 3.addView
     *
     * @return v1.3.8
     */
    LoadLayout replaceView(Object target, Callback.OnReloadListener onReloadListener);
}
