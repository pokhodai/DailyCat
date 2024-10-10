package com.cat.school.local.core.uikit.ui.button

import android.content.Context
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import com.cat.school.local.core.recycler.RecyclerState
import com.cat.school.local.core.uikit.R
import com.cat.school.local.core.uikit.base.ViewDimension

class ButtonItem {

    data class State(
        val id: String,
        val value: String,
        val type: Type,
        val width: ViewDimension = ViewDimension.WrapContent,
        val paddings: ViewDimension.Rect? = null,
        @ColorInt val backgroundRootInt: Int? = null,
        @DrawableRes val leadingIcon: Int? = null,
        @DrawableRes val trailingIcon: Int? = null,
        val onClick: (() -> Unit)? = null
    ): RecyclerState {
        override val provideId: String = id
        override val viewType: Int = ButtonItem::class.java.hashCode()
        override fun getView(context: Context) = ButtonItemView(context)
    }

    sealed class Type(
        @ColorRes open val textColorRes: Int,
        @ColorRes open val buttonColorRes: Int,
        open val radius: ViewDimension.Dp = ViewDimension.Dp(4),
    ) {
        data class Primary(
            @ColorRes override val textColorRes: Int = R.color.textColor2,
            @ColorRes override val buttonColorRes: Int = R.color.actionColor0,
        ) : Type(
            textColorRes = textColorRes,
            buttonColorRes = buttonColorRes
        )

        data class Secondary(
            @ColorRes override val textColorRes: Int = R.color.textColor0,
            @ColorRes override val buttonColorRes: Int = R.color.actionColor0,
        ) : Type(
            textColorRes = textColorRes,
            buttonColorRes = buttonColorRes
        )

        data class Tertiary(
            @ColorRes override val textColorRes: Int = R.color.textColor0,
            @ColorRes override val buttonColorRes: Int = R.color.transparent,
            override val radius: ViewDimension.Dp = ViewDimension.Dp(0)
        ) : Type(
            textColorRes = textColorRes,
            buttonColorRes = buttonColorRes,
            radius = radius
        )
    }
}