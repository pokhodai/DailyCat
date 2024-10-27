package com.cat.daily.local.presentation.root

import androidx.lifecycle.ViewModel
import com.cat.daily.local.core.router.screen.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import kotlin.math.truncate

@HiltViewModel
class RootViewModel @Inject constructor(
) : ViewModel() {

    private val _changeVisibilityBottomNavFlow = MutableStateFlow<Boolean>(true)
    val changeVisibilityBottomNavFlow = _changeVisibilityBottomNavFlow.asStateFlow()

    fun onChangeVisibilityBottomNavigation(screen: Screen?) {
        _changeVisibilityBottomNavFlow.value = when(screen) {
            is Screen.CreateEvent -> true
            else -> true
        }
    }
}