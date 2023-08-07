package com.example.core.utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

fun hideKeyboard(view: View){
    val imm=view.context.getSystemService(Context.INPUT_METHOD_SERVICE)
            as InputMethodManager
    imm.hideSoftInputFromWindow(view.windowToken,0)
}

fun showKeyboard(view: View){
    val imm=view.context.getSystemService(Context.INPUT_METHOD_SERVICE)
            as InputMethodManager
    imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
}