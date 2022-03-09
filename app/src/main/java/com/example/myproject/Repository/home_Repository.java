package com.example.myproject.Repository;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.myproject.Model.Book;
import com.example.myproject.Project_DataBase;
import com.example.myproject.okhttpHelper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class home_Repository {
    MutableLiveData<List<Book>> list  = new MutableLiveData<>();
    String path = okhttpHelper.serverPath + "getBook";
    String bookListJson = "";


    public home_Repository(Application application) {
        Project_DataBase dataBase = Project_DataBase.getInstance(application);
        setList();
    }

    private void setList() {
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();



        getBook(new getCallBack1<String>() {
            @Override
            public void onValue(String value) {
                bookListJson = value;
                JsonArray jsonArray = parser.parse(bookListJson).getAsJsonArray();
                List<Book> tmp = new ArrayList<>();
                for (JsonElement obj : jsonArray) {
                    Book book = gson.fromJson(obj, Book.class);
                    tmp.add(book);
                }
                list.postValue(tmp);
            }
        });




    }

    private void getBook(getCallBack1 getCallBack) {
        okhttpHelper okhttpHelper = new okhttpHelper();
        okhttpHelper.async_get(path, new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String back = response.body().string();
                getCallBack.onValue(back);
            }
        });


    }

    private interface getCallBack1<T> {
        void onValue(T value);
    }

    public MutableLiveData<List<Book>> getList() {
        return list;
    }


}
