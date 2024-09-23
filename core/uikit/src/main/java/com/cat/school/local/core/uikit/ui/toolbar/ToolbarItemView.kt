package com.cat.school.local.core.uikit.ui.toolbar

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.Toolbar
import com.cat.school.local.core.uikit.base.IconState
import com.cat.school.local.core.uikit.databinding.ViewToolbarItemBinding
import com.cat.school.local.core.uikit.ext.bindImageOptional
import com.cat.school.local.core.uikit.ext.bindTextOptional

class ToolbarItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : Toolbar(context, attrs, defStyleAttr), ToolbarItem.View {

    private val binding = ViewToolbarItemBinding.inflate(LayoutInflater.from(context), this)

    private var onClickLeading: (() -> Unit)? = null
    private var onClickTrailingText: (() -> Unit)? = onClickLeading

    init {
        layoutParams = LayoutParams(
            MATCH_PARENT,
            WRAP_CONTENT
        )

        setContentInsetsRelative(0, 0)

        binding.toolbarItemLeading.setOnClickListener {
            onClickLeading?.invoke()
        }

        binding.toolbarItemTrailingText.setOnClickListener {
            onClickTrailingText?.invoke()
        }
    }

    override fun bindState(state: ToolbarItem.State) {
        onClickLeading = state.onClickLeading
        onClickTrailingText = state.onClickTrailing
        setBackgroundColor(state.backgroundColorInt ?: Color.TRANSPARENT)
        binding.toolbarItemTitle.bindTextOptional(state.title)
        binding.toolbarItemLeading.bindImageOptional(state.leading)
        binding.toolbarItemTrailingText.bindTextOptional(state.trailingText)
    }

}