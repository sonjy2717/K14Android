package com.kosmo.a27gridview01;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "KOSMO";

    String[] names = {
            "엑소1", "방탄소년단1", "워너원1", "뉴이스트1", "갓세븐1", "비투비1", "빅스1",
            "엑소2", "방탄소년단2", "워너원2", "뉴이스트2", "갓세븐2", "비투비2", "빅스2",
            "엑소3", "방탄소년단3", "워너원3", "뉴이스트3", "갓세븐3", "비투비3", "빅스3"
    };
    String[] ages = {
            "9", "7", "11", "5", "7", "7", "6",
            "923", "1237", "1451", "3245", "347", "1237", "456",
            "923", "1257", "121", "556", "723", "237", "6324"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //어댑터 객체 생성(커스텀 + 데이터 적용)
        final MyAdapter adapter = new MyAdapter();
        //그리드뷰 위젯 가져오기
        GridView gridView1 = findViewById(R.id.gridView1);
        //어댑터와 그리드뷰 연결
        gridView1.setAdapter(adapter);
        //리스너
        gridView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //콜백되는 index를 통해 행과 열을 계산해서 로그로 출력한다.
                int row = position / 2;
                int column = position % 2;
                Log.d(TAG, "position : " + position);
                Log.d(TAG, "Row index : " + row + " Column index : " + column);
                Log.d(TAG, names[row * 2 + column]);

                //토스트로 출력
                Toast.makeText(getApplicationContext(),
                        "그룹명 : " + names[position] +
                                ", 인원수 : " + ages[position],
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    //커스텀 어댑터 생성
    class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return names.length;
        }

        @Override
        public Object getItem(int position) {
            return names[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //Java코드로 리니어 레이아웃 생성
            LinearLayout layout = new LinearLayout(getApplicationContext());
            //방향은 수직으로 설정
            layout.setOrientation(LinearLayout.VERTICAL);

            //텍스트뷰1 : 그룹명
            TextView view1 = new TextView(getApplicationContext());
            view1.setText(names[position]);
            view1.setTextSize(25.0f);
            view1.setTextColor(Color.BLUE);

            //텍스트뷰2 : 인원
            TextView view2 = new TextView(getApplicationContext());
            view2.setText(ages[position]);
            view2.setTextSize(25.0f);
            view2.setTextColor(Color.RED);

            //레이아웃에 위젯 추가
            layout.addView(view1);
            layout.addView(view2);

            return layout;
        }
    }
}