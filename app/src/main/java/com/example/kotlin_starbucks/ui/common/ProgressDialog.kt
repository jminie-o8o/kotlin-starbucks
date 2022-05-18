package com.example.kotlin_starbucks.ui.common

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import com.example.kotlin_starbucks.R

class ProgressDialog(context: Context) : Dialog(context) {
    init {
        setContentView(R.layout.loding_dialog)

        setCancelable(false)
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }
}