package com.cat.school.local.core.uikit.ui.calendar

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.view.isInvisible
import com.cat.school.local.core.uikit.ext.dp
import com.cat.school.local.core.uikit.R
import com.cat.school.local.core.uikit.databinding.ViewCalendarItemBinding
import com.cat.school.local.core.uikit.ext.makeRounded
import com.cat.school.local.core.uikit.ext.setNewWidth
import com.cat.school.local.core.uikit.ui.badge.BadgeItem

class CalendarItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr), CalendarItem.View {

    private val binding = ViewCalendarItemBinding.inflate(LayoutInflater.from(context), this)
    private val screenWidth = context.resources.displayMetrics.widthPixels
    private val calendarWidth = screenWidth / 7

    private val badgeItemState = BadgeItem.State(
        id = "calendar_item_badge",
        value = ""
    )

    init {
        binding.calendarItemContainer.makeRounded(4.dp)
    }

    override fun bindState(state: CalendarItem.State) {
        binding.calendarItemMonth.text = state.month
        binding.calendarItemNumber.text = state.day

        val backgroundResId = if (state.isSelected) {
            state.type.selectedColorRes
        } else {
            R.color.transparent
        }
        binding.calendarItemContainer.setBackgroundResource(backgroundResId)

        binding.calendarItemBadge.isInvisible = state.count.isNullOrEmpty()
        binding.calendarItemBadge.bindState(
            badgeItemState.copy(value = state.count.orEmpty())
        )

        setNewWidth(calendarWidth)
    }
}