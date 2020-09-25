package com.kingja.loadsir.target

import android.R
import android.app.Activity
import android.view.ViewGroup
import com.kingja.loadsir.callback.Callback
import com.kingja.loadsir.callback.SuccessCallback
import com.kingja.loadsir.core.LoadLayout

class ActivityTarget : ITarget {

    override fun equals(other: Any?): Boolean {
        return other is Activity
    }

    override fun replaceView(target: Any, onReloadListener: Callback.OnReloadListener?): LoadLayout? {
        val activity = target as? Activity ?: return null
        val childIndex = 0
        val contentParent: ViewGroup? = activity.findViewById(R.id.content)
        val oldContent = contentParent?.getChildAt(childIndex)?.apply {
            contentParent.removeView(this)
        }

        val oldLayoutParams = oldContent?.layoutParams
        val loadLayout = LoadLayout(activity, onReloadListener)
        if (oldContent != null) {
            loadLayout.setupSuccessLayout(SuccessCallback(oldContent, activity, onReloadListener))
        }
        contentParent?.addView(loadLayout, childIndex, oldLayoutParams)
        return loadLayout
    }
}