package com.example.myproject.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.myproject.Model.User;
import com.example.myproject.Repository.main_Repository;

public class main_ViewModel extends AndroidViewModel {
    private final main_Repository repository;
    private User user;
    private int userid;


    public main_ViewModel(@NonNull Application application) {
        super(application);
        repository = new main_Repository(application);
        user = repository.getMain_user(userid);
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }
}
