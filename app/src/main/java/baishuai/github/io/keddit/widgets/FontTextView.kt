package baishuai.github.io.keddit.widgets

import android.content.Context
import android.graphics.Paint
import android.util.AttributeSet
import android.widget.TextView
import baishuai.github.io.keddit.R

/**
 * Extension to TextView that adds support for custom fonts.
 * Created by Bai Shuai on 17/1/22.
 */
class FontTextView : TextView {


    constructor(context: Context)
            : this(context, null)

    constructor(context: Context, attrs: AttributeSet?)
            : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int)
            : super(context, attrs, defStyleAttr) {
        initView(attrs)
    }


    fun initView(attrs: AttributeSet?) {
        val a = context.obtainStyledAttributes(attrs, R.styleable.FontTextView)
        if (a.hasValue(R.styleable.FontTextView_font)) {
            setFont(a.getString(R.styleable.FontTextView_font))
        }
        a.recycle()
    }


    fun setFont(font: String) {
        paintFlags = paintFlags or  Paint.ANTI_ALIAS_FLAG

//        typeface = FontUtil.get(context, font)
    }
}
