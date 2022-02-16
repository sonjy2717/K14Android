package com.kosmo.a30fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

//Java코드로 프레그먼트를 삽입하기 위한 별도의 클래스 정의
public class MyFragment2 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //XML 레이아웃 파일을 화면에 전개하기 위해 inflate() 메서드 호출
        View view = inflater.inflate(R.layout.fragment_my2, container, false);

        //전개한 내용을 View객체에 저장한 후 위젯을 얻어온다.
        view.findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            //리스너에서는 버튼을 누를때 단순히 토스트를 띄워준다.
            @Override
            public void onClick(View v) {
                Toast.makeText(view.getContext(),
                        "두번째 프레그먼트 입니다.",
                        Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}