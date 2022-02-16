package com.kosmo.a32tabbar;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Fragment1 extends Fragment {

    private static final String TAG = "lecture";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_1, container, false);

        Button button = rootView.findViewById(R.id.button1);
        button.setOnClickListener(v -> {
            Log.d(TAG, "Fragment1");
        });

        return rootView;
    }
}