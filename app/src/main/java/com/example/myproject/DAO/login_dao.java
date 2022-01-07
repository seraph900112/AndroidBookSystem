package com.example.myproject.DAO;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.myproject.Model.login_user;


import java.util.List;

@Dao
public interface login_dao {

    @Query("SELECT * from login_user")
    LiveData<List<login_user>> getAllUser();

    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insert(login_user loginUser);

    @Query("SELECT * from login_user limit 1")
    login_user[] getAnUser();

    @Query("DELETE FROM login_user")
    void deleteAll();

}
