package com.damai.core

import android.widget.ImageView

/**
 * Created by damai.subimawanto on 7/10/2022.
 */

fun ImageView.loadImage(url: String?) {
    GlideApp.with(this)
        .load(url)
        .into(this)
}