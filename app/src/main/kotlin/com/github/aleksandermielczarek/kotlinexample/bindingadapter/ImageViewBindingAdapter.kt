package com.github.aleksandermielczarek.kotlinexample.bindingadapter

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.squareup.picasso.Picasso

/**
 * Created by Aleksander Mielczarek on 30.09.2016.
 */
@BindingAdapter("imageUrl")
fun ImageView.setImageUrl(imageUrl: String) {
    Picasso.with(context)
            .load(imageUrl)
            .into(this)
}
