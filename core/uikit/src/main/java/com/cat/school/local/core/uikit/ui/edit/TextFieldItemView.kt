package com.cat.school.local.core.uikit.ui.edit

import android.content.Context
import android.content.res.ColorStateList
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.view.inputmethod.EditorInfo
import android.widget.FrameLayout
import android.widget.TextView
import androidx.core.view.isVisible
import com.cat.school.local.core.uikit.R
import com.cat.school.local.core.uikit.databinding.ViewTextFieldBinding
import com.cat.school.local.core.uikit.ext.applyPadding
import com.cat.school.local.core.uikit.ext.bindTextOptional
import com.cat.school.local.core.uikit.ext.dp
import com.cat.school.local.core.uikit.ext.getColor
import com.cat.school.local.core.uikit.ext.getColorStateList
import com.cat.school.local.core.uikit.ext.getDrawable
import com.cat.school.local.core.uikit.ext.hideKeyboard
import com.google.android.material.textfield.TextInputLayout.END_ICON_CUSTOM
import com.google.android.material.textfield.TextInputLayout.END_ICON_NONE

class TextFieldItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr), TextFieldItem.View, TextWatcher,
    TextView.OnEditorActionListener {

    private val binding = ViewTextFieldBinding.inflate(LayoutInflater.from(context), this)

    private var state: TextFieldItem.State? = null

    init {
        layoutParams = ViewGroup.LayoutParams(
            MATCH_PARENT,
            WRAP_CONTENT
        )
    }

    override fun bindState(state: TextFieldItem.State) {
        this.state = state
        binding.root.applyPadding(state.paddings)
        binding.root.setBackgroundColor(getColor(state.background))
        bindTextChangeListener(state)
        bindStyle(state)
        bindLeading(state)
        bindTrailing(state)
        bindHint(state)
        bindValue(state)
        bindFooter(state)
        bindLabel(state)
        bindRequest(state)
    }

    private fun bindRequest(state: TextFieldItem.State) {
        if (state.isRequestFocus) binding.fieldInput.requestFocus()
        state.isRequestFocus = false
    }

    private fun bindTrailing(state: TextFieldItem.State) {
        val trailingIcon = state.trailingIcon
        binding.fieldInputLayout.endIconMode = if (trailingIcon != null) END_ICON_CUSTOM else END_ICON_NONE
        val endIcon = trailingIcon?.value?.let(::getDrawable)
        endIcon?.setBounds(0, 0, 16.dp, 16.dp)
        binding.fieldInputLayout.endIconDrawable = endIcon
        if (trailingIcon != null && state.onClickEndIcon != null) {
            binding.fieldInputLayout.setEndIconOnClickListener(::setOnEndIconListener)
        } else {
            binding.fieldInputLayout.setEndIconOnClickListener(null)
        }
        binding.fieldInputLayout.setEndIconTintList(trailingIcon?.tint?.let(::getColorStateList))
        binding.fieldInputLayout.refreshEndIconDrawableState()
    }

    private fun bindLeading(state: TextFieldItem.State) {
        val leadingIcon = state.leadingIcon
        val startIcon = leadingIcon?.value?.let(::getDrawable)
        startIcon?.setBounds(0, 0, 16.dp, 16.dp)
        binding.fieldInputLayout.startIconDrawable = startIcon
        if (leadingIcon != null && state.onClickStartIcon != null) {
            binding.fieldInputLayout.setStartIconOnClickListener(::setOnStartIconListener)
        } else {
            binding.fieldInputLayout.setStartIconOnClickListener(null)
        }
        binding.fieldInputLayout.setStartIconTintList(leadingIcon?.tint?.let(::getColorStateList))
        binding.fieldInputLayout.refreshStartIconDrawableState()
    }

    private fun bindLabel(state: TextFieldItem.State) {
        binding.fieldLabel.bindTextOptional(state.label)
    }

    private fun bindFooter(state: TextFieldItem.State) {
        binding.fieldFooter.text = state.footer
        binding.fieldFooter.isVisible = state.condition is TextFieldItem.Condition.Error
    }

    private fun bindValue(state: TextFieldItem.State) {
        binding.fieldInput.setText(state.value)
        binding.fieldInput.setLines(state.lines)
        binding.fieldInput.maxLines = state.maxLines
        binding.fieldInput.isSingleLine = state.isSingleLine
        binding.fieldInput.ellipsize = if (state.isEndEllipsize) TextUtils.TruncateAt.END else null
        binding.fieldInput.inputType = state.inputType
        binding.fieldInput.imeOptions = state.imeOption
        binding.fieldInput.setOnEditorActionListener(this)
    }

    private fun bindHint(state: TextFieldItem.State) {
        binding.fieldInput.hint = state.hint
        binding.fieldInput.setHintTextColor(getColor(R.color.textColorHint))
        binding.fieldInputLayout.isHintEnabled = false
    }

    private fun bindStyle(state: TextFieldItem.State) {
        binding.fieldInputLayout.boxStrokeWidthFocused = 1.dp
        binding.fieldInputLayout.boxStrokeWidth = 1.dp

        val states = arrayOf(
            intArrayOf(android.R.attr.state_enabled),
            intArrayOf(-android.R.attr.state_enabled),
            intArrayOf(android.R.attr.state_focused)
        )

        val borderColor = getColor(state.condition.borderColorRes)
        val focusBorderColor = getColor(state.condition.borderFocusedColorRes)

        val colors = intArrayOf(
            borderColor,
            borderColor,
            focusBorderColor
        )
        binding.fieldInputLayout.boxBackgroundColor = getColor(state.condition.background)
        binding.fieldInputLayout.setBoxStrokeColorStateList(ColorStateList(states, colors))
        binding.fieldInput.setTextColor(getColor(state.condition.textColorRes))
        binding.fieldFooter.setTextColor(getColor(state.condition.borderColorRes))
    }

    private fun bindTextChangeListener(state: TextFieldItem.State) = with(binding.fieldInput) {
        if (state.condition is TextFieldItem.Condition.Disabled) {
            isEnabled = false
            removeTextChangedListener(this@TextFieldItemView)
        } else {
            isEnabled = true
            addTextChangedListener(this@TextFieldItemView)
        }
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

    }

    private fun setOnEndIconListener(v: View) {
        state?.onClickEndIcon?.invoke()
    }

    private fun setOnStartIconListener(v: View) {
        state?.onClickStartIcon?.invoke()
    }

    override fun afterTextChanged(s: Editable?) {
        val value = s.toString()
        state?.value = value
        state?.doOnAfterTextChanger?.invoke(value)
    }

    override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            hideKeyboard()
            clearFocus()
        }

        if(actionId == EditorInfo.IME_ACTION_NEXT) {
            state?.onFocusChanged?.invoke(state?.focusId.orEmpty())
        }

        if (actionId == state?.imeOption) {
            state?.onClickEditorAction?.invoke()
        }

        return true
    }
}