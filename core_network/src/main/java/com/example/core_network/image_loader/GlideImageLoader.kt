package com.ruguide.core_network.image

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.MemoryCategory
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.core.network.ImageLoader
import java.util.concurrent.Executors
import java.util.concurrent.Future
import javax.inject.Inject


internal class GlideImageLoader @Inject constructor(private val context: Context): ImageLoader {

    private val executor = Executors.newFixedThreadPool(2)

    init {
        Glide.get(context).setMemoryCategory(MemoryCategory.LOW)
    }

    private val requestSVG by lazy {
        Glide.with(context)
            .`as`(BitmapDrawable::class.java)
            .transition(withCrossFade())
            .listener(SvgSoftwareLayerSetter())
    }

    override fun loadImage(url: String, imageView: ImageView, width: Int, height: Int) {
        clear(imageView)
        if (url.contains(".svg"))
            requestSVG.load(url).override(width, height).into(imageView)
        else
            Glide.with(context).load(url).centerCrop().override(width, height).into(imageView)
    }

    override fun loadImage(uri: Uri, imageView: ImageView, width: Int, height: Int) {
        clear(imageView)
         if (uri.toString().contains(".svg"))
            requestSVG.load(uri).override(width, height).into(imageView)
        else
            Glide.with(context).load(uri).centerCrop().override(width, height).into(imageView)
    }

    override fun loadImage(url: String, width: Int, height: Int, onFinish : (Drawable?) -> Unit) {
        val future : Future<Drawable> = if (url.contains(".svg"))
            requestSVG.load(url).centerCrop().override(width, height).submit() as Future<Drawable>
        else
            Glide.with(context).load(url).centerCrop().override(width, height).submit()
        val run = Runnable {
            val result = future.get()
            onFinish(result)
        }
        executor.submit(run)
    }

    override fun loadImage(uri: Uri, width: Int, height: Int, onFinish : (Drawable?) -> Unit) {
        val future : Future<Drawable> = if (uri.toString().contains(".svg"))
            requestSVG.load(uri).centerCrop().override(width, height).submit() as Future<Drawable>
        else
            Glide.with(context).load(uri).centerCrop().override(width, height).submit()
        val run = Runnable {
            val result = future.get()
            onFinish(result)
        }
        executor.submit(run)
    }

    override fun loadImage(url: String, imageView: ImageView) {
        clear(imageView)
        if (url.contains(".svg"))
            requestSVG.load(url).circleCrop().into(imageView)
        else
            Glide.with(context).load(url).centerCrop().into(imageView)
    }

    override fun loadImage(uri: Uri, imageView: ImageView) {
        clear(imageView)
        if (uri.toString().contains(".svg"))
            requestSVG.load(uri).circleCrop().into(imageView)
        else
            Glide.with(context).load(uri).centerCrop().into(imageView)
    }

    override fun loadImage(url: String, onFinish : (Drawable?) -> Unit) {
        val future : Future<Drawable> = if (url.contains(".svg"))
            requestSVG.load(url).submit() as Future<Drawable>
        else
            Glide.with(context).load(url).submit()

        val run = Runnable {
            val result = future.get()
            onFinish(result)
        }
        executor.submit(run)
    }

    override fun loadImage(uri: Uri, onFinish : (Drawable?) -> Unit) {
        val future : Future<Drawable> =
            if (uri.toString().contains(".svg"))
            requestSVG.load(uri).submit() as Future<Drawable>
        else
            Glide.with(context).load(uri).submit()
        val run = Runnable {
            val result = future.get()
            onFinish(result)
        }
        executor.submit(run)
    }

    override fun loadImage(url: String, imageView: ImageView, onFinish: (Boolean) -> Unit) {
        clear(imageView)
        if (url.contains(".svg"))
            requestSVG.load(url).circleCrop().addListener(requestListener(onFinish)).into(imageView)
        else
            Glide.with(context).load(url).centerCrop().addListener(requestListener(onFinish)).into(imageView)
    }

    override fun loadImage(uri: Uri, imageView: ImageView, onFinish: (Boolean) -> Unit) {
        clear(imageView)
        if (uri.toString().contains(".svg"))
            requestSVG.load(uri).circleCrop().addListener(requestListener(onFinish)).into(imageView)
        else
            Glide.with(context).load(uri).centerCrop().addListener(requestListener(onFinish)).into(imageView)
    }

    override fun loadImage(
        url: String,
        imageView: ImageView,
        width: Int,
        height: Int,
        onFinish: (Boolean) -> Unit
    ) {
        clear(imageView)
        if (url.contains(".svg"))
            requestSVG.load(url).override(width, height).addListener(requestListener(onFinish)).into(imageView)
        else
            Glide.with(context).load(url).centerCrop().override(width, height).addListener(requestListener(onFinish)).into(imageView)
    }

    override fun loadImage(
        uri: Uri,
        imageView: ImageView,
        width: Int,
        height: Int,
        onFinish: (Boolean) -> Unit
    ) {
        clear(imageView)
        if (uri.toString().contains(".svg"))
            requestSVG.load(uri).override(width, height).addListener(requestListener(onFinish)).into(imageView)
        else
            Glide.with(context).load(uri).centerCrop().override(width, height).addListener(requestListener(onFinish)).into(imageView)
    }

    override fun loadImage(
        url: String,
        imageView: ImageView,
        circleCrop: Boolean
    ) {
        clear(imageView)
        if (url.contains(".svg"))
            requestSVG.load(url).circleCrop().into(imageView)
        else
            Glide.with(context).load(url).circleCrop().into(imageView)
    }

    override fun loadImageAsBitmap(url: String, onFinish: (Bitmap?) -> Unit) {
        val future: Future<Bitmap> = Glide.with(context).asBitmap().load(url).submit()

        val run = Runnable {
            val result = future.get()
            onFinish(result)
        }
        executor.submit(run)
    }

    override fun clear(imageView: ImageView) {
        Glide.with(context).clear(imageView)
        imageView.setImageDrawable(null)
    }

    private fun <T> requestListener( listener : (Boolean)->Unit): RequestListener<T>{
        return object : RequestListener<T>{
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<T>?,
                isFirstResource: Boolean
            ): Boolean {
                listener(false)
                return false
            }

            override fun onResourceReady(
                resource: T,
                model: Any?,
                target: Target<T>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                listener(true)
                return false
            }
        }
    }
}