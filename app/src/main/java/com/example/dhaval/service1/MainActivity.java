package com.example.dhaval.service1;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button button;
    MyService myService;
    boolean wService=false;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=(Button) findViewById(R.id.button2);
        textView=(TextView) findViewById(R.id.textView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(wService)
                {
                    textView.setText(myService.getCurrentTimeStamp());
                    unbindService(serviceConnection);
                    wService=false;
                }
                Intent intent=new Intent(MainActivity.this,MyService.class);
                stopService(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent=new Intent(MainActivity.this,MyService.class);
        startService(intent);
        bindService(intent,serviceConnection, Context.BIND_AUTO_CREATE);
    }


    @Override
    protected void onStop() {
        super.onStop();
        if(wService) {
         unbindService(serviceConnection);
         wService=false;
        }
    }

    private ServiceConnection serviceConnection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyService.MyBinder myBinder=(MyService.MyBinder) service;
            myService=myBinder.getService();
            wService=true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
           wService=false;
        }
    };
}
