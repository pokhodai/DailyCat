package com.cat.school.local.core.uikit.ui.task

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.FrameLayout
import androidx.annotation.ColorInt
import androidx.core.view.isVisible
import com.cat.school.local.core.uikit.databinding.ViewTaskItemBinding
import com.cat.school.local.core.uikit.ext.applyPadding
import com.cat.school.local.core.uikit.ext.bindTextOptional
import com.cat.school.local.core.uikit.ext.dp
import com.cat.school.local.core.uikit.ext.makeRounded
import com.cat.school.local.core.uikit.ext.setTint
import com.cat.school.local.core.uikit.ui.badge.BadgeItem
import com.cat.school.local.core.uikit.ui.checkbox.CheckboxCompositeItem
import com.cat.school.local.core.uikit.ui.checkbox.CheckboxCompositeItemView

class TaskItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr), TaskItem.View {

    private val binding = ViewTaskItemBinding.inflate(LayoutInflater.from(context), this)

    private var state: TaskItem.State? = null

    private val badgeItemState = BadgeItem.State(
        id = "task_item_badge",
        value = "",
    )

    init {
        layoutParams = LayoutParams(
            MATCH_PARENT,
            WRAP_CONTENT
        )

        binding.taskItemContainer.makeRounded(10.dp)

        binding.taskItemName.setOnClickListener {
            state?.let {
                it.isExpanded = !it.isExpanded
                binding.taskItemExpanded.isVisible = it.isExpanded
            }
        }
    }

    override fun bindState(state: TaskItem.State) {
        this.state = state
        applyPadding(state.paddings)

        bindName(
            name = state.name,
            textColorInt = state.taskColorInt
        )

        binding.taskItemSettings.setTint(state.taskColorInt)
        binding.taskItemDivider.setBackgroundColor(state.taskColorInt)
        binding.taskItemReminder.bindTextOptional(state.reminder)
        binding.taskItemPlace.bindTextOptional(state.place)
        binding.taskItemTimeStart.text = state.startTime
        binding.taskItemTimeEnd.text = state.endTime

        bindMissingAssignment(state.missingAssignmentCount)
        bindAssignments(state.assignments)
        binding.taskItemExpanded.isVisible = state.isExpanded
    }

    private fun bindAssignments(
        checkboxStates: List<CheckboxCompositeItem.State>?
    ) {
        binding.taskItemExpanded.removeAllViews()
        checkboxStates?.forEach { state ->
            val checkBox = CheckboxCompositeItemView(context).apply {
                layoutParams = LayoutParams(
                    MATCH_PARENT,
                    WRAP_CONTENT
                )
                bindState(state)
            }
            binding.taskItemExpanded.addView(checkBox)
        }
    }

    private fun bindName(
        name: String,
        @ColorInt textColorInt: Int
    ) = with(binding.taskItemName) {
        text = name
        setTextColor(textColorInt)
    }

    private fun bindMissingAssignment(assignmentCount: String?) {
        binding.taskMissingAssignmentContainer.isVisible = !assignmentCount.isNullOrEmpty()
        binding.taskItemBadge.bindState(badgeItemState.copy(value = assignmentCount.orEmpty()))
    }
}