package com.cat.school.local.feature.event.create.mapper

import android.view.inputmethod.EditorInfo
import com.cat.school.core.common.managers.ResManager
import com.cat.school.local.core.recycler.RecyclerState
import com.cat.school.local.core.uikit.base.value.ImageValue
import com.cat.school.local.core.uikit.field.TextFieldItem
import com.cat.school.local.core.uikit.toolbar.ToolbarItem
import com.cat.school.local.feature.event.R
import javax.inject.Inject
import com.cat.school.local.core.uikit.R as uikitR

class CreateEventMapper @Inject constructor(
    private val resManager: ResManager,
) {

    fun mapCreateEventToolbarItemState(
        isSave: Boolean = false,
        onClickTrailing: (() -> Unit)? = null,
        onClickBackPressed: () -> Unit,
    ): ToolbarItem.State {
        return ToolbarItem.State(
            id = "create_event_toolbar_id",
            trailingText = if (isSave) {
                resManager.getString(R.string.create_event_save)
            } else {
                null
            },
            leading = ImageValue(
                value = uikitR.drawable.ic_chevron_left,
                tint = uikitR.color.actionColor0
            ),
            onClickTrailing = onClickTrailing,
            backgroundColorInt = resManager.getColor(uikitR.color.backgroundColor2),
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