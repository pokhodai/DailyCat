package com.cat.school.local.feature.event.create

import android.view.inputmethod.EditorInfo
import androidx.lifecycle.ViewModel
import com.cat.school.local.core.nav.api.LocalNav
import com.cat.school.local.core.uikit.adapter.item.GlobalItem
import com.cat.school.local.core.uikit.ui.toolbar.ToolbarItem
import com.cat.school.local.feature.event.create.mapper.CreateEventMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CreateEventViewModel @Inject constructor(
    private val createEventMapper: CreateEventMapper,
    private val nav: LocalNav,
): ViewModel() {

    private val _toolbarEventFlow = MutableStateFlow<ToolbarItem.State?>(null)
    val toolbarEventFlow = _toolbarEventFlow.asStateFlow()

    private val _listEventFlow = MutableStateFlow<List<GlobalItem>>(emptyList())
    val listEventFlow = _listEventFlow.asStateFlow()

    init {
        _toolbarEventFlow.value = createEventMapper.mapCreateEventToolbarItemState(
            onClickBackPressed = ::onClickBack
        )

        updateItems()
    }

    private fun onChangeFocus(focusId: String) {
       updateItems(focusId)
    }

    private fun updateItems(
        requestFocusId: String? = null
    ) {
        _listEventFlow.value = listOf(
            createEventMapper.mapTextFieldEvent(
                label = "Name",
                hint = "Name",
                focusId = "Place",
                onChangeFocus = ::onChangeFocus,
                onChangeValue = ::onChangeName
            ),
            createEventMapper.mapTextFieldEvent(
                label = "Place",
                hint = "Place",
                imeOption = EditorInfo.IME_ACTION_DONE,
                isRequestFocus = requestFocusId == "Place",
                onChangeFocus = ::onChangeFocus,
                onChangeValue = ::onChangePlace
            )
        )
    }

    private fun onClickBack() {
        nav.pop()
    }

    private fun onChangePlace(place: String) {

    }

    private fun onChangeName(name: String) {

    }
}