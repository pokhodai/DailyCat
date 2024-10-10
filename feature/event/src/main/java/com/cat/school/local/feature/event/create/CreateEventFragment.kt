package com.cat.school.local.feature.event.create

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.cat.school.core.common.ext.autoClean
import com.cat.school.core.common.ext.observe
import com.cat.school.core.common.ext.viewBinding
import com.cat.school.local.core.model.ScreenKeyEntry
import com.cat.school.local.core.provider.FragmentProvider
import com.cat.school.local.core.recycler.adapter.AsyncRecyclerAdapter
import com.cat.school.local.feature.event.R
import com.cat.school.local.feature.event.create.decoration.CreaterEventDecoration
import com.cat.school.local.feature.event.databinding.FragmentCreateEventBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.filterNotNull

@AndroidEntryPoint
class CreateEventFragment : Fragment(R.layout.fragment_create_event), FragmentProvider {

    private val binding by viewBinding { FragmentCreateEventBinding.bind(it) }

    private val viewModel by viewModels<CreateEventViewModel>()

    private val adapter by autoClean { AsyncRecyclerAdapter() }

    override fun getScreenKey(): ScreenKeyEntry = ScreenKeyEntry.CREATE_EVENT

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
        setObservable()
    }

    private fun setAdapter() {
        binding.createEventList.adapter = adapter
        binding.createEventList.addItemDecoration(CreaterEventDecoration())
    }

    private fun setObservable() = with(viewModel) {
        toolbarEventFlow.filterNotNull().observe(
            viewLifecycleOwner,
            binding.createEventToolbar::bindState
        )

        listEventFlow.observe(viewLifecycleOwner, adapter::submitList)
    }
}