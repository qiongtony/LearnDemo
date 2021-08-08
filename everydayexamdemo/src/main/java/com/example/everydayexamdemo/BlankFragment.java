package com.example.everydayexamdemo;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 重建时走的方法：onDestroyView->onDetach，Activity实例新建，onAttach->onCreateView
 */
public class BlankFragment extends Fragment {
    public BlankFragment() {
        // Required empty public constructor
    }

    public static BlankFragment newInstance() {
        BlankFragment fragment = new BlankFragment();
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("WWS", "onCreate");
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.e("WWS", "onAttach");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.e("WWS", "onCreateView");
        return inflater.inflate(R.layout.fragment_blank, container, false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e("WWS", "onDestroyView");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e("WWS", "onDetach");
    }
}