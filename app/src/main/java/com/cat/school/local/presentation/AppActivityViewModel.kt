package com.cat.school.local.presentation

import androidx.lifecycle.ViewModel
import com.cat.school.local.core.model.ScreenModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class AppActivityViewModel @Inject constructor(
) : ViewModel() {

    private val _changeVisibilityBottomNavFlow = MutableStateFlow<Boolean>(true)
    val changeVisibilityBottomNavFlow = _changeVisibilityBottomNavFlow.asStateFlow()

    fun onChangeVisibilityBottomNavigation(screen: ScreenModel?) {
        _changeVisibilityBottomNavFlow.value = when(screen) {
            is ScreenModel.CreateEvent -> false
            else -> true
        }
    }
}