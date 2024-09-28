package com.cat.school.local.presentation.task

import androidx.lifecycle.ViewModel
import com.cat.school.local.core.nav.Nav
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TodayViewModel @Inject constructor(
    private val nav: Nav
) : ViewModel() {

    fun onClickFloating() {
        nav.gotoCreateEvent()
    }

}