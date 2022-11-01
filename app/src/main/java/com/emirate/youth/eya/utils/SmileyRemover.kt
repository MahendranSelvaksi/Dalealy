package com.emirate.youth.eya.utils

import android.text.InputFilter
import android.text.Spanned
import android.util.Log


class SmileyRemover : InputFilter {

    override fun filter(
        source: CharSequence,
        start: Int,
        end: Int,
        dest: Spanned?,
        dstart: Int,
        dend: Int
    ): CharSequence? {
        for (i in start until end) {
            val type = Character.getType(source[i])
            Log.w("Success", "type ::::: $type")
            if (type == Character.SURROGATE.toInt() || type == Character.OTHER_SYMBOL.toInt()) {
                return ""
            }
        }
        return null
    }
}

class TextRemover : InputFilter {

    override fun filter(
        source: CharSequence,
        start: Int,
        end: Int,
        dest: Spanned?,
        dstart: Int,
        dend: Int
    ): CharSequence? {
        for (i in start until end) {
            val type = Character.getType(source[i])
            Log.w("Success", "type ::::: $type")
            if (type == Character.LOWERCASE_LETTER.toInt() || type == Character.UPPERCASE_LETTER.toInt()) {
                return ""
            }
        }
        return null
    }
}