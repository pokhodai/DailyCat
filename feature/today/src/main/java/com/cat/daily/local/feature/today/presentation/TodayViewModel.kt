package com.cat.daily.local.feature.today.presentation

import androidx.lifecycle.ViewModel
import com.cat.daily.local.core.router.Router
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TodayViewModel @Inject constructor(
    private val navRouter: Router
) : ViewModel() {

    fun onClickFloating() {
        navRouter.actionCreateEvent()
    }

}