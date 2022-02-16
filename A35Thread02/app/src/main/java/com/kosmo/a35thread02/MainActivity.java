package com.kosmo.a35thread02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "lecture";

    TextView textView1;
    Handler handler;
    ProgressBar progressBar1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler = new Handler();

        textView1 = findViewById(R.id.textView1);
        progressBar1 = findViewById(R.id.progressBar1);
    }

    public void onBtn1Clicked(View v) {
        RequestThread thread = new RequestThread();
        thread.start();
    }

    class RequestThread extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                Log.d(TAG, "Request Thread.." + i);

                /*
                별도의 핸들러 클래스를 생성하지 않고 핸들러 객체 내에서
                Runnable을 익명클래스 형태로 생성하여 바로 처리한다.
                 */
                final int index = i;
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        //메인 UI에 대한 변경
                        textView1.setText("Request Thread.." + index);
                        //프로그래스바의 진행사항을 1씩 증가시킴
                        progressBar1.incrementProgressBy(1);
                    }
                });

                //0.2초에 한번씩 블럭상태로 전환
                try {
                    Thread.sleep(200);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}