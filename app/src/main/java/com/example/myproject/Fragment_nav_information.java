package com.example.myproject;

import static android.app.Activity.RESULT_OK;

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
    private Button test;
    private Button add;
    private ImageView photo;
    private final int RES_CODE = 55688;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentNavInformationBinding.inflate(inflater, container, false);
        test = binding.testPhoto;
        photo = binding.photo;
        add = binding.add;
        test.setOnClickListener(this);
        add.setOnClickListener(this);
        viewModel = ViewModelProviders.of(this).get(fragment_information_ViewModel.class);
        if (viewModel.getPhoto() != null) {
            viewModel.getPhoto().observe(getViewLifecycleOwner(), bitmap -> {
                if (bitmap != null) {
                    photo.setImageBitmap(bitmap);
                }
            });
        }


        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == test.getId()) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, RES_CODE);
        }else if(v.getId() == add.getId()){
            Intent intent = new Intent(getContext(),AddBook.class);
            startActivity(intent);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, requestCode, intent);
        if (resultCode == RESULT_OK && requestCode == RES_CODE) {
            Bundle bundle = intent.getExtras();
            Bitmap bitmap = (Bitmap) bundle.get("data");
            viewModel.setPhoto(bitmap);
            photo.setImageBitmap(bitmap);

        }
    }
}