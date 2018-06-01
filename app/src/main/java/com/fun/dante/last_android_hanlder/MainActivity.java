package com.fun.dante.last_android_hanlder;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView textView = findViewById(R.id.Text_View);
//        Handler handler_ = new Handler();
//        handler_.post(new Runnable() {
//            @Override
//            public void run() {
//                textView.setText("123");
//            }
//        });


        final Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                textView.setText(msg.arg1+""+"sssssssss");
            }
        };
        final Runnable myWorker = new Runnable() {
            @Override
            public void run() {
                int progress = 0;
                while(progress<=100)
                {
                    Message msg = new Message();
                    msg.arg1 = progress;
                    handler.sendMessage(msg);
                    progress += 10;
                    try{
                        Thread.sleep(100);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
                Message msg = handler.obtainMessage();
                msg.arg1 = -1;
                handler.sendMessage(msg);
            }
        };
        Button Start = findViewById(R.id.Start);
        Start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Thread workThread = new Thread(null,myWorker,"WorkThread");
                workThread.start();
            }
        });
    }
}
