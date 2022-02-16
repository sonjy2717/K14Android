package com.kosmo.a01practice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),
                        "지현이는 바보래요",
                        Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://naver.com"));
                startActivity(intent);
            }
        });

        editText = findViewById(R.id.web);
        Button btnWeb = findViewById(R.id.btnWeb);
        btnWeb.setOnClickListener(listener);

        Button btnFool = findViewById(R.id.btnFool);
        btnFool.setOnClickListener(listener);
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.btnWeb) {
                String url = editText.getText().toString();

                if (url.equals("")) {
                    Toast.makeText(v.getContext(),
                            "URL 입력이 잘못되었다 닝겐!",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://"+url));
                startActivity(intent);
            }
            else if (v.getId() == R.id.btnFool) {
                Toast.makeText(v.getContext(),
                        "바보 지현이 왕국으로 이동합니다",
                        Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(v.getContext(), SubActivity.class);
                startActivity(intent);
            }
        }
    };

    public void myBtnClick(View view) {
        Toast.makeText(view.getContext(),
                "오늘의 복습 끝 바보 지현이 사랑해!",
                Toast.LENGTH_LONG).show();
    }
}