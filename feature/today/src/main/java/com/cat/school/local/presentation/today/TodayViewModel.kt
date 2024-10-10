package com.cat.school.local.presentation.today

import androidx.lifecycle.ViewModel
import com.cat.school.local.core.nav.router.NavRouter
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TodayViewModel @Inject constructor(
    private val navRouter: NavRouter
) : ViewModel() {

    fun onClickFloating() {
        navRouter.gotoCreateEvent()
    }

}