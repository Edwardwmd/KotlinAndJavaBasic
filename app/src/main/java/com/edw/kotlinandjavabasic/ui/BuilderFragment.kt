package com.edw.kotlinandjavabasic.ui

import android.util.Log
import com.edw.kotlinandjavabasic.R
import com.edw.kotlinandjavabasic.base.BaseFragment
import com.edw.kotlinandjavabasic.databinding.FragmentBuilderBinding

@Suppress("JAVA_CLASS_ON_COMPANION")
class BuilderFragment : BaseFragment<FragmentBuilderBinding>() {
    companion object {
        val TAG = "BuilderFragment"
    }

    override fun initLayoutRes(): Int {
        return R.layout.fragment_builder
    }

    override fun initView() {

    }

    override fun initData() {
        Computer
            .builder()
            .setCpu("AMD-3700x")
            .setRam("芝奇-64GB")
            .setDisk("三星NVME-500GB")
            .setDisplay("AOC-23英寸")
            .setKeyboard("Vpro-500")
            .setLed("追风者")
            .setGraphicsCard("七彩虹-1060GTX-6G")
            .setHeatSink("大镰刀")
            .printLog()
            .create()
    }

    /**
     * 创建者模式:当一个类的构造函数参数个数超过4个，而且这些参数有些是可选的参数，考虑使用构造者模式。
     */
    class Computer {
        var cpu: String? = null
        var ram: String? = null
        var disk: String? = null
        var display: String? = null
        var graphicsCard: String? = null
        var keyboard: String? = null
        var mouse: String? = null
        var usbInterface: String? = null
        var led: String? = null
        var heatSink: String? = null

        /**
         * 静态实例化Builder
         */
        companion object {
            fun builder(): Builder {
                return Builder()
            }
        }

        class Builder {
            //实例化Computer
            private val mComputer = Computer()

            /**
             * 设置CPU
             */
            fun setCpu(cpu: String): Builder {
                mComputer.cpu = cpu
                return this
            }

            /**
             * 设置内存
             */
            fun setRam(ram: String): Builder {
                mComputer.ram = ram
                return this
            }

            /**
             * 设置硬盘
             */
            fun setDisk(disk: String): Builder {
                mComputer.disk = disk
                return this
            }

            /**
             * 设置显示器
             */
            fun setDisplay(display: String): Builder {
                mComputer.display = display
                return this
            }

            /**
             * 设置显卡
             */
            fun setGraphicsCard(graphicsCard: String): Builder {
                mComputer.graphicsCard = graphicsCard
                return this
            }

            /**
             * 设置键盘
             */
            fun setKeyboard(keyboard: String): Builder {
                mComputer.keyboard = keyboard
                return this
            }

            /**
             * 设置鼠标
             */
            fun setMouse(mouse: String): Builder {
                mComputer.mouse = mouse
                return this
            }

            /**
             * 设置USB接口
             */
            fun setUsbInterface(usbInterface: String): Builder {
                mComputer.usbInterface = usbInterface
                return this
            }

            /**
             * 设置LED灯
             */
            fun setLed(led: String): Builder {
                mComputer.led = led
                return this
            }

            /**
             * 设置散热器
             */
            fun setHeatSink(heatSink: String): Builder {
                mComputer.heatSink = heatSink
                return this
            }

            fun create(): Computer {
                return mComputer
            }


            fun printLog(): Builder {
                val s = StringBuilder()
                    .append(mComputer.cpu)
                    .append("\n")
                    .append(mComputer.ram)
                    .append("\n")
                    .append(mComputer.disk)
                    .append("\n")
                    .append(mComputer.display)
                    .append("\n")
                    .append(mComputer.graphicsCard)
                    .append("\n")
                    .append(mComputer.usbInterface)
                    .append("\n")
                    .append(mComputer.keyboard)
                    .append("\n")
                    .append(mComputer.mouse)
                    .append("\n")
                    .append(mComputer.heatSink)
                    .append("\n")
                    .append(mComputer.led)
                Log.e(TAG, "$s")

                return this
            }
        }

    }


}