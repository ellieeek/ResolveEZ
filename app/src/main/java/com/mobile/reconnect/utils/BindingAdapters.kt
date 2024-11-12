package com.mobile.reconnect.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mobile.reconnect.R
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object BindingAdapters {
    @BindingAdapter("imageUrl")
    @JvmStatic
    fun loadImage(imageView: ImageView, url: String?) {
        if (!url.isNullOrEmpty()) {
            Glide.with(imageView.context)
                .load(url)
                .placeholder(R.drawable.ic_baseline_my_24) // 기본 이미지
                .into(imageView)
        } else {
            imageView.setImageResource(R.drawable.ic_baseline_my_24) // URL이 없을 때 기본 이미지
        }
    }

    @BindingAdapter("imageResource")
    fun setImageResource(imageView: ImageView, resource: Int?) {
        resource?.let {
            imageView.setImageResource(it)
        }
    }
}