package com.aritana.unittestwillian.string

import android.text.TextUtils

class StringValidator {
    fun isEmpty(text: String): Boolean {
        return TextUtils.isEmpty(text)
    }
}