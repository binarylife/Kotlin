package com.example.app.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import androidx.appcompat.widget.AppCompatTextView
import com.example.app.R
import com.example.core.utils.Utils
import java.util.*
import java.util.jar.Attributes

class CodeView : AppCompatTextView {

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attr: AttributeSet?) : super(context, attr) {
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f);
        setGravity(Gravity.CENTER);
        setBackgroundColor(getContext().getColor(R.color.colorPrimary));
        setTextColor(Color.WHITE);

        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(getContext().getColor(R.color.colorAccent));
        paint.setStrokeWidth(Utils.dp2px(6f));

        updateCode();
    }

    public fun updateCode() {
//        val intArrayof: IntArray = intArrayOf(1, 2, 3, 4, 5)
        val random: Int = Random().nextInt(codeList.size);
        val code: String = codeList[random];
        setText(code);
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawLine(0f, getHeight().toFloat(), getWidth().toFloat(), 0f, paint);
    }


    private val paint = Paint()

    private val codeList = arrayOf("kotlin",
            "android",
            "java",
            "http",
            "https",
            "okhttp",
            "retrofit",
            "tcp/ip")
}