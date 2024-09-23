package com.cat.school.core.common.ext

import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity

fun FragmentActivity.toggleKeyboard(isShow: Boolean) {
    val currentFocusedView = this.currentFocus
    currentFocusedView ?: return
    val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    when (isShow) {
        true -> {
            inputMethodManager.showSoftInput(currentFocusedView, InputMethodManager.SHOW_IMPLICIT)
        }
        false -> {
            inputMethodManager.hideSoftInputFromWindow(
                currentFocusedView.windowToken,
                InputMethodManager.HIDE_NOT_ALWAYS
            )
            currentFocusedView.clearFocus()
        }
    }
}

fun FragmentActivity.showKeyboard() = toggleKeyboard(true)
fun FragmentActivity.hideKeyboard() = toggleKeyboard(false)