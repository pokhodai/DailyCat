package com.cat.school.local.feature.event.create

import androidx.lifecycle.ViewModel
import com.cat.school.local.core.nav.api.LocalNav
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

    init {
        _toolbarEventFlow.value = createEventMapper.mapCreateEventToolbarItemState(
            onClickBackPressed = ::onClickBack
        )
    }


    private fun onClickBack() {
        nav.pop()
    }
}