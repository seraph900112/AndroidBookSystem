package com.example.myproject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class okhttpHelper {

    private OkHttpClient client = null;

    public okhttpHelper() {
        client = new OkHttpClient();
    }

    public String get(String path) {

        String res = "";

        Request.Builder builder = new Request.Builder();
        builder.url(path);
        Request request = builder.build();

        try {
            Call call = client.newCall(request);
            Response response = call.execute();
            res = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return res;
    }

    public void async_get(String path, Callback callback) {
        Request request = new Request.Builder()
                .url(path)
                .build();
        Call call = client.newCall(request);

        call.enqueue(callback);
    }


    public void async_post(String path, FormBody formBody, Callback callback) {

        Request request = new Request.Builder()
                .url(path)
                .post(formBody)
                .build();
        Call call = client.newCall(request);
        call.enqueue(callback);
    }

}
