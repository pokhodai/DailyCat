package com.cat.school.core.uikit.ui.badge


import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import com.cat.school.core.uikit.R
import com.cat.school.core.uikit.ext.dp
import com.cat.school.core.uikit.ext.getColor
import com.cat.school.core.uikit.ext.getFont

class BadgeItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr), BadgeItem.View {

    private val textPaint = TextPaint(Paint.ANTI_ALIAS_FLAG).apply {
        textAlign = Paint.Align.CENTER
        typeface = getFont(DEFAULT_FONT)
        textSize = DEFAULT_TEXT_SIZE
        color = getColor(DEFAULT_COLOR)
    }

    private val backgroundPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        color = getColor(DEFAULT_BACKGROUND_COLOR)
    }

    private var text: String = ""
    private var countBadge = CountBadge()

    private val realHeight = DEFAULT_HEIGHT
    private val realHalfHeight = realHeight / 2f

    private val realWidth: Float
        get() = textPaint.measureText(text)
    private val realHalfWidth: Float
        get() = realWidth / 2f

    private val isCircle: Boolean
        get() = realWidth <= realHeight

    override fun bindState(state: BadgeItem.State) {
        text = state.value

        val textX = if (isCircle) {
            realHalfHeight
        } else {
            realHalfWidth
        }
        val textY = realHeight - (realHeight - textPaint.textSize)
        val circleX = realHalfHeight
        val circleY = realHalfHeight
        val radius = realHalfHeight
        val roundRectF = RectF(
            0f,
            0f,
            realWidth,
            realHeight,
        )

        countBadge = CountBadge(
            textX = textX,
            textY = textY,
            circleX = circleX,
            circleY = circleY,
            radius = radius,
            roundRectF = roundRectF
        )

        invalidate()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = if (isCircle) {
            realHeight
        } else {
            realWidth
        }.toInt()
        setMeasuredDimension(width, realHeight.toInt())
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (isCircle) {
            canvas.drawCircle(
                countBadge.circleX,
                countBadge.circleY,
                countBadge.radius,
                backgroundPaint
            )
        } else {
            canvas.drawRoundRect(countBadge.roundRectF, 5f, 5f, backgroundPaint)
        }

        canvas.drawText(text, countBadge.textX, countBadge.textY, textPaint)
    }

    private class CountBadge(
        val textX: Float = 0f,
        val textY: Float = 0f,
        val roundRectF: RectF = RectF(),
        val circleX: Float = 0f,
        val circleY: Float = 0f,
        val radius: Float = 0f,
    )

    private companion object {
        val DEFAULT_TEXT_SIZE = 12.dp.toFloat()
        val DEFAULT_HEIGHT = 16.dp.toFloat()
        val DEFAULT_FONT = R.font.regular
        val DEFAULT_COLOR = R.color.textColor1
        val DEFAULT_BACKGROUND_COLOR = R.color.errorColor0
    }
}