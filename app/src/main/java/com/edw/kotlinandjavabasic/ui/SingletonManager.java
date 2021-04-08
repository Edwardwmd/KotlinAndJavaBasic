package com.edw.kotlinandjavabasic.ui;

import android.util.Log;

/**
 * Author: EdwardWMD
 * Data: 2021/4/7
 * Project: KotlinAndJavaBasic
 * Website: https://github.com/Edwardwmd
 * Desc: File Information!
 */
public class SingletonManager {

    //饿汉式
    public static class SingletonF {
        //私有化构造,禁止创建实例时使用new关键字创建
        private SingletonF() {
        }

        private static SingletonF instance = new SingletonF();

        public static SingletonF getInstance() {
            return instance;
        }

        public void printLog(){
            Log.e("SingletonF","SingletonF 被初始化啦!");
        }
    }


    //懒汉式单例 实现了懒加载的
    public static class SingletonS {
        private SingletonS() {
        }

        private static SingletonS instance = null;

        public static SingletonS getInstance() {
            if (instance == null) {
                instance = new SingletonS();
            }
            return instance;
        }

        public void printLog(){
            Log.e("SingletonS","SingletonS被初始化啦!");
        }
    }


    //线程安全的单例模式
    public static class SingletonT {
        private SingletonT() {
        }

        private static SingletonT instance = null;

        public static synchronized SingletonT getInstance() {
            if (instance == null) {
                instance = new SingletonT();
            }
            return instance;
        }
        public void printLog(){
            Log.e("SingletonT","SingletonT 被初始化啦!");
        }
    }


    //双重校验锁线程安全模式单例
    public static class SingletonFr {
        private SingletonFr() {
        }

        private static SingletonFr instance = null;

        public static SingletonFr getInstance() {
            if (instance == null) {
                synchronized (SingletonFr.class) {
                    if (instance == null) {
                        instance = new SingletonFr();
                    }
                }
            }
            return instance;
        }
        public void printLog(){
            Log.e("SingletonFr","SingletonFr 被初始化啦!");
        }
    }


    //静态内部类
    public static class SingletonFf {
        private SingletonFf() {
        }

        private static class Singleton {
            private static SingletonFf holder = new SingletonFf();
        }

        public static SingletonFf getInstance() {
            return Singleton.holder;
        }

        public void printLog(){
            Log.e("SingletonFf","SingletonFf 被初始化啦!");
        }
    }

    public  enum  SingletonSi {
        SINGLE;

        protected void printLog(){
            Log.e("SingletonFf","SingletonFf 被初始化啦!");
        }

        private SingletonSi(){
            Log.e("SingletonFf","SingletonFf 被初始化啦!");
        }

    }

//    public static void main(String[] args) {
//
//        //多线程并发测试
//        for (int i = 0; i < 10; i++) {
//           new Thread(() -> SingletonS.instance.printLog()).start();
//        }
//
//
//    }

}
