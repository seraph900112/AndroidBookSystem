package com.example.myproject.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.myproject.Model.User;
import com.example.myproject.Repository.main_Repository;

public class fragment_me_ViewModel extends AndroidViewModel {
    private final main_Repository repository;
    private int userId;
    private User user;

    public fragment_me_ViewModel(@NonNull Application application) {
        super(application);
        repository = new main_Repository(application);

    }

    public User getUser() {
        this.user = repository.getMain_user(userId);
        return user;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
