package com.edw.kotlinandjavabasic.ui

import android.view.View
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.edw.kotlinandjavabasic.R
import com.edw.kotlinandjavabasic.adapter.DesignPatternsAdapter
import com.edw.kotlinandjavabasic.base.BaseFragment
import com.edw.kotlinandjavabasic.base.BaseRecyAdapter
import com.edw.kotlinandjavabasic.data.designPatternsCards
import com.edw.kotlinandjavabasic.databinding.FragmentDesignPatternsBinding
import com.edw.kotlinandjavabasic.entity.ItemCard


class DesignPatternsFragment : BaseFragment<FragmentDesignPatternsBinding>(),
    BaseRecyAdapter.OnItemClickListener<ItemCard> {
    private val adapter by lazy { DesignPatternsAdapter() }

    override fun initLayoutRes(): Int {
        return R.layout.fragment_design_patterns
    }

    override fun initView() {
        binding?.apply {
            recyDesignPatterns.layoutManager =
                LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            recyDesignPatterns.setHasFixedSize(true)
            recyDesignPatterns.adapter = adapter
        }
    }

    override fun initData() {
        adapter.setData(designPatternsCards)
    }

    override fun initEvent() {
        adapter.setOnItemClickListener(this)
    }

    override fun onItemClick(itemView: View, item: ItemCard, position: Int) {
        when (position) {
            0 -> {
                Navigation.findNavController(itemView)
                    .navigate(R.id.action_designPatternsFragment_to_singletonFragment)
            }

            1 -> {
                Navigation.findNavController(itemView)
                    .navigate(R.id.action_designPatternsFragment_to_builderFragment)
            }
        }
    }

}