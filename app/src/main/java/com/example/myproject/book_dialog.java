package com.example.myproject;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.example.myproject.Adapter.Book_Adapter;
import com.example.myproject.Model.Book;
import com.example.myproject.ViewModel.fragment_home_ViewModel;
import com.example.myproject.databinding.BookinfoDialogBinding;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Response;

public class book_dialog extends Dialog implements View.OnClickListener {
    private Button modify;
    private Button delete;
    private TextView Name;
    private ImageView bookPicture;
    private int pos;
    private fragment_home_ViewModel viewModel;
    BookinfoDialogBinding binding;
    private Book thisBook;
    private List<Book> bookList;

    public book_dialog(Context context, int pos ,fragment_home_ViewModel viewModel, List<Book> bookList) {
        super(context);
        this.pos = pos;
        this.viewModel = viewModel;
        this.bookList = bookList;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        binding = BookinfoDialogBinding.inflate(getLayoutInflater());
        modify = binding.modify;
        delete = binding.delete;

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book del = bookList.get(pos);
                bookList.remove(pos);
                deleteBook(del);

                dismiss();
            }
        });

        Name = binding.diaBookName;
        bookPicture =binding.bookPicture;
        thisBook = viewModel.getBookByPos(pos);
        Name.setText(thisBook.getBookName());
        byte[] bytes = thisBook.getBookPicture();
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        bookPicture.setImageBitmap(bitmap);
        setContentView(binding.getRoot());
    }

    @Override
    public void onClick(View v) {

    }


    private void deleteBook(Book book){
        int id = book.getBookId();

        String path = okhttpHelper.serverPath + "DeleteBook?id=" +id;

        okhttpHelper okhttpHelper = new okhttpHelper();
        okhttpHelper.async_get(path, new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

            }
        });

    }
}
