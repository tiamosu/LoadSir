package com.kingja.loadsir.core

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.kingja.loadsir.callback.Callback
import com.kingja.loadsir.callback.SuccessCallback

class LoadService<T>(
        private val convertor: Convertor<T>?,
        val loadLayout: LoadLayout?,
        builder: LoadSir.Builder
) {

    private val handler by lazy { Handler(Looper.getMainLooper()) }

    init {
        initCallback(builder)
    }

    private fun initCallback(builder: LoadSir.Builder) {
        val callbacks = builder.callbackList
        val defaultCallback = builder.defaultCallback
        callbacks.forEach {
            loadLayout?.setupCallback(it)
        }

        if (defaultCallback != null && loadLayout != null) {
            handler.post {
                loadLayout.showCallback(defaultCallback)
            }
        }
    }

    fun showSuccess() {
        loadLayout?.showCallback(SuccessCallback::class.java)
    }

    fun showCallback(callback: Class<out Callback>) {
        loadLayout?.showCallback(callback)
    }

    fun showWithConvertor(t: T) {
        requireNotNull(convertor) { "You haven't set the Convertor." }
        loadLayout?.showCallback(convertor.map(t))
    }

    val currentCallback: Class<out Callback>?
        get() = loadLayout?.currentCallback

    /**
     * obtain rootView if you want keep the toolbar in Fragment
     *
     * @since 1.2.2
     */
    @Deprecated("")
    fun getTitleLoadLayout(context: Context, rootView: ViewGroup, titleView: View): LinearLayout {
        return LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
            val lp = LinearLayout.LayoutParams(-1, -1)
            layoutParams = lp
            rootView.removeView(titleView)
            addView(titleView)
            addView(loadLayout, lp)
        }
    }

    /**
     * modify the callback dynamically
     *
     * @param callback  which callback you want modify(layout, event)
     * @param transport a interface include modify logic
     * @since 1.2.2
     */
    fun setCallBack(callback: Class<out Callback>, transport: Transport?): LoadService<T> {
        loadLayout?.setCallBack(callback, transport)
        return this
    }
}