package com.kosmo.a30fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/*
자동완성을 통해 프레그먼트 생성
    기본패키지 > 우클릭 > new > Fragment > blank
    생성된 후 모든 주석과 메서드를 삭제하고 onCreateView()만 남겨둔다.

    프레그먼트는 액티비티와 비슷하게 화면을 표현하기 위해 생성한다.
    Java파일과 XML파일로 구성되어 있는 것은 동일하지만
    Manifest에 등록되지 않는다.
    즉, 전체화면이라기 보다는 부분화면을 표현할때 주로 사용한다.
 */
public class TopFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_top, container, false);
    }
}