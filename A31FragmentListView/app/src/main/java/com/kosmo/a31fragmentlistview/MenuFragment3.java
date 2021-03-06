package com.kosmo.a31fragmentlistview;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class MenuFragment3 extends Fragment {

    public static final String TAG = "kosmo123";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "MenuFragment3 > onCreateView()");
        View view = inflater.inflate(R.layout.fragment_menu3, container, false);

        ((Button)view.findViewById(R.id.button3)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),
                        "세번째 프레그먼트 입니다.",
                        Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }
}
