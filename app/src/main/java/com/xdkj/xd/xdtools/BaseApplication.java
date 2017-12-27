package com.xdkj.xd.xdtools;

import android.app.Activity;
import android.app.Application;
import android.os.Build;

import java.util.Stack;

public class BaseApplication extends Application {
    /*
    * 初始化TAG
    * */
    private  static String TAG=BaseApplication.class.getName();

    /*Activity堆*/
    private Stack<Activity> activityStack = new Stack<Activity>();


    @Override
    public void onCreate() {
        super.onCreate();
//        LogUtils.d(TAG,TAG+"---onCreate()");
        printAppParameter();
    }

    /*打印出一些app的参数*/
    private void printAppParameter(){
//        LogUtils.d(TAG, "OS : "+ Build.VERSION.RELEASE + " ( " + Build.VERSION.SDK_INT + " )");
//        DeviceMgr.ScrSize realSize = DeviceMgr.getScreenRealSize(this);
//        LogUtils.d(TAG, "Screen Size: " + realSize.w + " X " + realSize.h);
    }
    public void addActivity(final Activity curAT) {
        if (null == activityStack) {
            activityStack = new Stack<Activity>();
        }
        activityStack.add(curAT);
    }

    public void removeActivity(final Activity curAT) {
        if (null == activityStack) {
            activityStack = new Stack<Activity>();
        }
        activityStack.remove(curAT);
    }

    //获取最后一个Activity
    public Activity currentActivity() {
        Activity activity = activityStack.lastElement();
        return activity;
    }

    //返回寨内Activity的总数
    public int howManyActivities() {
        return activityStack.size();
    }

    //关闭所有Activity
    public void finishAllActivities() {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }

}