package com.cat.daily.local.core.uikit.calendar

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.view.isInvisible
import com.cat.daily.local.core.recycler.RecyclerItemView
import com.cat.daily.local.core.uikit.base.ext.getColor
import com.cat.daily.local.core.uikit.base.ext.makeRounded
import com.cat.daily.local.core.uikit.base.ext.setWidth
import com.cat.daily.local.core.uikit.base.value.DimensionValue
import com.cat.daily.local.core.uikit.databinding.ViewCalendarItemBinding

class CalendarItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr), RecyclerItemView<CalendarItem.State> {

    private val binding = ViewCalendarItemBinding.inflate(LayoutInflater.from(context), this)

    init {
        binding.calendarItemContainer.makeRounded(DimensionValue.Dp(4))
        setWidth((context.resources.displayMetrics.widthPixels) / 7)
    }

    override fun bindState(state: CalendarItem.State) {
        binding.calendarItemMonth.text = state.month
        binding.calendarItemNumber.text = state.day

        val background = if (state.isSelected) {
            getColor(state.type.colorValue)
        } else {
            Color.TRANSPARENT
        }
        binding.calendarItemContainer.setBackgroundColor(background)

        binding.calendarItemBadge.isInvisible = state.count.isNullOrEmpty()
        binding.calendarItemBadge.bindState(
            com.cat.daily.local.core.uikit.badge.BadgeItem.State(
                id = "calendar_item_badge",
                value = state.count.orEmpty()
            )
        )
    }
}