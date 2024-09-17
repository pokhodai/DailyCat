package com.cat.school.local.presentation.today

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.cat.school.local.core.uikit.adapter.GlobalAdapter
import com.cat.school.local.feature.today.databinding.FragmentTodayBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TodayFragment : Fragment() {

    private var _binding: FragmentTodayBinding? = null
    private val binding: FragmentTodayBinding
        get() = _binding!!

    private val viewModel by viewModels<TodayViewModel>()
    private var todayDatesAdapter: GlobalAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTodayBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

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
        todayDatesAdapter = GlobalAdapter()
        binding.todayDates.adapter = todayDatesAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        todayDatesAdapter = null
        _binding = null
    }
}