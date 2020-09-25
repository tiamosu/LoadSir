package com.kingja.loadsir.target

import com.kingja.loadsir.callback.Callback
import com.kingja.loadsir.core.LoadLayout

interface ITarget {

    /**
     * @return v1.3.8
     */
    override fun equals(other: Any?): Boolean

    /**
     * 1.removeView 2.确定LP 3.addView
     *
     * @return v1.3.8
     */
    fun replaceView(target: Any, onReloadListener: Callback.OnReloadListener?): LoadLayout?
}