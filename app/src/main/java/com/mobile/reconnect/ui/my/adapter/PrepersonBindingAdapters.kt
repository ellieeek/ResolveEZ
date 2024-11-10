package com.mobile.reconnect.ui.my.adapter

import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.mobile.reconnect.R
import com.mobile.reconnect.data.model.PreRegistrationStatus

@BindingAdapter("statusStyle")
fun setStatusStyle(textView: TextView, status: PreRegistrationStatus) {
    val context = textView.context
    when (status) {
        PreRegistrationStatus.PENDING -> {
            textView.setTextColor(ContextCompat.getColor(context, R.color.gray_500))
            textView.textSize = 13f
            textView.setTypeface(null, android.graphics.Typeface.NORMAL)
        }
        PreRegistrationStatus.APPROVED -> {
            textView.setTextColor(ContextCompat.getColor(context, R.color.primary_red))
            textView.textSize = 13f
            textView.setTypeface(null, android.graphics.Typeface.BOLD)
        }
        PreRegistrationStatus.COMPLETED -> {
            textView.setTextColor(ContextCompat.getColor(context, R.color.gray_600))
            textView.textSize = 13f
            textView.setTypeface(null, android.graphics.Typeface.BOLD)
        }
    }
}