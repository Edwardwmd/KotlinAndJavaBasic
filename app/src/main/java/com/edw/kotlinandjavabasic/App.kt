package com.edw.kotlinandjavabasic

import android.app.Application
import android.content.Context
import com.alibaba.android.arouter.launcher.ARouter
import kotlin.properties.Delegates

/**
 * Author: EdwardWMD
 * Data: 2021/4/2
 * Project: KotlinAndJavaBasic
 * Website: https://github.com/Edwardwmd
 * Desc: File Information!
 */
class App : Application() {


    companion object {
        private var mC by Delegates.notNull<Context>()
        fun appContext() = mC
    }

    override fun onCreate() {
        super.onCreate()
        mC = this.applicationContext

//        ARouter.init(this)
    }

    override fun onTerminate() {
        super.onTerminate()
        ARouter.getInstance().destroy()
    }

}