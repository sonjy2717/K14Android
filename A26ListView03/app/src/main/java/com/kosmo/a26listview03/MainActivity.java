package com.kosmo.a26listview03;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "KOSMO";

    //데이터 준비(팀명, 인원수, 이미지) -> 차후 JSON으로 대체
    String[] idolGroup = {"엑소", "방탄소년단", "워너원",
            "뉴이스트", "갓세븐", "비투비", "빅스"};
    int[] teamCount = {9, 7, 11, 5, 7, 7, 6};
    int[] images = {R.drawable.idol1, R.drawable.idol2, R.drawable.idol3,
            R.drawable.idol4, R.drawable.idol5, R.drawable.idol6,
            R.drawable.idol7};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //어댑터객체 생성
        final MyAdapter adapter = new MyAdapter();
        //리스트뷰 객체화
        ListView listView1 = findViewById(R.id.listView1);
        //앞에서 준비한 2개를 연결
        listView1.setAdapter(adapter);
        //각 아이템을 클릭했을때의 리스너
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i(TAG, "선택한index : " + position);
                Toast.makeText(getApplicationContext(),
                        "선택한 그룹 : " + idolGroup[position],
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    //커스텀 어댑터 객체 생성
    class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            //전체 항목수
            return idolGroup.length;
        }

        @Override
        public Object getItem(int position) {
            //하나의 항목을 반환
            return idolGroup[position];
        }

        @Override
        public long getItemId(int position) {
            //인덱스를 반환
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //XML리소스 파일로 정의한 레이아웃을 사용하는 IdolView클래스의 객체 생성
            IdolView view = new IdolView(getApplicationContext());
            //setter를 통해 각각의 값을 설정
            view.setName(idolGroup[position]);
            view.setPerson(new Integer(teamCount[position]).toString());
            view.setImage(images[position]);
            //하나의 항목을 반환
            return view;
        }
    }
}