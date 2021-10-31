package com.gy.lifecycledemo

import android.app.Application
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.util.Log

class MyApplication : Application(){

    override fun onCreate() {
        super.onCreate()
        cashLifeCycle()
        noDead()
    }

    // 保证主线程不会挂掉
    fun noDead(){
        Handler(Looper.getMainLooper()).post {
            while (true) {
                //主线程异常拦截
                try {
                    Looper.loop()
                } catch (e: Throwable) {
                }
            }
        }
    }

    fun cashLifeCycle(){

        val clazz = Application::class.java

        val mLoadedApkField = clazz.getDeclaredField("mLoadedApk")
        mLoadedApkField.isAccessible = true
        val mLoadedApk = mLoadedApkField.get(this)

        val loadedApkClass = mLoadedApk.javaClass
        val mActivityThreadField = loadedApkClass.getDeclaredField("mActivityThread")
        mActivityThreadField.isAccessible = true
        val mActivityThread = mActivityThreadField.get(mLoadedApk)

        val activityThreadClass = mActivityThread.javaClass
        val mHField = activityThreadClass.getDeclaredField("mH")
        mHField.isAccessible = true
        val mh = mHField.get(mActivityThread) as Handler


        val callBackField = Handler::class.java.getDeclaredField("mCallback")
        callBackField.isAccessible = true

        // TODO 可以通过这个方法捕获 所有的主线程的异常
        callBackField.set(mh,Handler.Callback { msg ->

            Log.i("log_application","msg==what====${msg.what}===${msg.obj}")
            if (Build.VERSION.SDK_INT >= 28) {
                //android 28之后的生命周期处理
                val EXECUTE_TRANSACTION = 159;
                if (msg.what == EXECUTE_TRANSACTION) {
                    try {
                        mh.handleMessage(msg);
                    } catch (throwable:Throwable) {
                        //杀死进程或者杀死Activity
                        Log.i("cccccccccc","===${throwable.message}")
                    }
                    return@Callback true;
                }
                return@Callback false;
            }

            //android 28之前的生命周期处理
//            switch (msg.what) {
//                case RESUME_ACTIVITY :
//                //onRestart onStart onResume回调这里
//                try {
//                    mhHandler.handleMessage(msg);
//                } catch (Throwable throwable) {
//                    sActivityKiller.finishResumeActivity(msg);
//                    notifyException(throwable);
//                }
//                return@Callback true;
//
//            }

            return@Callback false
        });

    }
}