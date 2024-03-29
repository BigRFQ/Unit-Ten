package com.example.unitten;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    private String TAG = "服务测试：";
    private DownloadBinder mBinder = new DownloadBinder();
    class DownloadBinder extends Binder {
        public void startDownload(){
            Log.d(TAG, "startDownload: ");
        }
        public int getProgress(){
            Log.d(TAG, "getProgress: ");
            return 0;
        }
    }

    public MyService() {
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: ");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");
        
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return mBinder;
    }
}
