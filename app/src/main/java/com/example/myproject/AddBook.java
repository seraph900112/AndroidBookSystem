package com.example.myproject;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myproject.Model.Book;
import com.example.myproject.databinding.ActivityAddbookBinding;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;

public class AddBook extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    private static final int RES_CODE = 55688;
    private ActivityAddbookBinding binding;
    private EditText bookId;
    private EditText bookName;
    private EditText writer;
    private Spinner spinner;
    private ImageView bookImage;
    private String bookType;
    private Button SetPicture;
    private Button submit;
    private TextView test;
    private String res;
    public static final MediaType JSON = MediaType.get("text/html; charset=utf-8");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddbookBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        bookId = binding.InputBookId;
        bookName = binding.InputBookName;
        writer = binding.InputWriter;
        spinner = binding.BookTypeSpinner;
        bookImage = binding.BookImage;
        SetPicture = binding.addBook;
        submit = binding.submit;
        test = binding.Test;
        bookImage.setDrawingCacheEnabled(true);
        submit.setOnClickListener(this);
        SetPicture.setOnClickListener(this);

        //setSpinnerAdapter
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.BookType, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == SetPicture.getId()) {

            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, RES_CODE);

        } else if (v.getId() == submit.getId()) {

            sendBook(new GetCallBack<String>() {
                @Override
                public void onValue(String value) {
                    res = value;
                }
            });

            Toast.makeText(this, res, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        bookType = adapterView.getItemAtPosition(position).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, requestCode, intent);
        if (resultCode == RESULT_OK && requestCode == RES_CODE) {
            Bundle bundle = intent.getExtras();
            Bitmap bitmap = (Bitmap) bundle.get("data");
            bookImage.setImageBitmap(bitmap);
        }
    }

    private interface GetCallBack<T> {
        void onValue(T value);
    }

    public void sendBook(GetCallBack getCallBack) {

        int BookId = Integer.parseInt(bookId.getText().toString());
        String BookName = bookName.getText().toString();
        String Writer = writer.getText().toString();
        Bitmap bitmap = bookImage.getDrawingCache();

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
        byte[] bookPicture = bos.toByteArray();

        Book book = new Book(BookId, BookName, bookType, Writer, bookPicture);
        Gson gson = new GsonBuilder().create();
        String jsonStr = gson.toJson(book) ;
        String path = okhttpHelper.serverPath + "AddBook";
        FormBody formBody = new FormBody.Builder().add("Book", jsonStr).build();

        Callback callback = new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String back = response.body().string();
                getCallBack.onValue(back);
            }
        };

        okhttpHelper okhttpHelper = new okhttpHelper();
        okhttpHelper.async_post(path, formBody, callback);
    }
}