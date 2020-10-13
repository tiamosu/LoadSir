package com.kingja.loadsir.core

import com.kingja.loadsir.LoadSirUtil
import com.kingja.loadsir.callback.Callback
import com.kingja.loadsir.target.ActivityTarget
import com.kingja.loadsir.target.ITarget
import com.kingja.loadsir.target.ViewTarget
import java.util.*

class LoadSir {
    private var builder: Builder

    private constructor() {
        this.builder = Builder()
    }

    private fun setBuilder(builder: Builder) {
        this.builder = builder
    }

    private constructor(builder: Builder) {
        this.builder = builder
    }

    @JvmOverloads
    fun <T> register(target: Any,
                     onReloadListener: Callback.OnReloadListener?,
                     convertor: Convertor<T>? = null
    ): LoadService<T> {
        val targetContext = LoadSirUtil.getTargetContext(target, builder.getTargetContextList())
        val loadLayout = targetContext.replaceView(target, onReloadListener)
        return LoadService(convertor, loadLayout, builder)
    }

    class Builder {
        val callbackList: MutableList<Callback> = ArrayList()
        private val targetContextList: MutableList<ITarget> = ArrayList()
        var defaultCallback: Class<out Callback>? = null
            private set

        init {
            targetContextList.add(ActivityTarget())
            targetContextList.add(ViewTarget())
        }

        fun addCallback(callback: Callback): Builder {
            callbackList.add(callback)
            return this
        }

        /**
         * @return Builder
         * @since 1.3.8
         */
        fun addTargetContext(targetContext: ITarget): Builder {
            targetContextList.add(targetContext)
            return this
        }

        fun getTargetContextList(): List<ITarget> {
            return targetContextList
        }

        fun setDefaultCallback(defaultCallback: Class<out Callback>): Builder {
            this.defaultCallback = defaultCallback
            return this
        }

        fun getCallbacks(): List<Callback> {
            return callbackList
        }

        fun commit() {
            default.setBuilder(this)
        }

        fun build(): LoadSir {
            return LoadSir(this)
        }
    }

    companion object {
        val default by lazy { LoadSir() }

        @JvmStatic
        fun beginBuilder(): Builder {
            return Builder()
        }
    }
}