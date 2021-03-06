package com.kingja.loadsir.callback

import android.R
import android.content.Context
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.annotation.StyleRes

class HintCallback(builder: Builder) : Callback() {
    private val title: String?
    private val subTitle: String?
    private val imgResId: Int
    private val titleStyleRes: Int
    private val subTitleStyleRes: Int

    init {
        title = builder.title
        subTitle = builder.subTitle
        imgResId = builder.imgResId
        subTitleStyleRes = builder.subTitleStyleRes
        titleStyleRes = builder.titleStyleRes
    }

    override fun onCreateView() = 0

    override fun onBuildView(context: Context): View? {
        return LinearLayout(context)
    }

    @Suppress("DEPRECATION")
    override fun onViewCreate(context: Context, view: View) {
        val lp = LinearLayout.LayoutParams(-2, -2).apply {
            gravity = Gravity.CENTER
        }
        (view as? LinearLayout)?.apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER

            if (imgResId != -1) {
                val ivImage = ImageView(context)
                ivImage.setBackgroundResource(imgResId)
                addView(ivImage, lp)
            }

            if (title?.isNotBlank() == true) {
                TextView(context).apply {
                    text = title
                    if (titleStyleRes == -1) {
                        setTextAppearance(context, R.style.TextAppearance_Large)
                    } else {
                        setTextAppearance(context, titleStyleRes)
                    }
                    addView(this, lp)
                }
            }

            if (subTitle?.isNotBlank() == true) {
                TextView(context).apply {
                    text = subTitle
                    if (subTitleStyleRes == -1) {
                        setTextAppearance(context, R.style.TextAppearance_Small)
                    } else {
                        setTextAppearance(context, subTitleStyleRes)
                    }
                    addView(this, lp)
                }
            }
        }
    }

    class Builder {
        var title: String? = null
            private set
        var subTitle: String? = null
            private set
        var imgResId = -1
            private set
        var subTitleStyleRes = -1
            private set
        var titleStyleRes = -1
            private set

        fun setHintImg(@DrawableRes imgResId: Int): Builder {
            this.imgResId = imgResId
            return this
        }

        fun setTitle(title: String): Builder {
            return setTitle(title, -1)
        }

        fun setTitle(title: String, @StyleRes titleStyleRes: Int): Builder {
            this.title = title
            this.titleStyleRes = titleStyleRes
            return this
        }

        fun setSubTitle(subTitle: String): Builder {
            return setSubTitle(subTitle, -1)
        }

        fun setSubTitle(subTitle: String, @StyleRes subTitleStyleRes: Int): Builder {
            this.subTitle = subTitle
            this.subTitleStyleRes = subTitleStyleRes
            return this
        }

        fun build(): HintCallback {
            return HintCallback(this)
        }
    }
}