package io.github.vnicius.appempresas.extension

import android.view.View
import io.github.vnicius.appempresas.util.GlobalSafeClickHelper


inline fun View.onClickListener(delay: Long = 1000L, crossinline action: View.() -> Unit) {
    setOnClickListener {
        isEnabled = false
        postDelayed({ isEnabled = true }, delay)
        action(this)
    }
}

inline fun View.globalSafeClickListener(crossinline action: View.() -> Unit) {
    setOnClickListener {
        if (GlobalSafeClickHelper.canClick()) {
            action()
        }
    }
}