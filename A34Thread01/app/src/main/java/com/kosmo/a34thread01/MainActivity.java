package com.kosmo.a34thread01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "lecture";

    TextView textView1;
    Button button1;
    ProgressHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView1 = findViewById(R.id.textView1);
        button1 = findViewById(R.id.button1);

        //앱이 실행도면 해당 객체를 생성한다.
        handler = new ProgressHandler();
    }

    public void onBtn1Clicked(View v) {
        //두번 누를수 없도록 비활성화 한다.
        button1.setEnabled(false);

        /*
        쓰레드 객체를 생성한 후 start()를 통해 쓰레드를 생성한다.
        쓰레드 객체의 main은 run()이지만, start()를 통해 간접적으로 호출해야
        쓰레드가 생성된다. 만약 run()을 직접 호출하면 단순한 실행만 되고
        쓰레드는 생성되지 않는다.
         */
        RequestThread thread = new RequestThread();
        thread.start();
    }

    //Thread를 상속하여 쓰레드 클래스를 생성한다.
    class RequestThread extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 20; i++) {
                /*
                서브쓰레드에서 메인쓰레드의 UI(위젯)으로 접근은 불가능하다.
                : 서브쓰레드에서 메인쓰레드의 UI를 접근하면 ANR이 발생된다.
                이때 앱은 강제종료 된다. 서브쓰레드의 접근이 메인쓰레드의 동작에
                영향을 미칠수 있으므로 접근을 원칙적으로 제한하기 때문이다.

                ANR이란?
                    ANR은 Application Not Responding의 약자로 그대로 해석해보면
                    의미를 쉽게 파악할 수 있다. '애플리케이션이 응답하지 않는다.'
                    이 에러의 원인은 Main Thread(UI Thread)가 일정 시간
                    어떤 Task에 잡혀 있으면 발생하게 된다.
                 */
                Log.d(TAG, "Request Thread.." + i);
                //textView1.setText("Request Thread.." + i); ==> 에러발생됨

                /*
                서브쓰레드의 직접적인 접근이 허용되지 않으므로 간접접근을 위해
                Message객체를 사용한다. Bundle객체에 전달할 데이터를 추가하여
                Handler객체에 Message를 보내는 형식이다.
                 */
                //핸들러 객체로 전달한 메세지(데이터)를 작성한다.
                Message msg = handler.obtainMessage();
                //메세지는 번들 객체에 저장한다.
                Bundle bundle = new Bundle();
                bundle.putString("data1", "Request Thread.." + i);
                bundle.putString("data2", String.valueOf(i));
                //데이터를 세팅한 후 핸들러로 전송한다.
                msg.setData(bundle);
                handler.sendMessage(msg);

                //쓰레드를 1초에 한번씩 Block상태로 변환한다.
                try {
                    Thread.sleep(1000);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /*
    핸들러 클래스 생성
        : 메인쓰레드에서 생성한 UI(위젯)는 서브쓰레드에서 접근할 수 없으므로
        핸들러 객체를 통해 간접적으로 접근하여 변경한다. 메세지를 통해 데이터를
        전달하고 이를 통해 UI를 변경한다.
     */
    class ProgressHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            //번들객체를 통해 전달된 데이터를 확인한다.
            Bundle bundle = msg.getData();
            String data1 = bundle.getString("data1");
            String data2 = bundle.getString("data2");

            //핸들러 객체에서 UI에 접근하여 텍스트를 변경한다.
            textView1.setText(data1);

            /*
            전체 20번 반복한 후 버튼과 텍스트뷰를 원상태로 설정한다.
            쓰레드의 동작이 끝나기 전에는 버튼을 누를수 없도록 비활성화 상태를 유지한다.
             */
            if (data2.equals("19")) {
                textView1.setText("쓰레드 테스트");
                button1.setEnabled(true);
            }
            else {
                button1.setEnabled(false);
            }
        }
    }
}