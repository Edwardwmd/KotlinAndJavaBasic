package com.edw.kotlinandjavabasic.ui

import androidx.navigation.findNavController
import com.edw.kotlinandjavabasic.R
import com.edw.kotlinandjavabasic.base.BaseActivity
import com.edw.kotlinandjavabasic.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun initLayoutRes(): Int {
        return R.layout.activity_main
    }

    /**
     * Navigation由三部分组成:
     * 1.Navigation Graph:一种XML资源，在一个集中位置包含所有与导航有关的信息。在/res/navigation文件夹下的XML文件
     * 2.NavHostFragment:一个空容器，用于显示导航图中的目的地。导航组件包含一个默认NavHost实现 NavHostFragment，它显示Fragment目的地。
     * 3.NavController:管理内的应用程序导航的对象NavHost。在 NavController编排中的目标内容的交换 NavHost，因为用户在整个移动你的应用程序。
     */
    override fun initView() {

    }

    override fun initData() {

    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.nav_host_fragment).navigateUp()
    }


}