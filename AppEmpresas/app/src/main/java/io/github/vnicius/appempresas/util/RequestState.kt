package io.github.vnicius.appempresas.util


sealed class RequestState {
    object LOADING : RequestState()
    object SUCCESS : RequestState()
    class FAILED(val exception: Exception) : RequestState()
}