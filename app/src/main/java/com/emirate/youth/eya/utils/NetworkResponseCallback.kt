package com.emirate.youth.eya.utils

interface NetworkResponseCallback {
    fun onNetworkSuccess()
    fun onNetworkFailed(msg:String)
    fun onNetworkFailure(th : Throwable)
}