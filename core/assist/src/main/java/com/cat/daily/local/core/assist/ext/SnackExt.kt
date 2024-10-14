package com.cat.daily.local.core.assist.ext

import android.app.Activity
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

fun Activity.showSnackBar(
    view: View,
    message: String,
) {
    Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show()
}

fun Fragment.showSnackBar(
    message: String,
) {
    Snackbar.make(this.requireView(), message, Snackbar.LENGTH_SHORT).show()
}