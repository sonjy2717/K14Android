package com.kosmo.a11alert;

import android.app.AlertDialog;
import android.content.Context;
import android.view.Gravity;
import android.widget.TextView;

public class MyAlert {

    //1번 경고창 : 내용만 있음.
    public static void AlertShow(Context context, String msg) {
        //경고창을 띄우기 위해 Builder 객체를 생성
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        /*
        안드로이드의 물리적 Back버튼에 대한 동작방식을 설정한다.
        AlertDialog를 띄운 상태에서 Back버튼을 눌렀을때 대화창이 닫히지 않게 해준다.
        true로 지정한 경우 빈공간을 누르거나 Back버튼을 누르면 닫히게 된다.
         */
        builder.setCancelable(true);
        //메세지
        builder.setMessage("\n" + msg + "\n" + "Back버튼으로 닫힘");
        //확인버튼
        builder.setPositiveButton("ㅇㅈ", null);

        //앞에서 설정한 내용으로 대화창을 생성하고 화면에 표시한다.
        AlertDialog alert = builder.create();
        alert.show();

        //메세지를 가운데 정렬하기 위한 코드로 주석처리하면 좌측 정렬된다.
        TextView messageView = alert.findViewById(android.R.id.message);
        messageView.setGravity(Gravity.CENTER);
    }

    //2번 경고창 : 타이틀, 아이콘, 메세지 등이 포함됨
    public static void AlertShow(Context context, String msg, String title) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        //안드로이드 시스템이 기본적으로 제공하는 아이콘 사용
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        //제목 설정
        builder.setTitle(title);
        //Back버튼 동작 여부 설정
        builder.setCancelable(false);
        //메세지
        builder.setMessage("\n" + msg + "\n" + "Back버튼 동작 안됨");
        //확인 버튼
        builder.setPositiveButton("ㅇㅈ", null);


        AlertDialog alert = builder.create();
        alert.show();

        //AlertDialog의 가운데 정렬을 위한 setting
        //Must call show() prior to fetching text view
        TextView messageView = alert.findViewById(android.R.id.message);
        messageView.setGravity(Gravity.CENTER);
    }
}
