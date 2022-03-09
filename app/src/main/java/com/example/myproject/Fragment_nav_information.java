package com.example.myproject;

import static android.app.Activity.RESULT_OK;
import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.myproject.ViewModel.fragment_information_ViewModel;
import com.example.myproject.databinding.FragmentNavInformationBinding;


public class Fragment_nav_information extends Fragment implements View.OnClickListener {

    private FragmentNavInformationBinding binding;
    private fragment_information_ViewModel viewModel;
    private Button add;
    private final int RES_CODE = 55688;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentNavInformationBinding.inflate(inflater, container, false);
        add = binding.add;
        add.setOnClickListener(this);

        viewModel = ViewModelProviders.of(this).get(fragment_information_ViewModel.class);

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    @Override
    public void onClick(View v) {
         if(v.getId() == add.getId()){
            Intent intent = new Intent(getContext(),AddBook.class);
            intent.setFlags(FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    }


}