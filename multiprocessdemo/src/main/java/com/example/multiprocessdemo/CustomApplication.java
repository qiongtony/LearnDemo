package com.example.multiprocessdemo;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.view.ViewGroup;

import java.util.List;

public class CustomApplication extends Application {
    private static final String REAL_PACKAGE_NAME = "com.example.multiprocessdemo";
    @Override
    public void onCreate() {
        super.onCreate();
        String processName = getProcessName(this, android.os.Process.myPid());
        Log.w("CustomApplication", "WWS onCreate processName = " + processName);
        boolean defaultProcess = processName.equals(REAL_PACKAGE_NAME);
        // 默认的主进程启动时初始化应用
        if (defaultProcess) {
           Log.e("CustomApplication", "WWS init defaultProcess");
        }
        // 其他进程启动时初始化对应内容
         else if (processName.contains(":remote")) {
            Log.e("CustomApplication", "WWS init remote");
        }
    }

    /**
     * @return null may be returned if the specified process not found
     */
    public static String getProcessName(Context cxt, int pid) {
        ActivityManager am = (ActivityManager) cxt.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningApps = am.getRunningAppProcesses();
        if (runningApps == null) {
            return null;
        }
        for (ActivityManager.RunningAppProcessInfo procInfo : runningApps) {
            if (procInfo.pid == pid) {
                return procInfo.processName;
            }
        }
        return null;
    }
}
