package com.example.dhaval.service1;

import android.app.Service;
import android.content.Intent;
import android.icu.text.DateFormat;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.RequiresApi;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MyService extends Service {
    private IBinder binder=new MyBinder();

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        //throw new UnsupportedOperationException("Not yet implemented");
        return binder;
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return true;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }


    public String getCurrentTimeStamp()
    {
       //Long milliseconds=System.currentTimeMillis()/1000;
        Calendar c = Calendar.getInstance();
       // System.out.println("Current time => "+c.getTime());

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = df.format(c.getTime());
        return formattedDate;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public class MyBinder extends Binder
    {
        MyService getService()
        {
            return MyService.this;
        }
    }
}
