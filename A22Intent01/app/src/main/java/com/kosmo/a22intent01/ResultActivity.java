package com.kosmo.a22intent01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    int[] tvResourceIds = {R.id.tv1, R.id.tv2, R.id.tv3,
            R.id.tv4, R.id.tv5, R.id.tv6,
            R.id.tv7, R.id.tv8, R.id.tv9};

    int[] rbResourceIds = {R.id.rb1, R.id.rb2, R.id.rb3,
            R.id.rb4, R.id.rb5, R.id.rb6,
            R.id.rb7, R.id.rb8, R.id.rb9};

    TextView[] texts = new TextView[9];
    RatingBar[] ratings = new RatingBar[9];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        /*
        Intent를 통해 전달되는 부가데이터는 Bundle객체를 통해 액티비티간에
        전송된다. 액티비티가 실행될때 제일 처음 호출되는 생명주기 메서드가
        onCreate()인데 이때 파라미터의 형태로 전송받게 된다.
         */
        //메서드를 통해 Intent객체를 얻어온다.
        Intent intent = getIntent();
        //번들 객체를 얻어온다.
        Bundle bundle = intent.getExtras();
        //투표수를 가져온다.
        int[] votes = bundle.getIntArray("votes");
        //영화제목을 가져온다.
        String[] titles = intent.getStringArrayExtra("titles");

        //텍스트뷰와 레이팅바에 값을 세팅한다.
        for (int i = 0; i < votes.length; i++) {
            ((TextView)findViewById(tvResourceIds[i])).setText(titles[i]);
            ((RatingBar)findViewById(rbResourceIds[i])).setRating(votes[i]);
        }

        ((Button)findViewById(R.id.btn_main))
                .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //해당 액티비티를 종료(destroy)
                finish();
            }
        });
    }
}