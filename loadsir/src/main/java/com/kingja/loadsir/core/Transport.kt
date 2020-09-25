package com.kingja.loadsir.core

import android.content.Context
import android.view.View
import com.kingja.loadsir.callback.Callback

fun interface Transport {

    fun order(context: Context, view: View?, callback: Callback)
}