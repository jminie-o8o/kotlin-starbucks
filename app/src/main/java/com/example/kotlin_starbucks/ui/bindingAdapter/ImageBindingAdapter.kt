package com.example.kotlin_starbucks.ui.bindingAdapter

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.kotlin_starbucks.repository.module.GlideApp

@BindingAdapter("imageUrl")
fun setEventImageBitmap(view: ImageView, imageUrl: String?) {
    Log.d("BindingAdapter", "$imageUrl")
    Glide.with(view).load(imageUrl).into(view)
}