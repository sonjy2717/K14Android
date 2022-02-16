package com.kosmo.a26listview03;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

//선형 레이아웃을 상속하여 리스트뷰의 하나의 항목을 대체할 클래스를 선언한다
public class IdolView extends LinearLayout {

    //XML레이아웃에서 출력할 항목을 멤버변수로 선언한다.
    TextView textView1;//팀명
    TextView textView2;//인원수
    ImageView imageView1;//이미지

    //생성자 메서드
    public IdolView(Context context) {
        super(context);

        //레이아웃 전개를 위해 LayoutInflater 객체를 생성한다.
        LayoutInflater inflater =
                (LayoutInflater)context.getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);

        //부분뷰로 제작한 XML파일을 해당 Java파일에 전개한다.(XML과 Java를 연결하는 부분)
        inflater.inflate(R.layout.idol_view, this, true);

        //데이터를 출력할 위젯을 얻어온다.
        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        imageView1 = findViewById(R.id.imageView1);
    }

    //각 항목을 설정할 Setter() 메서드 정의
    public void setName(String name) {
        textView1.setText(name);
    }

    public void setPerson(String pCount) {
        textView2.setText(pCount);
    }

    public void setImage(int imgNum) {
        imageView1.setImageResource(imgNum);
    }
}