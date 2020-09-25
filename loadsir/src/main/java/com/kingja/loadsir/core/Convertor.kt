package com.kingja.loadsir.core

import com.kingja.loadsir.callback.Callback

fun interface Convertor<T> {

    fun map(t: T): Class<out Callback>
}