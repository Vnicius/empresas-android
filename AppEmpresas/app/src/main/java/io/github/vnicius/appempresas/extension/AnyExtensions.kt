package io.github.vnicius.appempresas.extension

import com.google.gson.GsonBuilder

fun <T> T.toJson(): String = GsonBuilder().create().toJson(this)