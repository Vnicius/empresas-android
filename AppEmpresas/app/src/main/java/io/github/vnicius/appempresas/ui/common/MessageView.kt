package io.github.vnicius.appempresas.ui.common

import android.view.View
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import io.github.vnicius.appempresas.R


object MessageView {

    fun show(
        rootView: View,
        @StringRes textRes: Int,
        @BaseTransientBottomBar.Duration duration: Int
    ) {
        show(rootView, rootView.context.getString(textRes), duration)
    }

    fun show(rootView: View, text: String, @BaseTransientBottomBar.Duration duration: Int) {
        val snackbarBackground =
            ContextCompat.getDrawable(rootView.context, R.drawable.snackbar_error_background)

        Snackbar.make(rootView, text, duration).apply {
            view.background = snackbarBackground
        }.show()
    }
}