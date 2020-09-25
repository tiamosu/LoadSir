package com.kingja.loadsir.callback

import android.content.Context
import android.view.View
import java.io.*

abstract class Callback : Serializable {
    @Transient
    private var rootView: View? = null

    @Transient
    private lateinit var context: Context

    private var onReloadListener: OnReloadListener? = null

    /**
     * if return true, the successView will be visible when the view of callback is attached.
     */
    var successVisible = false

    constructor()

    constructor(view: View?, context: Context, onReloadListener: OnReloadListener?) {
        this.rootView = view
        this.context = context
        this.onReloadListener = onReloadListener
    }

    fun setCallback(context: Context, onReloadListener: OnReloadListener?): Callback {
        this.context = context
        this.onReloadListener = onReloadListener
        return this
    }

    fun getRootView(): View? {
        if (rootView != null) {
            return rootView
        }
        rootView = onBuildView(context)

        val resId = onCreateView()
        if (rootView == null && resId > 0) {
            rootView = View.inflate(context, onCreateView(), null)
        }
        rootView?.apply {
            setOnClickListener {
                if (onReloadEvent(context, this)) {
                    return@setOnClickListener
                }
                onReloadListener?.onReload(this)
            }
            onViewCreate(context, this)
        }
        return rootView
    }

    protected open fun onBuildView(context: Context): View? {
        return null
    }

    @Suppress("UNUSED_PARAMETER")
    protected open fun onReloadEvent(context: Context, view: View): Boolean {
        return false
    }

    fun copy(): Callback? {
        var obj: Any? = null
        try {
            val bao = ByteArrayOutputStream()
            val oos = ObjectOutputStream(bao)
            oos.writeObject(this)
            oos.close()
            val bis = ByteArrayInputStream(bao.toByteArray())
            val ois = ObjectInputStream(bis)
            obj = ois.readObject()
            ois.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return obj as? Callback
    }

    /**
     * @since 1.2.2
     */
    fun obtainRootView(): View? {
        if (rootView != null) {
            return rootView
        }
        val resId = onCreateView()
        if (rootView == null && resId > 0) {
            rootView = View.inflate(context, onCreateView(), null)
        }
        return rootView
    }

    fun obtainReloadListener(): OnReloadListener? {
        return onReloadListener
    }

    fun interface OnReloadListener : Serializable {
        fun onReload(v: View)
    }

    protected abstract fun onCreateView(): Int

    /**
     * Called immediately after [onCreateView]
     *
     * @since 1.2.2
     */
    protected open fun onViewCreate(context: Context, view: View) {}

    /**
     * Called when the rootView of Callback is attached to its LoadLayout.
     *
     * @since 1.2.2
     */
    @Suppress("UNUSED_PARAMETER")
    open fun onAttach(context: Context, view: View) {
    }

    /**
     * Called when the rootView of Callback is removed from its LoadLayout.
     *
     * @since 1.2.2
     */
    open fun onDetach() {}
}