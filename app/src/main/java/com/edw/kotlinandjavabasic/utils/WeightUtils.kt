package com.edw.kotlinandjavabasic.utils

import android.widget.Toast
import com.edw.kotlinandjavabasic.App

/**
 * Author: EdwardWMD
 * Data: 2021/4/4
 * Project: KotlinAndJavaBasic
 * Website: https://github.com/Edwardwmd
 * Desc: File Information!
 */
object WeightUtils {

    fun toastShow(msg: String) {
        Toast.makeText(App.appContext(), msg, Toast.LENGTH_SHORT).show()
    }
}