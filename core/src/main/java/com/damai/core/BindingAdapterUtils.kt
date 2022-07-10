package com.damai.core

import android.os.Build
import android.text.Html
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter

/**
 * Created by damai.subimawanto on 7/10/2022.
 */
object BindingAdapterUtils {

    @JvmStatic
    @BindingAdapter("thumbnail")
    fun ImageView.setImageThumbnail(url: String?) {
        loadImage(url)
    }

    @JvmStatic
    @BindingAdapter("hospital", "specialization")
    fun TextView.hospitalSpecial(
        hospitalText: String?,
        specializationText: String?
    ) {
        val hospitalSpecializationText = "$hospitalText - $specializationText"
        this.text = hospitalSpecializationText
    }

    @JvmStatic
    @BindingAdapter("about")
    fun TextView.aboutHtml(aboutText: String?) {
        val htmlText = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(aboutText?.trim() ?: "", Html.FROM_HTML_MODE_COMPACT, null, null)
        } else {
            Html.fromHtml(aboutText?.trim() ?: "", null, null)
        }
        this.text = htmlText
    }
}