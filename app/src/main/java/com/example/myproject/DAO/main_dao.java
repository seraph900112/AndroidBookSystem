package com.example.myproject.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.myproject.Model.Book;
import com.example.myproject.Model.User;

import java.util.List;

@Dao
public interface main_dao {

    @Query("SELECT * FROM USER WHERE USERID == :id")
    User selectUserById(int id);


    @Query("DELETE FROM USER")
    void deleteAllUser();

    @Insert
    void insert(User user);

    @Query("SELECT * FROM Book ORDER BY BOOKID DESC")
    LiveData<List<Book>> selectAllBook();


    @Query("DELETE FROM BOOK")
    void deleteAllBook();

    @Insert
    void insert(Book book);


}
