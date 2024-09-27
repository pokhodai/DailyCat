package com.cat.school.local.common.ext

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

fun Fragment.showSnackBar() {

}