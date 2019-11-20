package com.example.revolut.util

import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import java.text.DecimalFormat
import android.graphics.Typeface
import android.util.Log
import java.lang.Exception


fun Float.formatToAmount(decimals: Int = 2): String {
    return if (this == 0f) "" else DecimalFormat("#.${"#".repeat(decimals)}").format(this)
}

fun ImageView.loadImage(url: String) {
    Picasso.with(context).cancelRequest(this)
    Picasso.with(context).load(url).transform(CircleTransform()).centerCrop().fit().into(this)
}

fun EditText.showKeyboard() {
    post {
        requestFocus()
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
    }
}

fun TextView.setFont(font: String) {
    try {
        typeface = Typeface.createFromAsset(context.assets, "font/${font}")
    } catch (e : Exception) {
        Log.e("", "Could not set the font", e)
    }
}