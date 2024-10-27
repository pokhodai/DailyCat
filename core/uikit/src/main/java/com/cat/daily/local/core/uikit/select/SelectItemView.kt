package com.cat.daily.local.core.uikit.select

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.FrameLayout
import com.cat.daily.local.core.recycler.RecyclerItemView
import com.cat.daily.local.core.uikit.databinding.ViewSelectItemBinding

class SelectItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr), RecyclerItemView<SelectItem.State> {

    private val binding = ViewSelectItemBinding.inflate(LayoutInflater.from(context), this)

    init {
        layoutParams = LayoutParams(
            MATCH_PARENT,
            WRAP_CONTENT,
        )
    }

    override fun bindState(state: SelectItem.State) {
    }
}