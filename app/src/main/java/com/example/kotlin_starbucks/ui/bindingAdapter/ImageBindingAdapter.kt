package com.example.kotlin_starbucks.ui.bindingAdapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.kotlin_starbucks.repository.module.GlideApp

@BindingAdapter("imageUrl")
fun setEventImageBitmap(view: ImageView, imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        GlideApp.with(view)
            .load(imageUrl)
            .into(view)
    }
}