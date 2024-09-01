package com.cat.school.core.uikit.ui.checkbox

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.FrameLayout
import com.cat.school.core.uikit.databinding.ViewCheckboxCompositeItemBinding
import com.cat.school.core.uikit.ext.applyPadding
import com.cat.school.core.uikit.ext.bindImageOptional
import com.cat.school.core.uikit.ext.bindTextOptional
import com.cat.school.core.uikit.ui.checkbox.view.CheckboxItem

class CheckboxCompositeItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr), CheckboxCompositeItem.View {

    private val binding = ViewCheckboxCompositeItemBinding.inflate(LayoutInflater.from(context), this)

    private var onClickTrailing: (() -> Unit)? = null
    private var onClickCheckbox: (() -> Unit)? = null

    private val checkBoxState = CheckboxItem.State(
        id = "checkbox_composition_item_state",
        isChecked = false,
        checkedBackgroundColorInt = Color.TRANSPARENT
    )

    init {
        layoutParams = ViewGroup.LayoutParams(
            MATCH_PARENT,
            WRAP_CONTENT
        )

        binding.checkboxCompositeItemTrailing.setOnClickListener {
            onClickTrailing?.invoke()
        }

        binding.checkboxItemContentContainer.setOnClickListener {
            onClickCheckbox?.invoke()
        }
    }

    override fun bindState(state: CheckboxCompositeItem.State) {
        onClickTrailing = state.onClickTrailing
        onClickCheckbox = state.onClickCheckbox
        applyPadding(state.padding)
        binding.checkboxCompositeItemName.text = state.name
        binding.checkboxCompositeItemNote.bindTextOptional(state.note)
        binding.checkboxCompositeItemTrailing.bindImageOptional(state.trailingIcon)
        binding.checkboxCompositeItemCheck.bindState(
            checkBoxState.copy(
                isChecked = state.isChecked,
                checkedBackgroundColorInt = state.backgroundColorInt
            )
        )
    }
}