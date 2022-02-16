package com.kosmo.a21notification;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    //각 알림을 식별하는 정수형 상수
    public static final int NOTIFICATION_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //알림 버튼 클릭시 호출
    public void sendNotification(View view) {

        //알림바를 클리했을때 이동할 액티비티를 설정한다.
        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://www.google.com"));
        //Intent생성시 즉시 띄우지 않고, 우선 준비만 해놓는다.
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                0, intent, 0);
        //노티매니저 객체 생성 및 빌더객체 생성
        NotificationManager notificationManager =
                (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        Notification.Builder builder;

        //Android8.0(Oreo, API26)이상인 경우 채널생성이 필요하다.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //아래는 채널 생성을 위한 패턴이라 생각.
            NotificationChannel notificationChannel =
                    new NotificationChannel("kosmo_id", "kosmo_name",
                            NotificationManager.IMPORTANCE_DEFAULT  );

            notificationChannel.setDescription("코스모 채널입니다");
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.GREEN);
            notificationChannel.enableVibration(true);
            notificationChannel.setVibrationPattern(new long[]{100, 200,
            100, 200});
            notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
            notificationManager.createNotificationChannel(notificationChannel);

            //Notification.Builder를 사용하여 알림을 설정한다.
            builder = new Notification.Builder(this, "kosmo_id");
        }
        else {
            builder = new Notification.Builder(this);
        }

        builder.setSmallIcon(R.drawable.android);//작은 아이콘
        builder.setContentIntent(pendingIntent);//인텐트객체(클릭시 구글로 이동)
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(),
                R.drawable.android));//큰 아이콘
        builder.setContentTitle("알려드립니당!");//타이틀
        builder.setContentText("서울지역에 호우경보가 발령되었습니당.");//내용1
        builder.setSubText("구글에서 날씨정보를 검색하세욧");//내용2
        /*
        알림방법
            DEFAULT_SOUND : 알림소리가 난다.(단말기에 설정된 사운드)
            DEFAULT_VIBRATE : 진동으로 알려준다.
         */
        builder.setDefaults(Notification.DEFAULT_SOUND);
        /*
        알림바를 클릭했을때 상태바에서 제거할지 여부
            true : 클릭했을때 제거됨
            false : 클릭해도 제거되지 않고 유지됨
         */
        builder.setAutoCancel(true);
        //알림바에 알림을 표시한다.
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }
}