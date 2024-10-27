package com.cat.daily.local.feature.event.create.mapper

import android.view.inputmethod.EditorInfo
import com.cat.daily.local.core.assist.managers.ResManager
import com.cat.daily.local.core.recycler.RecyclerState
import com.cat.daily.local.core.uikit.base.value.ColorValue
import com.cat.daily.local.core.uikit.base.value.ImageValue
import com.cat.daily.local.core.uikit.field.TextFieldItem
import com.cat.daily.local.core.uikit.toolbar.ToolbarItem
import com.cat.daily.local.feature.event.R
import javax.inject.Inject
import com.cat.daily.local.core.uikit.R as uikitR

class CreateEventMapper @Inject constructor(
    private val resManager: ResManager,
) {

    fun mapCreateEventToolbarItemState(
        isSave: Boolean = false,
        onClickTrailing: (() -> Unit)? = null,
        onClickBackPressed: () -> Unit,
    ): ToolbarItem.State {
        val leadingTint = ColorValue.Color(resManager.getColor(uikitR.color.actionColor0))
        val background = ColorValue.Color(resManager.getColor(uikitR.color.backgroundColor2))
        return ToolbarItem.State(
            id = "create_event_toolbar_id",
            trailingText = if (isSave) {
                resManager.getString(R.string.create_event_save)
            } else {
                null
            },
            leading = ImageValue(
                value = uikitR.drawable.ic_chevron_left,
                tint = leadingTint
            ),
            onClickTrailing = onClickTrailing,
            backgroundColor = background,
            onClickLeading = onClickBackPressed
        )
    }

    fun mapTextFieldEvent(
        value: String,
        label: String,
        hint: String,
        focusId: String? = null,
        requestFocusId: String? = null,
        imeOption: Int = EditorInfo.IME_ACTION_NEXT,
        onChangeFocus: (focusId: String) -> Unit,
        onChangeValue: (text: String) -> Unit
    ): RecyclerState {
        return TextFieldItem.State(
            id = label,
            label = label,
            focusId = focusId,
            value = value,
            hint = hint,
            isRequestFocus = label == requestFocusId,
            imeOption = imeOption,
            onFocusChanged = onChangeFocus,
            doOnAfterTextChanger = onChangeValue
        )
    }
}