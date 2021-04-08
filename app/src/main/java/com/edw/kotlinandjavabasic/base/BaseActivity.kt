package com.edw.kotlinandjavabasic.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment

/**
 * Author: EdwardWMD
 * Data: 2021/4/2
 * Project: KotlinAndJavaBasic
 * Website: https://github.com/Edwardwmd
 * Desc: File Information!
 */
abstract class BaseActivity<VB : ViewDataBinding> : AppCompatActivity() {
    protected var binding: VB? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, initLayoutRes())

        initView()

        initData()

        initEvent()
    }

    open fun initEvent() {}

    @LayoutRes
    abstract fun initLayoutRes(): Int

    abstract fun initView()

    abstract fun initData()

    override fun onDestroy() {
        super.onDestroy()
        if (binding != null)
            binding!!.unbind()
    }

    /**
     * 检测当前的Fragment到底是哪一个子依赖于Activity的fragment
     */
    @Suppress("UNCHECKED_CAST")
    fun <F : Fragment> AppCompatActivity.getFragment(fragmentClass: Class<F>): F? {
        val navHostFragment = this.supportFragmentManager.fragments.first() as NavHostFragment
        navHostFragment.childFragmentManager.fragments.forEach {
            if (fragmentClass.isAssignableFrom(it.javaClass)) {
                return it as F
            }
        }
        return null
    }
}