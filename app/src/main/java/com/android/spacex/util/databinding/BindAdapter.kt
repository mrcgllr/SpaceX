package com.android.spacex.util.databinding

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BindingAdapter
import com.android.spacex.R
import com.bumptech.glide.Glide
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object BindAdapter {

    @JvmStatic
    @BindingAdapter("bind_image_url")
    fun bindImage(imageView: ImageView, imageUrl: String?) {
        if (!imageUrl.isNullOrEmpty())
            Glide.with(imageView.context)
                .load(imageUrl)
                .placeholder(R.drawable.ic_image_loading)
                .error(R.drawable.ic_image_error)
                .into(imageView)
    }

    @JvmStatic
    @BindingAdapter("convert_date")
    fun AppCompatTextView.convertDate(date: String?) {
        try {
            val format = SimpleDateFormat("yyyy-MM-dd")
            date?.let {
                val date1 = format.parse(it)
                val format2 = SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH)
                this.text = format2.format(date1)
            }
        } catch (exception: ParseException) {
            exception.printStackTrace()
        }
    }

    @JvmStatic
    @BindingAdapter("bind_visible")
    fun bindVisible(view: View, isProgress: Boolean) {
        when (isProgress) {
            true -> view.visibility = View.VISIBLE
            false -> view.visibility = View.GONE
        }
    }

}