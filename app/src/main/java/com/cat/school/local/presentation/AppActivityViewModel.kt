package com.cat.school.local.presentation

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.cat.school.core.common.Keys
import com.cat.school.local.core.model.ScreenKeyEntry
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class AppActivityViewModel @Inject constructor(
) : ViewModel() {

    private val _changeVisibilityBottomNavFlow = MutableStateFlow<Boolean>(true)
    val changeVisibilityBottomNavFlow = _changeVisibilityBottomNavFlow.asStateFlow()

    fun onChangeVisibilityBottomNavigation(screenName: ScreenKeyEntry?) {
        _changeVisibilityBottomNavFlow.value = when(screenName) {
            ScreenKeyEntry.CREATE_EVENT -> false
            else -> true
        }
    }
}