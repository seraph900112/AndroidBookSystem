package com.example.myproject.Model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.sql.Blob;

@Entity(tableName = "Book")
public class Book {
    @PrimaryKey
    @ColumnInfo(name = "bookId")
    private int bookId;

    @NonNull
    @ColumnInfo(name = "bookName")
    private String bookName = "NULL";
    @ColumnInfo(name = "Type")
    private String Type;

    @ColumnInfo(name = "Writer")
    private String Writer;

    @Ignore
    private byte[] BookPicture;

    public Book(int bookId, @NonNull String bookName, String Type, String Writer) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.Type = Type;
        this.Writer = Writer;
    }

    public Book(int bookId, @NonNull String bookName, String Type, String Writer, byte[] bookPicture) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.Type = Type;
        this.Writer = Writer;
        this.BookPicture = bookPicture;
    }

    public int getBookId() {
        return bookId;
    }

    @NonNull
    public String getBookName() {
        return bookName;
    }

    public String getType() {
        return Type;
    }

    public String getWriter() {
        return Writer;
    }

    public byte[] getBookPicture() {
        return BookPicture;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public void setBookName(@NonNull String bookName) {
        this.bookName = bookName;
    }

    public void setType(String type) {
        Type = type;
    }

    public void setWriter(String writer) {
        Writer = writer;
    }

    public void setBookPicture(byte[] bookPicture) {
        BookPicture = bookPicture;
    }
}
