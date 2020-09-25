package com.kingja.loadsir.callback

import android.content.Context
import android.view.View

class SuccessCallback(view: View, context: Context, onReloadListener: OnReloadListener?)
    : Callback(view, context, onReloadListener) {

    override fun onCreateView() = 0

    @Deprecated("Use {@link #showWithCallback(boolean successVisible)} instead.",
            ReplaceWith("obtainRootView()?.visibility = View.INVISIBLE", "android.view.View"))
    fun hide() {
        obtainRootView()?.visibility = View.INVISIBLE
    }

    fun show() {
        obtainRootView()?.visibility = View.VISIBLE
    }

    fun showWithCallback(successVisible: Boolean) {
        obtainRootView()?.visibility = if (successVisible) View.VISIBLE else View.INVISIBLE
    }
}