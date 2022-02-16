package com.kosmo.a01helloandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/*
리스너 부착4 : View.OnClickListener 를 구현한 후 onClick()메서드를
    오버라이딩 하여 사용한다.
 */
public class SubActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        Button button = findViewById(R.id.btnBackMain);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //현재 실행된 액티비티를 종료한다.
        finish();
    }
}