package com.example.customviewexample.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import com.example.customviewexample.R

/**
 * TODO: document your custom view class.
 */
class MyFullCustomView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private var _exampleString: String? = ""
    private var _exampleColor: Int = Color.RED
    private var _exampleDimension: Float = 0f

    /**
     * The text to draw
     */
    var exampleString: String?
        get() = _exampleString
        set(value) {
            _exampleString = value
        }

    /**
     * The font color
     */
    var exampleColor: Int
        get() = _exampleColor
        set(value) {
            _exampleColor = value
        }

    /**
     * In the example view, this dimension is the font size.
     */
    var exampleDimension: Float
        get() = _exampleDimension
        set(value) {
            _exampleDimension = value
        }


    init {
        // Load attributes
        val a = context.obtainStyledAttributes(
            attrs, R.styleable.MyCustomView, defStyleAttr, 0
        )

        _exampleString = a.getString(
            R.styleable.MyCustomView_exampleString
        )
        _exampleColor = a.getColor(
            R.styleable.MyCustomView_exampleColor,
            exampleColor
        )
        // Use getDimensionPixelSize or getDimensionPixelOffset when dealing with
        // values that should fall on pixel boundaries.
        _exampleDimension = a.getDimension(
            R.styleable.MyCustomView_exampleDimension,
            exampleDimension
        )

        a.recycle()

        LayoutInflater.from(context).inflate(R.layout.view_my_full_custom, this)

        val textView = findViewById<TextView>(R.id.myFullCustomViewText)
        textView.text = exampleString
        textView.setTextColor(exampleColor)
        textView.textSize = _exampleDimension
    }
}