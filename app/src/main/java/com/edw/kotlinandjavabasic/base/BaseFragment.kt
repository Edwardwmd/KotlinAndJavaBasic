package com.edw.kotlinandjavabasic.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

/**
 * Author: EdwardWMD
 * Data: 2021/4/2
 * Project: KotlinAndJavaBasic
 * Website: https://github.com/Edwardwmd
 * Desc: File Information!
 */
abstract class BaseFragment<VB : ViewDataBinding> : Fragment() {

    protected var binding: VB? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, initLayoutRes(), container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()

        initData()

        initEvent()
    }

    @LayoutRes
    abstract fun initLayoutRes(): Int

    abstract fun initView()

    abstract fun initData()

    open fun initEvent() {}

    override fun onDestroyView() {
        super.onDestroyView()
        if (binding != null) binding!!.unbind()
    }

    /**
     * 多个View都实现点击事件,不需要写过多的View.OnClickListener
     */
    open fun viewOnClick(listener: View.OnClickListener, vararg views: View) {
        for (it in views) {
            it.setOnClickListener(listener)
        }
    }
}