package com.cat.school.local.presentation.creater

import androidx.lifecycle.ViewModel
import com.cat.school.local.core.uikit.ui.toolbar.ToolbarItem
import com.cat.school.local.presentation.creater.mappers.AssignmentsCreaterUIMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class AssignmentsCreaterViewModel @Inject constructor(
    private val mapper: AssignmentsCreaterUIMapper,
) : ViewModel() {

    private val _toolbarFlow = MutableStateFlow<ToolbarItem.State?>(null)
    val toolbarFlow = _toolbarFlow.asStateFlow()

    init {
        _toolbarFlow.value = mapper.mapToolbar(::onClickClose)
    }

    private fun onClickClose() {

    }
}