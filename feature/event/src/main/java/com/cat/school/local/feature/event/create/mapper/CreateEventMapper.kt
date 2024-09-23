package com.cat.school.local.feature.event.create.mapper

import android.view.inputmethod.EditorInfo
import com.cat.school.core.common.managers.ResManager
import com.cat.school.local.core.uikit.adapter.item.GlobalItem
import com.cat.school.local.core.uikit.base.IconState
import com.cat.school.local.core.uikit.ui.edit.TextFieldItem
import com.cat.school.local.core.uikit.ui.toolbar.ToolbarItem
import javax.inject.Inject
import com.cat.school.local.core.uikit.R as uikitR

class CreateEventMapper @Inject constructor(
    private val resManager: ResManager,
) {

    fun mapCreateEventToolbarItemState(
        onClickBackPressed: () -> Unit,
    ): ToolbarItem.State {
        return ToolbarItem.State(
            id = "create_event_toolbar_id",
            leading = IconState(
                value = uikitR.drawable.ic_chevron_left,
                tint = uikitR.color.actionColor0
            ),
            backgroundColorInt = resManager.getColor(uikitR.color.backgroundColor2),
            onClickLeading = onClickBackPressed
        )
    }

    fun mapTextFieldEvent(
        label: String,
        hint: String,
        focusId: String? = null,
        isRequestFocus: Boolean = false,
        imeOption: Int = EditorInfo.IME_ACTION_NEXT,
        onChangeFocus: (focusId: String) -> Unit,
        onChangeValue: (text: String) -> Unit
    ): GlobalItem {
        return GlobalItem.InputWrap(
            TextFieldItem.State(
                id = label,
                label = label,
                focusId = focusId,
                hint = hint,
                isRequestFocus = isRequestFocus,
                imeOption = imeOption,
                onFocusChanged = onChangeFocus,
                doOnAfterTextChanger = onChangeValue
            )
        )
    }
}