package com.kosmo.a11alert;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //타이틀이 있는 대화창 오픈
    public void onBtn1Clicked(View v) {
        //버튼1 : 기본형
        MyAlert.AlertShow(MainActivity.this, "지현이가 바보라고 생각하시나요?",
                "알림");
    }

    //내용만 있는 대화창 오픈
    public void onBtn2Clicked(View v) {
        //버튼2 : 상단 없애고 내용만
        MyAlert.AlertShow(MainActivity.this, "지현이가 바보라고 생각하시나요?");
    }

    /*
    대화창 : 확인, 취소 버튼에 리스너 부착 후 동작할 내용을 정의한다.
        AlertDialog창을 띄우는 절차
        1. AlertDialog.Builder 객체 생성
        2. 생성자를 통한 여러가지 설정(타이틀, 메세지, 아이콘 등)
        3. 2번에서 생성한 객체를 통해 create()메서드 호출
        4. show()메서드를 호출하여 화면에 출력
     */
    public void onBtn3Clicked(View v) {
        //버튼3 : 버튼 처리
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        builder.setMessage("지현이가 바보라고 생각하시나요?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("알림")
                .setCancelable(false)
                .setPositiveButton("ㅇㅇ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Yes 버튼을 누르면 매개변수 which로 -1이 전달된다.
                        Toast.makeText(getApplicationContext(),
                                "Yes is " + Integer.toString(which),
                                Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("ㄴㄴ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //No 버튼을 누르면 매개변수 which로 -2가 전달된다.
                        Toast.makeText(getApplicationContext(),
                                "No is " + Integer.toString(which),
                                Toast.LENGTH_SHORT).show();
                    }
                });

        AlertDialog alert = builder.create();
        alert.show();
    }
}