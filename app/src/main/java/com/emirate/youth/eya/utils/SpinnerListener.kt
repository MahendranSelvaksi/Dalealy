package com.emirate.youth.eya.utils

import java.text.FieldPosition

interface SpinnerListener {
    abstract fun onItemSelectListener(value:String,position: Int)
}