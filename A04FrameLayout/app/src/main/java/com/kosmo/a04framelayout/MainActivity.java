package com.kosmo.a04framelayout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_second = findViewById(R.id.btn_second);
        //버튼 객체에 리스너 부착 및 onClick 메서드 오버라이딩딩
       btn_second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //인텐트를 통해 새로운 액티비티를 화면에 띄운다.
                Intent intent = new Intent(getApplicationContext(),
                        FrameActivity.class);
                startActivity(intent);
            }
        });
    }
}