package com.kosmo.a14lifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

/*
Activity의 Lifecycle(수명주기) 메서드
    : 액티비티가 실행되면 아래 순서대로 메서드가 실행된다.
    onCreate() -> onStart() -> onResume()
    액티비티가 종료되면
    onPause() -> onStop() -> onDestroy()

    안드로이드에서 수명주기 메서드는 앱이 비정상적으로 종료되는
    시점의 상태를 저장하거나 앱이 실행될때 자동으로 실행하고자
    하는 명령이 있는 경우 사용한다.
 */
public class SubActivity extends AppCompatActivity {

    private static final String TAG = "KOSMO123";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        Log.d(TAG, "서브액티비티 시작됨");
        Log.d(TAG, "onCreate() 호출");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() 호출");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() 호출");
    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() 호출");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() 호출");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestory() 호출");
    }

    public void subBtnClicked(View view) {
        Log.d(TAG, "서브액티비티 종료버튼 클릭");
        finish();
    }
}