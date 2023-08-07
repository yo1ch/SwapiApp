package com.example.core.network

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.ImageView

interface ImageLoader {
    fun loadImage(url: String, imageView: ImageView)
    fun loadImage(uri: Uri, imageView: ImageView)
    fun loadImage(url: String, onFinish: (Drawable?) -> Unit)
    fun loadImage(uri: Uri, onFinish: (Drawable?) -> Unit)
    fun loadImage(url: String, imageView: ImageView, onFinish: (Boolean) -> Unit)
    fun loadImage(uri: Uri, imageView: ImageView, onFinish: (Boolean) -> Unit)
    fun loadImage(url: String, imageView: ImageView, width: Int, height: Int)
    fun loadImage(uri: Uri, imageView: ImageView, width: Int, height: Int)
    fun loadImage(url: String, width: Int, height: Int, onFinish: (Drawable?) -> Unit)
    fun loadImage(uri: Uri, width: Int, height: Int, onFinish: (Drawable?) -> Unit)
    fun loadImage(
        url: String,
        imageView: ImageView,
        width: Int,
        height: Int,
        onFinish: (Boolean) -> Unit
    )

    fun loadImage(
        uri: Uri,
        imageView: ImageView,
        width: Int,
        height: Int,
        onFinish: (Boolean) -> Unit
    )

    fun loadImage(url: String, imageView: ImageView, circleCrop: Boolean)
    fun loadImageAsBitmap(url: String, onFinish: (Bitmap?) -> Unit)
    fun clear(imageView: ImageView)
}