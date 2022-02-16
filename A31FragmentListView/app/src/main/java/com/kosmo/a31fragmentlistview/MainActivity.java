package com.kosmo.a31fragmentlistview;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "kosmo123";

    //프레그먼트 매니저와 각 화면을 구성할 프레그먼트 선언언
   FragmentManager fragmentManager;
    MenuFragment1 menuFragment1;
    MenuFragment2 menuFragment2;
    MenuFragment3 menuFragment3;
    MenuFragment4 menuFragment4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //하단 탭메뉴의 버튼 위젯 가져오기 및 리스너 부착
        Button button1 = (Button)findViewById(R.id.btnFirstFragment);
        Button button2 = (Button)findViewById(R.id.btnSecondFragment);
        Button button3 = (Button)findViewById(R.id.btnThirdFragment);
        Button button4 = (Button)findViewById(R.id.btnFourthFragment);
        button1.setOnClickListener(listener);
        button2.setOnClickListener(listener);
        button3.setOnClickListener(listener);
        button4.setOnClickListener(listener);

        //프레그먼트 매니저 객체 생성
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        //각 화면의 프레그먼트 객체 생성
        menuFragment1 = new MenuFragment1();
        menuFragment2 = new MenuFragment2();
        menuFragment3 = new MenuFragment3();
        menuFragment4 = new MenuFragment4();

        //앱 실행시 첫번째 프레그먼트로 화면 교체체
       fragmentTransaction.replace(R.id.mainLayout, menuFragment1).commit();
    }

    //각 버튼에 대한 리스너 선언
    View.OnClickListener listener = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            //각 버튼을 눌렀을때 교체할 프레그먼트 선언
            if(view.getId()==R.id.btnFirstFragment){
                fragmentManager.beginTransaction().replace(R.id.mainLayout, menuFragment1).commit();
            }
            else if(view.getId()==R.id.btnSecondFragment){
                fragmentManager.beginTransaction().replace(R.id.mainLayout, menuFragment2).commit();
            }
            else if(view.getId()==R.id.btnThirdFragment){
                fragmentManager.beginTransaction().replace(R.id.mainLayout, menuFragment3).commit();
            }
            else if(view.getId()==R.id.btnFourthFragment){
                fragmentManager.beginTransaction().replace(R.id.mainLayout, menuFragment4).commit();
            }
        }
    };
}
