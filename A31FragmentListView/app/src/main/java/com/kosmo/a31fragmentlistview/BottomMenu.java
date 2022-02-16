package com.kosmo.a31fragmentlistview;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class BottomMenu extends Fragment {

    private static String TAG = "iKosmo";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "BottomMenu Class > onCreateView() called.. ");
        return inflater.inflate(R.layout.fragment_bottom_menu, container, false);
    }
}