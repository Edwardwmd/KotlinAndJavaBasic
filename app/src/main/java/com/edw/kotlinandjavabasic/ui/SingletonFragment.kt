package com.edw.kotlinandjavabasic.ui

import android.util.Log
import com.edw.kotlinandjavabasic.R
import com.edw.kotlinandjavabasic.base.BaseFragment
import com.edw.kotlinandjavabasic.databinding.FragmentSingletonBinding

import java.util.concurrent.Executors


@Suppress("JAVA_CLASS_ON_COMPANION")
class SingletonFragment : BaseFragment<FragmentSingletonBinding>() {


    override fun initLayoutRes(): Int {
        return R.layout.fragment_singleton
    }

    override fun initView() {

    }

    override fun initData() {
        //多线程下执行单例测试
        val executor = Executors.newFixedThreadPool(20)

        for (i in 0 until 10) {
            executor.submit {
                SingletonFirst
                SingletonSecond.get()
                SingletonThird.get()
                SingletonFourth.instance
                SingletonFifth.getInstance()
            }
        }
        val s1 = SingletonFourth.instance
        val s2 = SingletonFourth.instance
        Log.e("SingletonFragment", "s1 ${if (s1 == s2) "=" else "!="} s2")

        //反射破坏单例模式:
        try {
            //1.首先获取空参构造器
            val constructor = SingletonFourth::class.java.getDeclaredConstructor()
            // isAccessible=true破解代码中包含private的私有方法,成员变量,构造,内部类,这里直接无视私有构造器
            constructor.isAccessible = true
            //通过反射创建SingletonFourth对象
            val s3 = constructor.newInstance()
            //通过判断发现直接创建的对象和反射获取的对象不相等(违反了单例原则)
            Log.e("SingletonFragment", "s1 ${if (s3 == s1) "=" else "!="} s3")

        } catch (e: Exception) {
            Log.e("SingletonFragment", e.message.toString())
        }


    }

    /**
     * 饿汉式:一初始化就直接加载SingletonFirst对象,效率相比懒汉式要快,线程是安全的,更占用内存,耗费资源
     *
     * 为什么是线程安全的:可以说饿汉式是天生线程安全的单例模式,饿汉式是通过类加载形式实现的,类加载的方式是按需加载，且只加载一次
     * 在线程访问单例对象之前就已经创建好了,由于一个类在整个生命周期中只会被加载一次，因此该单例类只会创建一个实例
     */
    object SingletonFirst {
        fun printLog(num: Int) {
            Log.e(
                "SingletonFragment",
                "饿汉式单例模式-->初始化啦~  是否在主线程-->${Thread.currentThread().id}  -->$num"
            )
        }

        init {
            Log.e(
                "SingletonFragment",
                "饿汉式单例模式-->初始化啦~  是否在主线程-->${Thread.currentThread().id}"
            )
        }
    }

    /**
     * 非线程安全懒汉式:只有调用该类中的方法才会被加载,延迟了对象的加载,不占用内存,
     * 只有调用类中方法时才会被真正初始化,在多线程并发操作使用这类单例模式会使得线程不安全,这样也违反了单例的原则
     *  为什么是线程不安全:因为在多线程并发执行时,每个线程都同时进入
     *     if (field == null) {
     *       field = SingletonSecond()
     *     }
     * 这个代码块,使得同时创建的多个不同的SingletonSecond的实例
     *
     */
    class SingletonSecond private constructor() {
        companion object {
            private var singleton: SingletonSecond? = null
                get() {
                    if (field == null) {
                        field = SingletonSecond()
                    }
                    return field
                }

            fun get(): SingletonSecond {
                return singleton!!
            }
        }

        init {
            Log.e(
                "SingletonFragment",
                "懒汉式单例模式-->初始化啦~  是否在主线程-->${Thread.currentThread().id}"
            )
        }


        fun printLog(num: Int) {
            Log.e(
                "SingletonFragment",
                "懒汉式单例模式-->初始化啦~  是否在主线程-->${Thread.currentThread().id}    -->$num"
            )
        }

    }

    /**
     * 线程安全的懒汉式: 这种单例方法是在非线程安全的情况下演变而来的,在静态公有方法中添加Synchronized锁主方法中的代码块,以实现
     * 临界资源的同步互斥访问作用,保证多线程并发下实现线程安全,但效率很慢
     */
    class SingletonThird {

        companion object {
            private var instance: SingletonThird? = null
                get() {
                    if (field == null) {
                        field = SingletonThird()
                    }
                    return field
                }

            @Synchronized
            fun get(): SingletonThird {
                return instance!!
            }
        }

        private constructor() {
            Log.e(
                "SingletonFragment",
                "线程安全懒汉式单例模式-->初始化啦~   是否在主线程-->${Thread.currentThread().id}"
            )
        }

        fun printLog(num: Int) {
            Log.e(
                "SingletonFragment",
                "线程安全懒汉式单例模式-->初始化啦~   是否在主线程-->${Thread.currentThread().id}  -->$num"
            )

        }
    }

    /**
     * 双重检测锁懒汉式单例: 是线程安全的懒汉式单例的升级版,这种方式其但保证了单例，而且切实提高了程序运行效率
     * 为了在保证单例的前提下提高运行效率，我们需要对 SingletonFourth 进行第二次检查，
     * 目的是避开过多的同步（因为这里的同步只需在第一次创建实例时才同步，一旦创建成功，以后获取实例时就不需要同步获取锁了）
     * 必须要做的的一点: 必须使用volatile关键字修饰单例引用。保证原子性操作,防止指令重排
     */
    class SingletonFourth private constructor() {

        companion object {
            val instance: SingletonFourth by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
                SingletonFourth()
            }

        }

        fun printLog(num: Int) {
            Log.e(
                "SingletonFragment",
                "双重检测锁懒汉式单例模式-->初始化啦~  是否在主线程-->${Thread.currentThread().id}  -->$num"
            )

        }

        init {
            Log.e(
                "SingletonFragment",
                "双重检测锁懒汉式单例模式-->初始化啦~  是否在主线程-->${Thread.currentThread().id}"
            )
        }
    }

    /**
     * 静态内部类单例模式: 线程安全的单例模式,线程安全方式与饿汉式一致,与此同时也实现了懒加载的方式
     */
    class SingletonFifth private constructor() {
        private object Singleton {
            var holder: SingletonFifth = SingletonFifth()
        }

        companion object {
            fun getInstance(): SingletonFifth {
                return Singleton.holder
            }
        }

        fun printLog(num: Int) {
            Log.e(
                "SingletonFragment",
                "静态内部类单例模式-->初始化啦~  是否在主线程-->${Thread.currentThread().id}  -->$num"
            )

        }

        init {
            Log.e(
                "SingletonFragment",
                "静态内部类单例模式-->初始化啦~  是否在主线程-->${Thread.currentThread().id}"
            )
        }
    }


    /**
     * 枚举类单例模式:
     *
     */
    enum class SingletonSixth {
        INSTANCE;

        fun printLog(num: Int) {
            Log.e(
                "SingletonFragment",
                "枚举类单例模式-->初始化啦~  是否在主线程-->${Thread.currentThread().id}  -->$num"
            )

        }

        init {
            Log.e(
                "SingletonFragment",
                "枚举类单例模式-->初始化啦~  是否在主线程-->${Thread.currentThread().id} "
            )
        }

    }
}