package com.example.kotlin_starbucks.ui.bindingAdapter

import android.graphics.Color
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.kotlin_starbucks.R

@BindingAdapter("homeTitle")
fun setColorTextView(view: TextView, text: String) {
    val textLen = text.length
    val subtitle = view.context.getString(R.string.your_recommend_title_example)
    val spannable = SpannableStringBuilder(text)
    spannable.append(subtitle)
    spannable.setSpan(
        ForegroundColorSpan(view.context.getColor(R.color.pink)),
        0,
        textLen + 1,
        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    )
    view.text = spannable
}