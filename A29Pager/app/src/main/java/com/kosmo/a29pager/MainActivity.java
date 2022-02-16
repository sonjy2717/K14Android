package com.kosmo.a29pager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "lecture";

    ViewPager pager1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Pager객체 생성 및 기본화면을 3으로 설정한다.
        pager1 = findViewById(R.id.pager1);
        pager1.setOffscreenPageLimit(3);
        
        //Pager의 각 화면을 구성할 어댑터 객체 생성 및 설정
        MyPagerAdapter adapter = new MyPagerAdapter();
        pager1.setAdapter(adapter);
    }

    //각 버튼 클릭시 디스플레이 할 화면 설정(인덱스 사용)
    public void onBtn1Clicked(View v) {
        pager1.setCurrentItem(0);
    }
    public void onBtn2Clicked(View v) {
        pager1.setCurrentItem(1);
    }
    public void onBtn3Clicked(View v) {
        pager1.setCurrentItem(2);
    }

    /*
    ListView를 사용하기 위해서는 BaseAdapter클래스를 상속한다.
    ViewPager를 사용할때는 아래와 같이 PagerAdapter클래스를 상속하여
    커스텀 어댑터를 생성한다.
     */
    class MyPagerAdapter extends PagerAdapter {
        //각 화면에서 사용할 데이터를 배열로 선언함
        String[] names = {"홍길동", "강감찬", "을지문덕"};

        //기본적으로 오버라이딩 해야할 메서드
        @Override
        public int getCount() {
            return names.length;
        }
        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view.equals(object);
        }
        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            super.destroyItem(container, position, object);
            pager1.removeView((View)object);
        }
        
        //하나의 화면을 구성하는 메서드
        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            //리니어 레이아웃 생성 및 방향설정
            LinearLayout layout = new LinearLayout(getApplicationContext());
            layout.setOrientation(LinearLayout.VERTICAL);

            //텍스트뷰 생성 및 텍스트 설정
            TextView view1 = new TextView(getApplicationContext());
            
            //각 화면을 보기위해 버튼을 클릭할때 전달된 인덱스를 통해 텍스트 설정
            view1.setText(names[position]);
            view1.setTextSize(40.0f);
            view1.setTextColor(Color.BLUE);
            
            //레이아웃에 텍스트뷰를 추가한다.
            layout.addView(view1);
            
            //앞의 레이아웃을 Pager에 추가한다.
            pager1.addView(layout, position);
            
            //최종적으로 레이아웃 반환
            return layout;
        }
    }
}