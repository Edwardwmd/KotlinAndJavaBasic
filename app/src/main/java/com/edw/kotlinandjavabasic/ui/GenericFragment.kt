package com.edw.kotlinandjavabasic.ui

import android.view.View
import android.widget.Button
import android.widget.TextView
import com.edw.kotlinandjavabasic.R
import com.edw.kotlinandjavabasic.base.BaseFragment
import com.edw.kotlinandjavabasic.databinding.FragmentGenericBinding


class GenericFragment : BaseFragment<FragmentGenericBinding>(), View.OnClickListener {

    private lateinit var btnMethodGeneric: Button
    private lateinit var btnClassGeneric: Button
    private lateinit var btnInterfaceGeneric: Button
    private lateinit var tvContent: TextView

    override fun initLayoutRes(): Int {
        return R.layout.fragment_generic
    }

    override fun initView() {
        btnMethodGeneric = binding!!.btnMethodGenric
        btnClassGeneric = binding!!.btnClassGenric
        btnInterfaceGeneric = binding!!.btnInterfaceGenric
        tvContent = binding!!.tvResultWindows

    }

    override fun initData() {

    }

    override fun initEvent() {
        viewOnClick(this, btnMethodGeneric, btnClassGeneric, btnInterfaceGeneric)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btn_method_genric -> {
                /**
                 * 方法泛型
                 * 工具,泛型<T>写在fun后面
                 */
                val s = """
                    //方法泛型
                     fun <T> tools(vararg tool: T): MutableList<T> {
                            val tools = mutableListOf<T>()
                            for (it in tool) {
                                    tools.add(it)
                                       }
                              return tools
                              }
                              
                val tools = tools("锤子", "电钻", "抛光机", "打磨机", "锯片")
                val sb = StringBuilder()
                tools.forEach {
                    sb.append(it).append("\n")
                }
               
                """
                tvContent.text =s
            }
            R.id.btn_class_genric -> {
                val s = """  
                    //类型泛型
                    class Person<T>(type: T) {
                      private var type: T? = null

                      init {
                        this.type = type
                      }

                      fun getInfo(): String {
                        return "这是个"+type +"的工作"
                      }
                }
                val info = Person("老师").getInfo()"""
                tvContent.text = s
            }
            R.id.btn_interface_genric -> {

                val s = """
                 val tools = mutableListOf("汽车", "火车", "飞机")
                 
                  //接口泛型
                 interface DataPost<T>{ 
                 
                       fun getData():MutableList<T>
                       
                   }
                   
                class Repos:DataPost<String>{
                
                   override fun getData(): MutableList<String> {
                   
                     return tools
                     
                   }

                }
                val data = Repos().getData()"""

                tvContent.text=s
            }
        }
    }

}