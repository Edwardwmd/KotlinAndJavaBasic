package com.edw.kotlinandjavabasic.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * Author: EdwardWMD
 * Data: 2021/4/2
 * Project: KotlinAndJavaBasic
 * Website: https://github.com/Edwardwmd
 * Desc: RecycleAdapter的基类
 */
abstract class BaseRecyAdapter<E, VB : ViewDataBinding> :
    RecyclerView.Adapter<BaseRecyAdapter.BaseViewHolder>() {
    protected var rootView: View? = null

    private var vb: VB? = null

    private var listener: OnItemClickListener<E>? = null

    private val itemList by lazy { ArrayList<E>() }

    open fun setData(itemList: MutableList<E>) {
        if (this.itemList.size > 0) this.itemList.clear()
        this.itemList.addAll(itemList)
        notifyDataSetChanged()
    }

    open fun cleanAllData() {
        if (itemList.size > 0)
            itemList.clear()
        notifyDataSetChanged()
    }


    class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val binding = DataBindingUtil.inflate<VB>(
            LayoutInflater.from(parent.context),
            initLayoutRes(),
            parent,
            false
        )
        rootView = binding.root
        return BaseViewHolder(rootView!!)
    }


    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        vb = DataBindingUtil.getBinding(holder.itemView)
        onBindData(vb!!, holder, this.itemList[position], position)
        holder.itemView.setOnClickListener {
            if (this.listener != null) {
                this.listener!!.onItemClick(it, this.itemList[position], position)
            }
        }
    }

    override fun getItemCount(): Int = if (this.itemList.size <= 0) 0 else this.itemList.size

    @LayoutRes
    abstract fun initLayoutRes(): Int

    abstract fun onBindData(binding: VB?, holder: BaseViewHolder, data: E?, position: Int)

    interface OnItemClickListener<in E> {
        fun onItemClick(itemView: View, item: E, position: Int)
    }

    open fun setOnItemClickListener(listener: OnItemClickListener<E>) {
        this.listener = listener
    }

    open fun recycle() {
        if (vb != null) vb!!.unbind()
        if (listener != null) listener = null
    }
}