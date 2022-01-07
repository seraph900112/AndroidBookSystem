package com.example.myproject.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.myproject.Model.login_user;
import com.example.myproject.Repository.login_Repository;

import java.util.List;

public class loginUser_ViewModel extends AndroidViewModel {
    private final login_Repository repository;
    private LiveData<List<login_user>> loginUser;

    public loginUser_ViewModel(@NonNull Application application) {
        super(application);
        repository = new login_Repository(application);
        loginUser = repository.getLogin_users();
    }

    public LiveData<List<login_user>> getLoginUser() {
        return loginUser;
    }
}


