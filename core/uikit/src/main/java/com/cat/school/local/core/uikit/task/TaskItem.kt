package com.cat.school.local.core.uikit.task

import android.content.Context
import android.view.View
import androidx.annotation.ColorInt
import com.cat.school.local.core.recycler.RecyclerState
import com.cat.school.local.core.uikit.checkbox.CheckboxCompositeItem
import com.cat.school.local.core.uikit.base.value.DimensionValue
import com.cat.school.local.core.uikit.base.value.TextValue

class TaskItem {

    data class State(
        val id: String,
        val name: String,
        val paddings: DimensionValue.Rect? = null,
        val startTime: String,
        val endTime: String,
        @ColorInt val taskColorInt: Int,
        val reminder: String? = null,
        val place: String? = null,
        var isExpanded: Boolean = false,
        val missingAssignmentCount: String? = null,
        val assignments: List<CheckboxCompositeItem.State>? = null,
    ): RecyclerState {
        override val provideId: String = id
        override val viewType: Int = TaskItem::class.java.hashCode()
        override fun getView(context: Context): View = TaskItemView(context)
    }
}