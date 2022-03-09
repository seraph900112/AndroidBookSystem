package com.example.myproject;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.myproject.Model.User;
import com.example.myproject.ViewModel.fragment_me_ViewModel;
import com.example.myproject.databinding.FragmentNavMeBinding;


public class Fragment_nav_me extends Fragment {
    FragmentNavMeBinding binding;
    private User user;
    private TextView textView;
    SharedPreferences preferences ;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
         preferences = context.getSharedPreferences("USER", 0);
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentNavMeBinding binding = FragmentNavMeBinding.inflate(inflater, container, false);
        fragment_me_ViewModel viewModel = ViewModelProviders.of(requireActivity()).get(fragment_me_ViewModel.class);

        viewModel.setUserId(preferences.getInt("USERID",1));
        user = viewModel.getUser();
        textView =binding.userName;
        textView.setText(user.getUsername());
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}