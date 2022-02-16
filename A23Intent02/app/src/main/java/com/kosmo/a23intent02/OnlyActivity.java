package com.kosmo.a23intent02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class OnlyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_only);

        //부가데이터로 전달된 값을 받아서 변수에 저장한다.
        String user = getIntent().getStringExtra("USER");
        String pass = getIntent().getStringExtra("PASS");

        //텍스트뷰에 값을 설정하여 출력한다.
        ((TextView)findViewById(R.id.textview_only)).setText(
                String.format("아이디:%s, 비밀번호:%s", user, pass));
        
        //해당 액티비티의 실행 종료
        findViewById(R.id.btn_finish).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}