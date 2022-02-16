package com.kosmo.a13animation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //위젯을 얻어옴
        imageView = findViewById(R.id.kakao_con);
        Button btnMove = findViewById(R.id.btn_move);
        Button btnScale = findViewById(R.id.btn_scale);
        Button btnAlpha = findViewById(R.id.btn_alpha);

        //xml로 생성한 애니메이션 리소스를 기반으로 객체 생성
        final Animation anim1 = AnimationUtils.loadAnimation(
                getApplicationContext(), R.anim.move);
        final Animation anim2 = AnimationUtils.loadAnimation(
                getApplicationContext(), R.anim.alpha);
        final Animation anim3 = AnimationUtils.loadAnimation(
                getApplicationContext(), R.anim.size);

        //이동
        btnMove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.startAnimation(anim1);
            }
        });

        //크기변환
        btnScale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.startAnimation(anim3);
            }
        });

        //투명도 변환
        btnAlpha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.startAnimation(anim2);
            }
        });
    }
}