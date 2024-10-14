package com.cat.school.local.core.uikit.toolbar

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.Toolbar
import androidx.core.view.isVisible
import androidx.core.view.postOnAnimationDelayed
import com.cat.school.local.core.uikit.R
import com.cat.school.local.core.uikit.databinding.ViewToolbarItemBinding
import com.cat.school.local.core.uikit.base.ext.bindImageOptional
import com.cat.school.local.core.uikit.base.ext.bindTextOptional
import com.cat.school.core.common.ext.dp
import com.cat.school.local.core.uikit.base.ext.getColor
import com.cat.school.local.core.uikit.base.ext.makeRippleDrawable

class ToolbarItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : Toolbar(context, attrs, defStyleAttr), ToolbarItem.View {

    private val binding = ViewToolbarItemBinding.inflate(LayoutInflater.from(context), this)

    private var state: ToolbarItem.State? = null

    init {
        layoutParams = LayoutParams(
            MATCH_PARENT,
            WRAP_CONTENT
        )

        setContentInsetsAbsolute(0, 0)
        setContentInsetsRelative(0, 0)

        binding.toolbarItemLeadingContainer.setOnClickListener(::onClickLeading)
        binding.toolbarItemTrailingContainer.setOnClickListener(::onCLickTrailingText)

        binding.toolbarItemLeadingContainer.makeRippleDrawable(
            rippleColor = getColor(R.color.actionColor1),
            shapeDrawable = GradientDrawable.OVAL,
        )

        binding.toolbarItemTrailingContainer.makeRippleDrawable(
            rippleColor = getColor(R.color.actionColor1),
            shapeDrawable = GradientDrawable.RECTANGLE,
            cornerRadius = TRAILING_CORNER
        )
    }

    private fun onClickLeading(view: View) {
        view.postOnAnimationDelayed(100) {
            state?.onClickLeading?.invoke()
        }
    }

    private fun onCLickTrailingText(view: View) {
        state?.onClickTrailing?.invoke()
    }

    override fun bindState(state: ToolbarItem.State) {
        this.state = state
        setBackgroundColor(state.backgroundColorInt ?: Color.TRANSPARENT)
        binding.toolbarItemTitle.bindTextOptional(state.title)
        binding.toolbarItemLeading.bindImageOptional(state.leading)
        binding.toolbarItemTrailingText.bindTextOptional(state.trailingText)
        binding.toolbarItemLeadingContainer.isVisible = state.leading != null
        binding.toolbarItemTrailingContainer.isVisible = state.trailingText != null
    }

    private companion object {
        val TRAILING_CORNER = 12.dp.toFloat()
    }
}