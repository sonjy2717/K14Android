package com.kosmo.a01helloandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    /*
    전화번호 입력상자를 전역적으로 사용하기 위해 선언
    HTML의 input과 동일하게 입력값을 받기위한 위젯이다.
     */
    private EditText editText;

    /*
    Java에서의 출발점은 main()메서드였듯이 안드로이드의 출발점은
    onCreate()메서드이다. 수명주기(Lifecycle) 메서드 중 첫번째로 실행된다.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*
        xml로 생성된 레이아웃을 액티비티에 전개(Inflate)해주는 메서드로
        해당 메서드가 실행되지 않으면 화면에는 아무것도 보이지 않게된다.
         */
        setContentView(R.layout.activity_main);

        /*
        findViewById(res에 설정한 id값)
            : xml에 설정된 id값을 통해 버튼 객체를 Java클래스로 얻어온다.
            JS의 getElementById()와 동일한 역할을 한다.
        res폴더는 resources의 줄임말로 접근시에는 R객체를 사용한다.
         */
        Button btnNate = findViewById(R.id.btnNate);

        //리스너 부착1 : 버튼 객체에 직접 리스너 인터페이스를 사용한다.
        btnNate.setOnClickListener(new View.OnClickListener() {
            /*
            View.OnClickListener 인터페이스는 onClick() 추상메서드를 포함하고 있으므로
            반드시 아래 메서드를 오버라이딩 해야한다.
             */
            @Override
            public void onClick(View v) {
                /*
                버튼을 누르면 토스트 메세지를 띄워준다. 토스트는 JS의 alert()와 유사하게
                메세지를 일정시간 보여준다.
                형식] Toast.makeText(메세지를 띄울 컨텍스트(뷰),
                                     메세지 내용,
                                     시간(Long or Short)).show();
                 */
                Toast.makeText(v.getContext(),
                        "잠시후 네이트로 이동합니다",
                        Toast.LENGTH_LONG).show();

                /*
                인텐트를 통해 새로운 액티비티를 띄워준다. 웹 URL이 전달되므로
                내장 웹브라우저를 통해 네이트로 접속한다.
                 */
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://nate.com"));
                startActivity(intent);
            }
        });

        //전화걸기 앱을 통해 입력한 전화번호를 전달한다.
        //전화번호가 입력된 에디트 텍스트를 가져온다.
        editText = findViewById(R.id.editText);
        //전화걸기 버튼을 가져온다.
        Button btnCall = findViewById(R.id.btnCall);
        //전화걸기 리스너 부착
        btnCall.setOnClickListener(listener);

        //화면전환 버튼을 눌렀을때 내부의 SubActivity를 띄워준다.
        Button btnNext = findViewById(R.id.btnNext);
        btnNext.setOnClickListener(listener);

    }////end of onCreate()

    //리스너 부착2 : onCreate()메서드 외부에 리스너를 별도로 선언한다.
    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            /*
            버튼을 누를때 전달되는 View객체를 통해 눌러진 버튼의 id를 알아 낼 수 있다.
             */
            if (v.getId() == R.id.btnCall) {
                //전화걸기 버튼을 눌렀다면 입력상자에 입력된 전화번호를 가져와서 문자열로 변경
                String callNumber = editText.getText().toString();
                //인텐트를 통해 tel:과 함께 전화번호를 전달한다.
                Intent intent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("tel:" + callNumber));
                //전화걸기 앱을 실행한다.
                startActivity(intent);
            }
            else if (v.getId() == R.id.btnNext) {
                //화면전환 버튼을 눌렀을때는 서브액티비티를 띄워준다.
                Intent intent = new Intent(v.getContext(), SubActivity.class);
                startActivity(intent);
            }
        }
    };

    /*
    리스너 부착3 : 버튼의 onClick속성을 이용한다. 이때 호출할 메서드명을
        기술하면 해당 메서드를 즉시 호출할 수 있다. 단, 메서드 선언시
        매개변수로 View객체를 반드시 사용해야 한다.

     */
    public void myBtnClick(View view) {
        Toast.makeText(view.getContext(),
                "onClick 속성을 이용한 버튼입니다. 전 짧아요",
                Toast.LENGTH_SHORT).show();
    }

}////end of MainActivity