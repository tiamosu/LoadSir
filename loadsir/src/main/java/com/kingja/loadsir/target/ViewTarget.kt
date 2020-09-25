package com.kingja.loadsir.target

import android.view.View
import android.view.ViewGroup
import com.kingja.loadsir.callback.Callback
import com.kingja.loadsir.callback.SuccessCallback
import com.kingja.loadsir.core.LoadLayout

class ViewTarget : ITarget {

    override fun equals(other: Any?): Boolean {
        return other is View
    }

    override fun replaceView(target: Any, onReloadListener: Callback.OnReloadListener?): LoadLayout? {
        val oldContent = target as? View ?: return null
        val contentParent = oldContent.parent as? ViewGroup
        var childIndex = 0
        val childCount = contentParent?.childCount ?: 0
        for (i in 0 until childCount) {
            if (contentParent?.getChildAt(i) === oldContent) {
                childIndex = i
                break
            }
        }
        contentParent?.removeView(oldContent)
        val oldLayoutParams = oldContent.layoutParams
        val loadLayout = LoadLayout(oldContent.context, onReloadListener)
        loadLayout.setupSuccessLayout(SuccessCallback(oldContent, oldContent.context, onReloadListener))
        contentParent?.addView(loadLayout, childIndex, oldLayoutParams)
        return loadLayout
    }
}