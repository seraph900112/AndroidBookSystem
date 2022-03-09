package com.example.myproject;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myproject.Adapter.Book_Adapter;
import com.example.myproject.Model.Book;
import com.example.myproject.ViewModel.fragment_home_ViewModel;
import com.example.myproject.databinding.FragmentNavHomeBinding;

import java.util.List;


public class Fragment_nav_home extends Fragment {
    FragmentNavHomeBinding binding;
    private fragment_home_ViewModel viewModel;
    private Book_Adapter adapter;
    private RecyclerView recyclerView;
    private TextView textView1;
    private TextView textView2;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentNavHomeBinding.inflate(inflater, container, false);
        textView1 = binding.textView1;
        textView2 = binding.textView2;
        recyclerView = binding.bookShow;

        viewModel = new ViewModelProvider(requireActivity()).get(fragment_home_ViewModel.class);

        //setRecyclerView
        adapter = new Book_Adapter(getContext(),viewModel);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        viewModel.getBook_list().observe(requireActivity(), books -> adapter.setBookList(books));

        // Inflate the layout for this fragment
        textView1.setText(viewModel.getRes());

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    public fragment_home_ViewModel getViewModel(){
        return viewModel;
    }

}