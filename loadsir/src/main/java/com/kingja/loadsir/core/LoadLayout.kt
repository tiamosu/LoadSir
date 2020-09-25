package com.kingja.loadsir.core

import android.content.Context
import android.view.ViewGroup
import android.widget.FrameLayout
import com.kingja.loadsir.LoadSirUtil
import com.kingja.loadsir.callback.Callback
import com.kingja.loadsir.callback.SuccessCallback
import java.util.*

class LoadLayout(context: Context) : FrameLayout(context) {
    private val callbacks: MutableMap<Class<out Callback>, Callback?> = HashMap()
    private var onReloadListener: Callback.OnReloadListener? = null
    private var preCallback: Class<out Callback>? = null
    var currentCallback: Class<out Callback>? = null
        private set

    constructor(context: Context, onReloadListener: Callback.OnReloadListener?) : this(context) {
        this.onReloadListener = onReloadListener
    }

    fun setupSuccessLayout(callback: Callback) {
        addCallback(callback)
        callback.getRootView()?.apply {
            visibility = INVISIBLE
            addView(this, ViewGroup.LayoutParams(-1, -1))
        }
        currentCallback = SuccessCallback::class.java
    }

    fun setupCallback(callback: Callback) {
        callback.copy()?.apply {
            setCallback(context, onReloadListener)
            addCallback(this)
        }
    }

    private fun addCallback(callback: Callback) {
        if (!callbacks.containsKey(callback.javaClass)) {
            callbacks[callback.javaClass] = callback
        }
    }

    fun showCallback(callback: Class<out Callback>) {
        checkCallbackExist(callback)
        if (LoadSirUtil.isMainThread) {
            showCallbackView(callback)
        } else {
            postToMainThread(callback)
        }
    }

    private fun postToMainThread(status: Class<out Callback>) {
        post { showCallbackView(status) }
    }

    private fun showCallbackView(status: Class<out Callback>) {
        if (preCallback != null) {
            if (preCallback == status) {
                return
            }
            callbacks[preCallback]?.onDetach()
        }
        if (childCount > 1) {
            removeViewAt(CALLBACK_CUSTOM_INDEX)
        }
        for (key in callbacks.keys) {
            if (key == status) {
                val successCallback = callbacks[SuccessCallback::class.java] as? SuccessCallback
                if (key == SuccessCallback::class.java) {
                    successCallback?.show()
                } else {
                    val callback = callbacks[key]
                    if (callback != null) {
                        successCallback?.showWithCallback(callback.successVisible)
                        callback.getRootView()?.apply {
                            addView(this)
                            callback.onAttach(context, this)
                        }
                    }
                }
                preCallback = status
            }
        }
        currentCallback = status
    }

    fun setCallBack(callback: Class<out Callback>, transport: Transport?) {
        if (transport == null) {
            return
        }
        checkCallbackExist(callback)
        val tempCallback = callbacks[callback]
        if (tempCallback != null) {
            transport.order(context, tempCallback.obtainRootView(), tempCallback)
        }
    }

    private fun checkCallbackExist(callback: Class<out Callback>) {
        require(callbacks.containsKey(callback)) {
            String.format("The Callback (%s) is nonexistent.", callback.simpleName)
        }
    }

    companion object {
        private const val CALLBACK_CUSTOM_INDEX = 1
    }
}