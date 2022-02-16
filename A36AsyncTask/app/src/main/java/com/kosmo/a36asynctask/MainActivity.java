package com.kosmo.a36asynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "KOSMO";

    ProgressBar mProgress1;
    int mProgressStatus = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgress1 = findViewById(R.id.progressBar1);
    }

    public void onBtnClicked(View v) {
        new CounterTask().execute(0);
    }
    /*
    AsyncTask
        : 작업쓰레드와 관련된 복잡한 작업을 쉽게 처리해주는 클래스.
          UI에 대한 비동기 작업을 자동으로 실행해주며
          개발자가 직접 핸들러 클래스를 만들 필요가 없다.
          실행은 execute()메소드를 호출하면 된다.
          전달할 파라미터가 있는 경우 execute(파라미터) 형식으로 사용한다

    [형식]
        AsyncTask<Param, Progress, Result>
            Param : 실행시 작업에 전달되는 값(파라미터)의 타입을 지정
            Progress : 작업의 진행정도를 나타내는 값의 타입을 지정
            Result : 작업의 결과값을 나타내는 타입을 지정
        만약 사용할 필요가 없는 타입이 있다면 Void라고 표기한다. (대문자임에 주의한다)
        또한 모든 매개변수는 가변인자를 사용하여 여러개의 파라미터를 처리할 수 있도록 정의되어 있다.
     */

    class CounterTask extends AsyncTask<Integer, Integer, Integer> {
        // doInBackground() 호출 전 실행된다.
        protected void onPreExecute() {
            Log.i(TAG, "onPreExecute() 실행");
        }
        /*
            execute()가 호출되면 자동으로 doInBackground()가 호출된다.
            해당 클래스에서 실제 작업을 담당한다.
         */
        protected Integer doInBackground(Integer... value) {
            Log.i(TAG, "doInBackground() 실행");
            // 100번 반복한다.
            while (mProgressStatus < 100) {
                try {
                    Thread.sleep(100);
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
                mProgressStatus++;
                // onProgressUpdate()을 호출할때 사용한다.
                publishProgress(mProgressStatus);
            }
            return mProgressStatus;
        }
        /*
            doInBackground() 메서드 실행중 언제든지 호출할 수 있는 메서드로
            해당 메서드를 호출할때는 publishProgress()를 사용한다.
         */
        @Override
        protected  void onProgressUpdate(Integer... value) {
            Log.i(TAG, "onProgressUpdate() 실행");
            mProgress1.setProgress(mProgressStatus);
        }
        /*
            doInBackground()가 실행된 후 결과값을 해당 메서드로 전송한다.
            즉, 정상적으로 종료되었을 때 호출된다.
         */
        @Override
        protected void onPostExecute(Integer integer) {
            Log.i(TAG, "onPostExecute() 실행");
            mProgress1.setProgress(mProgressStatus);
        }
    }
}