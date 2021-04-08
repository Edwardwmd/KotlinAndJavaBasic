package com.edw.kotlinandjavabasic.helper

import android.graphics.Color
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.databinding.BindingAdapter

/**
 * Author: EdwardWMD
 * Data: 2021/4/2
 * Project: KotlinAndJavaBasic
 * Website: https://github.com/Edwardwmd
 * Desc: DataBing Adapter的参数设置提供类
 */
object DataBindingCommon {

    @BindingAdapter("app:BackgroundColor")
    @JvmStatic
    fun setBackground(itemView: CardView, color: String?) {
        if (color != null) {
           itemView.setCardBackgroundColor(Color.parseColor(color))
        }
    }


    @BindingAdapter("app:Text")
    @JvmStatic
    fun setItemText(textView: TextView, name: String?) {
        if (name != null) {
            textView.text = name
        }
    }

}