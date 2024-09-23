package com.cat.school.local.feature.event.create

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.cat.school.core.common.ext.autoClean
import com.cat.school.core.common.ext.observe
import com.cat.school.core.common.ext.viewBinding
import com.cat.school.local.core.uikit.adapter.GlobalAdapter
import com.cat.school.local.feature.event.R
import com.cat.school.local.feature.event.databinding.FragmentCreateEventBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.filterNotNull

@AndroidEntryPoint
class CreateEventFragment : Fragment(R.layout.fragment_create_event) {

    private val binding by viewBinding { FragmentCreateEventBinding.bind(it) }

    private val viewModel by viewModels<CreateEventViewModel>()

    private val adapter by autoClean { GlobalAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
        setObservable()
    }

    private fun setAdapter() {
        binding.createEventList.adapter = adapter
    }

    private fun setObservable() = with(viewModel) {
        toolbarEventFlow.filterNotNull().observe(
            viewLifecycleOwner,
            binding.createEventToolbar::bindState
        )

        listEventFlow.observe(viewLifecycleOwner, adapter::submitList)
    }
}