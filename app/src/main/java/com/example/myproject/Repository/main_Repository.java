package com.example.myproject.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.myproject.DAO.main_dao;
import com.example.myproject.Model.Book;
import com.example.myproject.Model.User;
import com.example.myproject.Project_DataBase;

import java.io.IOException;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;

public class main_Repository {
    private main_dao dao;
    private User user;



    public main_Repository(Application application) {
        Project_DataBase dataBase = Project_DataBase.getInstance(application);
        dao = dataBase.main_dao();

    }

    public User getMain_user(int id) {
        searchThread thread = new searchThread(id);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        user = thread.getUser();

        return user;
    }


    class searchThread extends java.lang.Thread {
        int id;
        User user;

        searchThread(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            user = dao.selectUserById(id);
        }

        public User getUser() {
            return user;
        }
    }




}


