package com.example.myproject.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.myproject.Model.Book;
import com.example.myproject.Repository.home_Repository;

import java.util.List;

public class fragment_home_ViewModel extends AndroidViewModel {
    private final home_Repository repository;
    private MutableLiveData<List<Book>> book_list;
    private String res;

    public fragment_home_ViewModel(@NonNull Application application) {
        super(application);
        repository = new home_Repository(application);
        book_list = new MutableLiveData<>();
        book_list.postValue(repository.getList());

    }

    public String getRes() {
        //Log.d("2",res);
        return res;
    }

    public MutableLiveData<List<Book>> getBook_list() {
        return book_list;
    }
}
