package com.jtorreal.superheromarvel.ui.common

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.jtorreal.superheromarvel.R

fun ImageView.loadUrl(url: String) {
    Glide.with(context)
        .load(url)
        .error(R.drawable.placeholder_default)
        .placeholder(R.drawable.placeholder_default)
        .centerCrop()
        .into(this);
}


fun View.showOrHidden(state: Boolean): View {

    visibility = if (state) {
        View.VISIBLE
    } else {
        View.GONE
    }

    return this
}

fun Context.toast(message: CharSequence) =
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()