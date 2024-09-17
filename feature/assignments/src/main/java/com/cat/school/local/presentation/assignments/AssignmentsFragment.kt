package com.cat.school.local.presentation.assignments

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AssignmentsFragment : Fragment() {

    private val viewModel by viewModels<AssignmentsViewModel>()
}