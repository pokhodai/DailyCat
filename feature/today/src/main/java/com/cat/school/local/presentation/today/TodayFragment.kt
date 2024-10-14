package com.cat.school.local.presentation.today

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.cat.school.core.common.Keys
import com.cat.school.core.common.ext.autoClean
import com.cat.school.core.common.ext.viewBinding
import com.cat.school.local.core.model.ScreenModel
import com.cat.school.local.core.nav.provider.FragmentNavRouterProvider
import com.cat.school.local.core.recycler.adapter.AsyncRecyclerAdapter
import com.cat.school.local.feature.today.R
import com.cat.school.local.feature.today.databinding.FragmentTodayBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TodayFragment : Fragment(R.layout.fragment_today), FragmentNavRouterProvider {

    private val binding by viewBinding { FragmentTodayBinding.bind(it) }

    override fun getScreen() = ScreenModel.Today

    private val viewModel by viewModels<TodayViewModel>()
    private val todayDatesAdapter by autoClean { AsyncRecyclerAdapter() }

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