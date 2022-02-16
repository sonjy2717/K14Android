package com.kosmo.a32tabbar;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Fragment2 extends Fragment {

    private static final String TAG = "lecture";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_2, container, false);

        Button button = rootView.findViewById(R.id.button2);
        button.setOnClickListener(v -> {
            Log.d(TAG, "Fragment2");
        });
        return rootView;
    }
}