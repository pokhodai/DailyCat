package com.cat.daily.local.feature.event.create

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.cat.daily.local.core.assist.ext.autoClean
import com.cat.daily.local.core.assist.ext.observe
import com.cat.daily.local.core.assist.ext.viewBinding
import com.cat.daily.local.core.router.provider.IScreenRouterProvider
import com.cat.daily.local.core.recycler.adapter.RecyclerAdapter
import com.cat.daily.local.core.router.screen.Screen
import com.cat.daily.local.feature.event.R
import com.cat.daily.local.feature.event.create.decoration.CreaterEventDecoration
import com.cat.daily.local.feature.event.databinding.FragmentCreateEventBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.filterNotNull

@AndroidEntryPoint
class CreateEventFragment : Fragment(R.layout.fragment_create_event), IScreenRouterProvider {

    private val binding by viewBinding { FragmentCreateEventBinding.bind(it) }

    private val viewModel by viewModels<CreateEventViewModel>()

    private val adapter by autoClean { RecyclerAdapter() }

    override fun getScreen(): Screen = Screen.CreateEvent

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