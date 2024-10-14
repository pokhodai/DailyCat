package com.cat.daily.local.core.uikit.checkbox.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import com.cat.daily.local.core.assist.ext.dp
import com.cat.daily.local.core.uikit.R
import com.cat.daily.local.core.uikit.base.ext.getColor
import com.cat.daily.local.core.uikit.base.value.ColorValue

class CheckboxItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr), CheckboxItem.View {

    private val baseColor = getColor(R.color.textColor0)

    private val containerStrokePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        strokeWidth = DEFAULT_STROKE_WIDTH
        strokeCap = Paint.Cap.ROUND
        isAntiAlias = true
        style = Paint.Style.STROKE
        color = baseColor
    }

    private val containerFillPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        strokeCap = Paint.Cap.ROUND
        isAntiAlias = true
        style = Paint.Style.FILL
        color = Color.TRANSPARENT
    }

    private val checkPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.BLACK
        strokeCap = Paint.Cap.ROUND
        isAntiAlias = true
        color = baseColor
        strokeWidth = DEFAULT_STROKE_WIDTH
    }

    private var isChecked: Boolean = false

    private val realWidth = DEFAULT_WIDTH
    private val realHeight = DEFAULT_HEIGHT

    private val checkPoints1: FloatArray
    private val checkPoints2: FloatArray

    init {
        val pointOneX = 4.dp.toFloat()
        val pointOneY = (realHeight / 2).toFloat()

        val pointTwoX = ((realWidth / 2) - 1.dp).toFloat()
        val pointTwoY = (realHeight - 6.dp).toFloat()

        val pointThreeX = pointTwoX
        val pointThreeY = pointTwoY

        val pointFourX = (realWidth - 4.dp).toFloat()
        val pointFourY = (pointOneY - 3.dp)

        checkPoints1 = floatArrayOf(
            pointOneX,
            pointOneY,
            pointTwoX,
            pointTwoY,
        )
        checkPoints2 = floatArrayOf(
            pointThreeX,
            pointThreeY,
            pointFourX,
            pointFourY,
        )
    }

    private val containerRectF = RectF(
        DEFAULT_STROKE_WIDTH,
        DEFAULT_STROKE_WIDTH,
        realWidth.toFloat() - DEFAULT_STROKE_WIDTH,
        realHeight.toFloat() - DEFAULT_STROKE_WIDTH,
    )

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setMeasuredDimension(realWidth, realHeight)
    }

    override fun bindState(state: CheckboxItem.State) {
        this.isChecked = state.isChecked
        containerFillPaint.color = when(val color = state.backgroundColor) {
            is ColorValue.Color -> color.value
            is ColorValue.Res -> getColor(color.value)
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (isChecked) {
            canvas.drawRoundRect(containerRectF, DEFAULT_RADIUS, DEFAULT_RADIUS, containerFillPaint)
        }
        canvas.drawRoundRect(containerRectF, DEFAULT_RADIUS, DEFAULT_RADIUS, containerStrokePaint)

        if (isChecked) {
            canvas.drawLines(checkPoints1, checkPaint)
            canvas.drawLines(checkPoints2, checkPaint)
        }
    }

    private companion object {
        val DEFAULT_STROKE_WIDTH = (1.1f).dp
        val DEFAULT_WIDTH = 16.dp
        val DEFAULT_HEIGHT = 17.dp
        val DEFAULT_RADIUS = 2.dp.toFloat()
    }
}