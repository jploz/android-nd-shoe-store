package com.udacity.shoestore

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import com.google.android.material.textfield.TextInputEditText


@Suppress("unused")
@BindingAdapter("shoeSizeFormatted")
fun TextInputEditText.setShoeSizeFormatted(item: Double?) {
    item?.let {
        if (item > 0.0) {
            setText(item.toString(), TextView.BufferType.EDITABLE)
        } else {
            setText("", TextView.BufferType.EDITABLE)
        }
    }
}

@Suppress("unused")
@InverseBindingAdapter(attribute = "shoeSizeFormatted", event = "android:textAttrChanged")
fun getText(view: TextInputEditText): Double {
    return try {
        val numStr = view.text.toString()
        numStr.toDouble()
    } catch (e: NumberFormatException) {
        0.0
    }
}
