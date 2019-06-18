package com.example.unitten;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class ServiceTest extends AppCompatActivity implements View.OnClickListener{
    private MyService.DownloadBinder downloadBinder;
    private ServiceConnection connection  = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            downloadBinder = (MyService.DownloadBinder) service;
            downloadBinder.startDownload();
            downloadBinder.getProgress();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_test);
        Button startService = findViewById(R.id.start_service);//启动服务按键
        startService.setOnClickListener(this);
        Button stopService = findViewById(R.id.stop_service);//定制服务按键
        stopService.setOnClickListener(this);
        Button bindService = findViewById(R.id.bind_service);//绑定服务
        bindService.setOnClickListener(this);
        Button unbindService = findViewById(R.id.unbind_service);//取消绑定服务
        unbindService.setOnClickListener(this);
        //将服务变成前台服务
        Log.d("MyService", "onCreate: ");
        Intent intent = new Intent(this,MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this,0,intent,0);
        Notification notification = new NotificationCompat.Builder(this).setContentTitle("this is content title")
                .setContentText("this is content text")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
                .setContentIntent(pi)
                .build();
        //启动前台服务
    }

    @Override//按键响应函数
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.start_service:
                Intent startIntent  = new Intent(this,MyService.class);
                startService(startIntent);
                break;
            case R.id.stop_service:
                Intent stopIntent  = new Intent(this,MyService.class);
                stopService(stopIntent);
                break;
            case R.id.bind_service:
                Intent bindIntent = new Intent(this,MyService.class);
                bindService(bindIntent,connection,BIND_AUTO_CREATE);//绑定服务
                break;
            case R.id.unbind_service:
                unbindService(connection);
                break;
            default :
                break;
        }

    }
}
