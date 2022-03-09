package com.example.myproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myproject.databinding.FragmentGetBookInfoBinding;


public class Fragment_getBookInfo extends Fragment {
    FragmentGetBookInfoBinding binding;
    TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentGetBookInfoBinding.inflate(inflater,container,false);
        textView = binding.myText;
        // Inflate the layout for this fragment
        return binding.getRoot();
    }
}