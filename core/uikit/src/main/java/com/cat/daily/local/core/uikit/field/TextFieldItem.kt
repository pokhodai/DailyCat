package com.cat.daily.local.core.uikit.field

import android.content.Context
import android.text.InputType
import android.view.inputmethod.EditorInfo
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import com.cat.daily.local.core.recycler.RecyclerState
import com.cat.daily.local.core.uikit.R
import com.cat.daily.local.core.uikit.base.value.DimensionValue

class TextFieldItem {

    data class State(
        val id: String,
        var value: String? = null,
        val label: String? = null,
        val footer: String? = null,
        val hint: String? = null,
        val focusId: String? = null,
        val paddings: DimensionValue.Rect = DimensionValue.Rect(0, 0, 0, 0),
        @ColorRes val background: Int = R.color.transparent,
        val lines: Int = 1,
        val maxLines: Int = 1,
        val isEndEllipsize: Boolean = false,
        val isSingleLine: Boolean = true,
        val inputType: Int = InputType.TYPE_CLASS_TEXT,
        val imeOption: Int = EditorInfo.IME_NULL,
        val condition: Condition = Condition.Enabled,
        var isRequestFocus: Boolean = false,
        val trailingIcon: Icon? = null,
        val leadingIcon: Icon? = null,
        val onClickEndIcon: (() -> Unit)? = null,
        val onClickStartIcon: (() -> Unit)? = null,
        val doOnAfterTextChanger: ((text: String) -> Unit)? = null,
        val onClickEditorAction: (() -> Unit)? = null,
        val onFocusChanged: ((focusId: String) -> Unit)? = null,
    ) : RecyclerState {
        override val provideId: String = id
        override val viewType: Int = TextFieldItem::class.java.hashCode()
        override fun getView(context: Context) = TextFieldItemView(context)
    }

    data class Icon(
        @DrawableRes val value: Int,
        @ColorRes val tint: Int,
    )

    sealed class Condition(
        @ColorRes val borderColorRes: Int,
        @ColorRes val borderFocusedColorRes: Int,
        @ColorRes val textColorRes: Int,
        @ColorRes val background: Int,
    ) {
        data object Disabled : Condition(
            textColorRes = R.color.textColorHint,
            borderColorRes = R.color.textColorHint,
            borderFocusedColorRes = R.color.textColorHint,
            background = R.color.transparent,
        )

        data object Enabled : Condition(
            textColorRes = R.color.textColor0,
            borderColorRes = R.color.textColor1,
            borderFocusedColorRes = R.color.textColor1,
            background = R.color.transparent,
        )

        data object Success : Condition(
            textColorRes = R.color.textColor0,
            borderColorRes = R.color.textColor1,
            borderFocusedColorRes = R.color.successColor0,
            background = R.color.transparent,
        )

        data object Warning : Condition(
            textColorRes = R.color.textColor0,
            borderColorRes = R.color.textColor1,
            borderFocusedColorRes = R.color.warningColor0,
            background = R.color.transparent,
        )

        data object Error : Condition(
            textColorRes = R.color.textColor0,
            borderColorRes = R.color.textColor1,
            borderFocusedColorRes = R.color.errorColor0,
            background = R.color.transparent,
        )
    }
}