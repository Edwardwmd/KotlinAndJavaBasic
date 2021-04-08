package com.edw.kotlinandjavabasic.adapter

import com.edw.kotlinandjavabasic.R
import com.edw.kotlinandjavabasic.base.BaseRecyAdapter
import com.edw.kotlinandjavabasic.databinding.ItemCommonCardBinding
import com.edw.kotlinandjavabasic.entity.ItemCard


/**
 * Author: EdwardWMD
 * Data: 2021/4/5
 * Project: KotlinAndJavaBasic
 * Website: https://github.com/Edwardwmd
 * Desc: File Information!
 */
class DesignPatternsAdapter:BaseRecyAdapter<ItemCard, ItemCommonCardBinding>() {
    override fun initLayoutRes(): Int {
        return R.layout.item_common_card
    }

    override fun onBindData(
        binding: ItemCommonCardBinding?,
        holder: BaseViewHolder,
        data: ItemCard?,
        position: Int
    ) {
        binding?.apply {
            this.itemCard=data!!
        }
    }


}