package com.example.myproject.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myproject.Model.Book;
import com.example.myproject.R;
import com.example.myproject.ViewModel.fragment_home_ViewModel;
import com.example.myproject.book_dialog;

import java.util.List;

public class Book_Adapter extends RecyclerView.Adapter<Book_Adapter.ViewHolder> {
    private LayoutInflater inflater;
    public List<Book> bookList;
    private fragment_home_ViewModel viewModel;


    public Book_Adapter(Context context, fragment_home_ViewModel viewModel) {
        inflater = LayoutInflater.from(context);
        this.viewModel = viewModel;
    }

    public Book_Adapter() {

    }

    @NonNull
    @Override
    public Book_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.book_item, parent, false);
        return new ViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull Book_Adapter.ViewHolder holder, int position) {
        Book current = bookList.get(position);
        holder.BookName.setText(current.getBookName());
        holder.Writer.setText(current.getWriter());
        byte[] bytes = current.getBookPicture();
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        holder.BookPicture.setImageBitmap(bitmap);
    }

    @SuppressLint("notifyDatasetChanged")
    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (bookList != null) {
            return bookList.size();
        } else return 0;
    }




    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView BookName;
        public final TextView Writer;
        public final ImageView BookPicture;
        Book_Adapter adapter;

        public ViewHolder(View itemView, Book_Adapter adapter) {
            super(itemView);
            BookName = itemView.findViewById(R.id.item_bookName);
            Writer = itemView.findViewById(R.id.Writer);
            BookPicture = itemView.findViewById(R.id.BookPIC);
            this.adapter = adapter;
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            int pos = getLayoutPosition();
            book_dialog book_dialog = new book_dialog(v.getContext(), pos, viewModel ,bookList);
            book_dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
            book_dialog.show();
            book_dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    notifyDataSetChanged();
                }
            });

        }
    }


}


