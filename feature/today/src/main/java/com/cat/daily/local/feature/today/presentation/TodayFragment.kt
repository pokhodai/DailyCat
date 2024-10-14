package com.cat.daily.local.feature.today.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.cat.daily.local.core.assist.ext.autoClean
import com.cat.daily.local.core.assist.ext.viewBinding
import com.cat.daily.local.core.router.screen.Screen
import com.cat.daily.local.core.router.provider.IScreenRouterProvider
import com.cat.daily.local.core.recycler.adapter.RecyclerAdapter
import com.cat.daily.local.feature.today.R
import com.cat.daily.local.feature.today.databinding.FragmentTodayBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TodayFragment : Fragment(R.layout.fragment_today), IScreenRouterProvider {

    private val binding by viewBinding { FragmentTodayBinding.bind(it) }

    override fun getScreen() = Screen.Today

    private val viewModel by viewModels<TodayViewModel>()
    private val todayDatesAdapter by autoClean { RecyclerAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
        setListeners()
        setObservable()
    }

    private fun setObservable() = with(viewModel) {
    }

    private fun setListeners() = with(binding) {
        todayFloating.setOnClickListener {
            viewModel.onClickFloating()
        }
    }

    private fun setAdapter() {
        binding.todayDates.adapter = todayDatesAdapter
    }
}