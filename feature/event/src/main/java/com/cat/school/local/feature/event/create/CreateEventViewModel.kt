package com.cat.school.local.feature.event.create

import android.view.inputmethod.EditorInfo
import androidx.lifecycle.ViewModel
import com.cat.school.core.common.managers.ResManager
import com.cat.school.local.core.nav.router.NavRouter
import com.cat.school.local.core.recycler.RecyclerState
import com.cat.school.local.core.uikit.toolbar.ToolbarItem
import com.cat.school.local.feature.event.R
import com.cat.school.local.feature.event.create.mapper.CreateEventMapper
import com.cat.school.local.feature.event.create.states.CreateEventErrorState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CreateEventViewModel @Inject constructor(
    private val createEventMapper: CreateEventMapper,
    private val navRouter: NavRouter,
    private val resManager: ResManager,
): ViewModel() {

    private val _toolbarEventFlow = MutableStateFlow<ToolbarItem.State?>(null)
    val toolbarEventFlow = _toolbarEventFlow.asStateFlow()

    private val _listEventFlow = MutableStateFlow<List<RecyclerState>>(emptyList())
    val listEventFlow = _listEventFlow.asStateFlow()

    private var errorState = CreateEventErrorState()

    init {
        updateToolbar()
        updateItems()
    }

    private fun updateToolbar() {
        val name = ""
        val isSave = name.trim().isNotEmpty()
        _toolbarEventFlow.value = createEventMapper.mapCreateEventToolbarItemState(
            isSave = isSave,
            onClickTrailing = ::onClickSave,
            onClickBackPressed = ::onClickBack
        )
    }

    private fun onChangeFocus(focusId: String) {
       updateItems(focusId)
    }

    private fun updateItems(
        requestFocusId: String? = null
    ) {
        val labelName = resManager.getString(R.string.create_event_label_name)
        val hintName = resManager.getString(R.string.create_event_hint_name)
        val labelPlace = resManager.getString(R.string.create_event_label_place)
        val hintPlace = resManager.getString(R.string.create_event_hint_place)
        _listEventFlow.value = listOf(
            createEventMapper.mapTextFieldEvent(
                value = "",
                label = labelName,
                hint = hintName,
                focusId = labelPlace,
                requestFocusId = requestFocusId,
                onChangeFocus = ::onChangeFocus,
                onChangeValue = ::onChangeName
            ),
            createEventMapper.mapTextFieldEvent(
                value = "",
                label = labelPlace,
                hint = hintPlace,
                imeOption = EditorInfo.IME_ACTION_DONE,
                requestFocusId = requestFocusId,
                onChangeFocus = ::onChangeFocus,
                onChangeValue = ::onChangePlace
            ),
        )
    }

    private fun onClickSave() {
        
    }

    private fun onClickBack() {
        navRouter.pop()
    }

    private fun onChangePlace(place: String) {
        updateToolbar()
    }

    private fun onChangeName(name: String) {
        updateToolbar()
    }
}