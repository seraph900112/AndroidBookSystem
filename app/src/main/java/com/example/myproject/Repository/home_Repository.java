package com.example.myproject.Repository;

import android.app.Application;

import com.example.myproject.Model.Book;
import com.example.myproject.Project_DataBase;
import com.example.myproject.okhttpHelper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

public class home_Repository {
    List<Book> list;
    String path = "http://192.168.1.103:8080/WebServer/getBook";


    public home_Repository(Application application) {
        Project_DataBase dataBase = Project_DataBase.getInstance(application);
        setList();
    }

    private void setList() {
        list = new ArrayList<>();

        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        getThread thread = new getThread();
        thread.start();
        try { thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String bookListJson = thread.getBookListJson();

        JsonArray jsonArray = parser.parse(bookListJson).getAsJsonArray();
        for (JsonElement obj : jsonArray) {
            Book book = gson.fromJson(obj, Book.class);
            list.add(book);
        }
    }

    public List<Book> getList() {
        return list;
    }

    public class getThread extends java.lang.Thread {
        String bookListJson;

        @Override
        public void run() {
            okhttpHelper okhttpHelper = new okhttpHelper();
            bookListJson = okhttpHelper.get(path);
        }

        public String getBookListJson() {
            return bookListJson;
        }
    }
}
