package com.damai.core

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.RequestOptions
import okhttp3.OkHttpClient
import java.io.InputStream
import java.util.concurrent.TimeUnit

/**
 * Created by damai.subimawanto on 7/10/2022.
 */
@GlideModule
class CustomGlideModule : AppGlideModule() {

    private val squarePlaceHolder = R.drawable.ic_placeholder_square

    override fun applyOptions(context: Context, builder: GlideBuilder) {
        builder.setDefaultRequestOptions(requestOption())
    }

    override fun isManifestParsingEnabled(): Boolean {
        return false
    }

    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        val client = OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .build()
        registry.replace(GlideUrl::class.java, InputStream::class.java, OkHttpUrlLoader.Factory(client))
    }

    private fun requestOption(): RequestOptions {
        return RequestOptions()
            .format(DecodeFormat.PREFER_RGB_565)
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .placeholder(squarePlaceHolder)
            .error(squarePlaceHolder)
    }
}