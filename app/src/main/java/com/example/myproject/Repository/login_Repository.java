package com.example.myproject.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.myproject.DAO.login_dao;
import com.example.myproject.Model.login_user;
import com.example.myproject.Project_DataBase;

import java.util.List;

public class login_Repository {
    private login_dao loginDao;
    private LiveData<List<login_user>> login_users;

    public login_Repository(Application application) {
        Project_DataBase db = Project_DataBase.getInstance(application);
        loginDao = db.loginDao();
        login_users = loginDao.getAllUser();
    }

    public LiveData<List<login_user>> getLogin_users() {
        return login_users;
    }
}
