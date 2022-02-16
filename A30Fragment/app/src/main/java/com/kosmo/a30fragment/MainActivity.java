package com.kosmo.a30fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //프레그먼트 사용을 위한 관리자 객체와 화면교체용 프레그먼트 객체 생성
    FragmentManager fragmentManager;
    MyFragment1 myFragment1;
    MyFragment2 myFragment2;
    MyFragment3 myFragment3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn1Frag = findViewById(R.id.btnFirstFragment);
        Button btn2Frag = findViewById(R.id.btnSecondFragment);
        Button btn3Frag = findViewById(R.id.btnThirdFragment);

        btn1Frag.setOnClickListener(listener);
        btn2Frag.setOnClickListener(listener);
        btn3Frag.setOnClickListener(listener);

        //프레그먼트 교체를 위한 매니저 객체 생성 및 트랜젝션 객체 생성
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction =
                fragmentManager.beginTransaction();

        //부분 화면을 전담할 프레그먼트 객체 생성
        myFragment1 = new MyFragment1();
        myFragment2 = new MyFragment2();
        myFragment3 = new MyFragment3();

        /*
        첫번째 프레그먼트로 Bundle객체를 통해 데이터를 전달한다.
        Bundle객체의 사용법은 Map컬렉션과 거의 유사하다. Key와 Value로 구성된다.
         */
        Bundle bundle = new Bundle();
        bundle.putString("KOSMO", "한국소프트웨어인재개발원");
        myFragment1.setArguments(bundle);//프레그먼트1로 데이터를 전송한다.

        /*
        앱을 처음 실행할때 fragment1을 화면에 즉시 적용한다.
        replace()는 교체를 의미하고, commit()은 적용을 의미한다.
         */
        fragmentTransaction.replace(R.id.bottomLayout, myFragment1).commit();
    }

    //프레그먼트 전환 버튼에 리스너 처리
    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            /*
            버튼 클릭시 전달된 View객체를 통해 리소스 아이디를 가져올 수 있다.
            버튼을 아이디로 구분하여 해당 프레그먼트로 교체 및 적용한다.
             */
            if (v.getId() == R.id.btnFirstFragment) {
                fragmentManager.beginTransaction().replace(R.id.bottomLayout,
                        myFragment1).commit();
            }
            else if (v.getId() == R.id.btnSecondFragment) {
                fragmentManager.beginTransaction().replace(R.id.bottomLayout,
                        myFragment2).commit();
            }
            else if (v.getId() == R.id.btnThirdFragment) {
                fragmentManager.beginTransaction().replace(R.id.bottomLayout,
                        myFragment3).commit();
            }
        }
    };

    /*
    프레그먼트1은 내부클래스로 정의한다.
    프레그먼트 정의시
    별도의 클래스로 정의하는 방법과
    내부클래스로 정의하는 방법
    2가지 모두 사용할 수 있다.

    별도로 제작할 경우 Fragment 클래스를 상속한 후
    onCreateView() 메서드를 오버라이딩 해야 한다.
    마지막으로 레이아웃을 표현할 XML파일을 inflate(전개)한다.
     */
    public static class MyFragment1 extends Fragment {
        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater,
                                 @Nullable ViewGroup container,
                                 @Nullable Bundle savedInstanceState) {

            /*
            자바코드로 프레그먼트를 붙힐 경우 세번째 인자는 false로 기술해야 한다.
            그렇지 않으면 ANR(앱 응답없음)이 발생한다.
             */
            View view = inflater.inflate(R.layout.fragment_my1, container, false);

            Button button1 = view.findViewById(R.id.button1);
            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /*
                    프레그먼트 교체시 번들객체를 통해 전달된 데이터를 아래와 같이 받아서
                    사용할 수 있다.
                     */
                    Bundle bundle = getArguments();
                    String value = bundle.getString("KOSMO");
                    Toast.makeText(view.getContext(), value,
                            Toast.LENGTH_SHORT).show();
                }
            });

            return view;
        }
    }
}