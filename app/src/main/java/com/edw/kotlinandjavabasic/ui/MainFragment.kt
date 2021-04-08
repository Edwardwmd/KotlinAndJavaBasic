package com.edw.kotlinandjavabasic.ui

import android.view.View
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.edw.kotlinandjavabasic.R
import com.edw.kotlinandjavabasic.adapter.MainAdapter
import com.edw.kotlinandjavabasic.base.BaseFragment
import com.edw.kotlinandjavabasic.base.BaseRecyAdapter
import com.edw.kotlinandjavabasic.data.itemCards
import com.edw.kotlinandjavabasic.databinding.FragmentMainBinding
import com.edw.kotlinandjavabasic.entity.ItemCard

class MainFragment : BaseFragment<FragmentMainBinding>(),
    BaseRecyAdapter.OnItemClickListener<ItemCard> {

    private var mRecy: RecyclerView? = null

    private val adapter by lazy { MainAdapter() }

    /**
     * 静态内部类单例获取MainFragment
     */
    private object Singleton {
        val holder = MainFragment()
    }

    companion object {
        fun getInstance() = Singleton.holder
    }


    override fun initLayoutRes(): Int {
        return R.layout.fragment_main
    }

    override fun initView() {

        mRecy = binding!!.recyMain
        mRecy!!.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        mRecy!!.setHasFixedSize(true)
        mRecy!!.adapter = adapter
    }

    override fun initData() {
        adapter.setData(itemCards)
    }


    override fun initEvent() {
        adapter.setOnItemClickListener(this)
    }

    override fun onItemClick(itemView: View, item: ItemCard, position: Int) {
        when (position) {
            0 -> {
                Navigation.findNavController(itemView)
                    .navigate(R.id.action_fragment_for_main_to_fragment_for_generic)
            }
            1 -> {
                Navigation.findNavController(itemView)
                    .navigate(R.id.action_fragment_for_main_to_lockFragment)
            }
            2 -> {
                Navigation.findNavController(itemView)
                    .navigate(R.id.action_fragment_for_main_to_designPatternsFragment)
            }

            3 -> {
                Navigation.findNavController(itemView)
                    .navigate(R.id.action_fragment_for_main_to_threadPoolExecutorFragment)
            }

            4 -> {
                Navigation.findNavController(itemView)
                    .navigate(R.id.action_fragment_for_main_to_bitmapCacheFragment)
            }
            5 -> {
                Navigation.findNavController(itemView)
                    .navigate(R.id.action_fragment_for_main_to_networkCacheFragment)
            }
            6 -> {
                Navigation.findNavController(itemView)
                    .navigate(R.id.action_fragment_for_main_to_ipcFragment)
            }
            7 -> {
                Navigation.findNavController(itemView)
                    .navigate(R.id.action_fragment_for_main_to_queueFragment)
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        adapter.cleanAllData()
        adapter.recycle()

    }
}