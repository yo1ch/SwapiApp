package com.example.core.utils

import android.content.Context
import android.content.res.Resources
import android.util.TypedValue

fun Int.dp2px( context: Context): Int{
    val r: Resources = context.resources
    val px = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this.toFloat(),
        r.displayMetrics
    )
    return px.toInt()
}
fun Int.sp2px(context: Context): Int{
    val r: Resources = context.resources
    val px = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_SP,
        this.toFloat(),
        r.displayMetrics
    )
    return px.toInt()
}

fun getDisplayHeight(context : Context): Int =  try {
    context.display!!.height
} catch (e : NoSuchMethodError){
    context.resources.displayMetrics.heightPixels
}
fun getDisplayWidth(context : Context): Int =  try {
    context.display!!.width
} catch (e : NoSuchMethodError){
    context.resources.displayMetrics.widthPixels
}