package com.kosmo.a31fragmentlistview;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MenuFragment2 extends Fragment {

    public static final String TAG = "kosmo123";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "MenuFragment2 > onCreateView()");
        View view = inflater.inflate(R.layout.fragment_menu2, container, false);

        ((Button)view.findViewById(R.id.button2)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Button)view.findViewById(R.id.button2)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
            	        /*Toast.makeText(view.getContext(),
                    	"두번째 프레그먼트 입니다.",
                    	Toast.LENGTH_LONG).show();*/

                        //토스트를 커스텀하기 위한 XML파일을 전개하여 View객체 생성
                        View ct = View.inflate(view.getContext(), R.layout.custom_toast, null);
                        //텍스트뷰를 가져온다.
                        TextView textView = ct.findViewById(R.id.message_tv);
                        //텍스트 설정(내용, 크기, 색깔)
                        textView.setText("토스트도 커스텀 되네요");
                        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
                        textView.setTextColor(Color.BLACK);

                        //토스트 객체생성
                        Toast toast = new Toast(view.getContext());
                        //유지 시간
                        toast.setDuration(Toast.LENGTH_LONG);
                        //커스텀 레이아웃 설정
                        toast.setView(ct);
                        //화면에 토스트를 출력한다.
                        toast.show();
                    }
                });
            }
        });

        return view;
    }
}
