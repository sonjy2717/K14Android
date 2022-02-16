package com.kosmo.a32tabbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "lecture";

    //탭바와 프레그먼트 설정
    TabLayout tabLayout;
    Fragment1 fragment1;
    Fragment2 fragment2;
    Fragment3 fragment3;
    Fragment4 fragment4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //탭바를 가져온다.
        tabLayout = findViewById(R.id.tabMenu);

        //프레그먼트 객체 생성
        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        fragment3 = new Fragment3();
        fragment4 = new Fragment4();

        //첫번째 화면은 fragment1로 지정한다.
        getSupportFragmentManager()
                .beginTransaction().replace(R.id.container, fragment1).commit();

        //탭바에 리스너 설정. 선택한 메뉴의 인덱스를 통해 각 화면을 설정한다.
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //탭메뉴의 인덱스를 통해 프레그먼트를 교체한다.
                Log.d(TAG, "POS:" + tab.getPosition());
                switch (tab.getPosition()) {
                    case 0:
                        getSupportFragmentManager()
                                .beginTransaction().replace(R.id.container, fragment1).commit();
                        break;
                    case 1:
                        getSupportFragmentManager()
                                .beginTransaction().replace(R.id.container, fragment2).commit();
                        break;
                    case 2:
                        getSupportFragmentManager()
                                .beginTransaction().replace(R.id.container, fragment3).commit();
                        break;
                    case 3:
                        getSupportFragmentManager()
                                .beginTransaction().replace(R.id.container, fragment4).commit();
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}