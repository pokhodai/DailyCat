package com.cat.daily.local.core.uikit.checkbox

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.FrameLayout
import com.cat.daily.local.core.recycler.RecyclerItemView
import com.cat.daily.local.core.uikit.base.ext.applyPadding
import com.cat.daily.local.core.uikit.base.ext.bindImageOptional
import com.cat.daily.local.core.uikit.base.ext.bindTextOptional
import com.cat.daily.local.core.uikit.checkbox.view.CheckboxItem
import com.cat.daily.local.core.uikit.databinding.ViewCheckboxCompositeItemBinding

class CheckboxCompositeItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr), RecyclerItemView<CheckboxCompositeItem.State> {

    private val binding = ViewCheckboxCompositeItemBinding.inflate(LayoutInflater.from(context), this)

    private var state: CheckboxCompositeItem.State? = null

    init {
        layoutParams = ViewGroup.LayoutParams(
            MATCH_PARENT,
            WRAP_CONTENT
        )

        binding.checkboxCompositeItemTrailing.setOnClickListener {
            state?.onClickTrailing?.invoke()
        }

        binding.checkboxItemContentContainer.setOnClickListener {
            state?.onClickCheckbox?.invoke()
        }
    }

    override fun bindState(state: CheckboxCompositeItem.State) {
        this.state = state
        applyPadding(state.padding)
        binding.checkboxCompositeItemName.text = state.name
        binding.checkboxCompositeItemNote.bindTextOptional(state.note)
        binding.checkboxCompositeItemTrailing.bindImageOptional(state.trailingIcon)
        binding.checkboxCompositeItemCheck.bindState(
            CheckboxItem.State(
                id = "checkbox_composition_item_state",
                isChecked = state.isChecked,
                backgroundColor = state.background
            )
        )
    }
}