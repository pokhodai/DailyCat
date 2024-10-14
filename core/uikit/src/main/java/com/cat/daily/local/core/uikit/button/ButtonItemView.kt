package com.cat.daily.local.core.uikit.button

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.FrameLayout
import com.cat.daily.local.core.recycler.RecyclerItemView
import com.cat.daily.local.core.assist.ext.dp
import com.cat.daily.local.core.uikit.base.ext.applyPadding
import com.cat.daily.local.core.uikit.base.ext.bindImageOptional
import com.cat.daily.local.core.uikit.base.ext.getColor
import com.cat.daily.local.core.uikit.base.ext.setBackground
import com.cat.daily.local.core.uikit.base.ext.setWithValue
import com.cat.daily.local.core.uikit.databinding.ViewButtonItemBinding

class ButtonItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr), RecyclerItemView<ButtonItem.State> {

    private val binding = ViewButtonItemBinding.inflate(LayoutInflater.from(context), this)

    private var onClick: (() -> Unit)? = null

    init {
        layoutParams = LayoutParams(
            WRAP_CONTENT,
            WRAP_CONTENT
        )

        binding.buttonItemContentContainer.setOnClickListener {
            onClick?.invoke()
        }
    }

    override fun bindState(state: ButtonItem.State) {
        onClick = state.onClick
        setBackground(state.background)
        applyPadding(state.paddings)
        setWithValue(state.width)
        binding.buttonItemTrailing.scaleType
        binding.buttonItemLeading.bindImageOptional(state.leadingIcon)
        binding.buttonItemTrailing.bindImageOptional(state.trailingIcon)
        binding.buttonItemText.text = state.value
        binding.buttonItemContentContainer.radius = state.type.radius.value.toFloat()
        binding.buttonItemText.setTextColor(getColor(state.type.textColorRes))
        bindType(state.type)
    }

    private fun bindType(type: ButtonItem.Type) = with(binding.buttonItemContentContainer) {
        when(type) {
            is ButtonItem.Type.Primary -> {
                setCardBackgroundColor(getColor(type.buttonColorRes))
                strokeWidth = 0
                strokeColor = Color.TRANSPARENT
            }

            is ButtonItem.Type.Secondary -> {
                setCardBackgroundColor(Color.TRANSPARENT)
                strokeWidth = (1.5f).dp.toInt()
                strokeColor = getColor(type.buttonColorRes)
            }

            is ButtonItem.Type.Tertiary -> {
                setCardBackgroundColor(getColor(type.buttonColorRes))
                strokeWidth = 0
                strokeColor = Color.TRANSPARENT
            }
        }
    }
}